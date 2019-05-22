(ns alumbra.printer-test
  (:require [alumbra.analyzer :as analyzer]
            [alumbra.generators :as alumbra-gen]
            [alumbra.parser :as parser]
            [alumbra.printer :as printer]
            [alumbra.spec :as specs]
            [clojure.pprint :refer [pprint]]
            [clojure.spec.alpha :as s]
            [clojure.test :refer [deftest is]]
            [clojure.test.check.clojure-test :refer [defspec]]
            [clojure.test.check.generators :as gen]
            [clojure.test.check.properties :as prop]
            [clojure.walk :as walk]
            [com.gfredericks.test.chuck :as chuck]))

(defn infinity? [x]
  (cond
    (double? x)
    (Double/isInfinite x)
    (float? x)
    (Float/isInfinite x)
    :else false))

(defn contains-infinity? [x]
  (cond
    (infinity? x)
    true
    (map? x)
    (or (some true? (map contains-infinity? (keys x)))
        (some true? (map contains-infinity? (vals x))))
    (sequential? x)
    (some true? (map contains-infinity? x))
    :else x))

(defn- strip-keys [m ks]
  (let [ks (set ks)
        f (fn [[k v]]
            (when-not (contains? ks k)
              [k v]))]
    (walk/postwalk
     (fn [x]
       (if (map? x)
         (into {} (map f x))
         x))
     m)))

(defn- strip-metadata [m]
  (strip-keys m #{:alumbra/metadata}))

(defn- throw-on-error [result]
  (if (contains? result :alumbra/parser-errors)
    (throw (ex-info "Can't parse document." result))
    result))

(defn- parse-document! [s]
  (-> (parser/parse-document s)
      (throw-on-error)
      (strip-metadata)))

(defn- parse-schema! [s]
  (-> (parser/parse-schema s)
      (throw-on-error)
      (strip-metadata)))

(defn- parse! [s]
  (or (try (parse-document! s)
           (catch Exception _))
      (parse-schema! s)))

(defn debug [doc]
  (binding [*print-namespace-maps* false]
    (let [ast (parse! doc)
          doc' (printer/pr-str ast)
          ast' (parse! doc')]
      (or (= ast ast')
          (do (println "---- ORIGINAL DOC ---------------------------------------------------------\n")
              (spit "ORIGINAL.graphql" doc)
              (println doc "\n")

              (println "---- PRINTED DOC ----------------------------------------------------------\n")
              (spit "PRINTED.graphql" doc')
              (println doc')

              (println "---- ORIGINAL AST ---------------------------------------------------------\n")
              (spit "ORIGINAL.edn" ast)
              (pprint ast)
              (println)
              (println "---- PRINTED AST ----------------------------------------------------------\n")
              (pprint ast')
              (println)
              (spit "PRINTED.edn" ast')
              (is (= ast ast')))))))

(defn- roundtrip? [parse-fn s]
  (let [ast (parse-fn s)]
    (= ast (parse-fn (printer/pr-str ast)))))

(def schema
  "type Person { name: String!, pets: [Pet!] }
   type Pet { name: String!, meows: Boolean }
   union PersonOrPet = Person | Pet
   enum PositionKind { LONG, LAT }
   input Position { x: Int, y: Int, k: PositionKind! }
   type QueryRoot { person(name: String!): Person, random(seed: Position!): PersonOrPet }
   type MutationRoot { createPerson(name: String!): Person! }
   schema { query: QueryRoot, mutation: MutationRoot }")

(def gen-operation
  (alumbra-gen/operation (analyzer/analyze-schema schema parser/parse-schema)))

(def gen-document
  (gen/let [operation-name (s/gen :alumbra/operation-name)
            operation (gen/elements [:query :mutation])
            document (gen-operation operation operation-name)]
    document))

(def gen-raw-document
  (gen/such-that
   #(not (contains-infinity? (parse-document! %)))
   (alumbra-gen/raw-document) 1000))

(def gen-raw-schema
  (gen/such-that
   #(not (contains-infinity? (parse-schema! %)))
   (alumbra-gen/raw-schema) 1000))

(defspec t-print-raw-document (chuck/times 20)
  (prop/for-all [document gen-raw-document]
    (string? (with-out-str (printer/print document)))))

(defspec t-pprint-raw-document (chuck/times 20)
  (prop/for-all [document gen-raw-document]
    (string? (with-out-str (printer/pprint document)))))

(defspec t-roundtrip-document (chuck/times 20)
  (prop/for-all [document gen-document]
    (roundtrip? parse-document! document)))

(defspec t-roundtrip-raw-document (chuck/times 20)
  (prop/for-all [document gen-raw-document]
    (roundtrip? parse-document! document)))

(defspec t-roundtrip-raw-schema (chuck/times 20)
  (prop/for-all [schema gen-raw-schema]
    (roundtrip? parse-schema! schema)))

(def debug-doc
  "schema {query: K0xg6}\nscalar D8yb35\ninterface Pi5a4e2 @e473td @P1AxC {Km8sIFKK: [I7xki], j44El: R4s}")

(deftest test-debug
  (is (debug debug-doc)))

(deftest test-main
  (is (string? (with-out-str (printer/-main "test-resources/document.edn")))))

(comment

  ;; Print a raw document
  (spit "document.edn"
        (with-out-str
          (->> (gen/generate gen-raw-document)
               (parse-document!)
               ;; (printer/print)
               (clojure.pprint/pprint)
               )))

  ;; Print a raw schema

  (->> (gen/generate gen-raw-schema)
       (parse-schema!)
       (printer/print))

  )
