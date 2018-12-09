(ns alumbra.printer
  #?(:cljs (:require-macros [alumbra.printer
                             :refer [with-emit-braces
                                     with-emit-curly-braces
                                     with-next-indentation-level]]))
  #?(:cljs (:import [goog.string StringBuffer]))
  (:require [clojure.string :as str])
  (:refer-clojure :exclude [pr-str print]))

(defn- printer
  "Returns a new printer for `opts`."
  [& [{:keys [indentation] :as opts}]]
  {:beginning-of-line? (atom true)
   :buffer (StringBuffer.)
   :indentation (or indentation 2)
   :indentation-level 0})

(defn- beginning-of-line?
  "Returns true if `printer` is at the beginning of a line, otherwise
  false."
  [printer]
  @(:beginning-of-line? printer))

(defn- current-indentation-str
  "Returns the whitespaces of the current indentation level for the
  `printer`."
  [printer]
  (apply str (repeat (:indentation-level printer) " ")))

(defn- append
  "Apply `str` on `args` and append the result to the string buffer in
  `printer`."
  [printer & args]
  (.append (:buffer printer) (apply str args)))

(defn- emit-indent-if-necessarry
  "Emits indentation if necessarry."
  [printer]
  (when (beginning-of-line? printer)
    (append printer (current-indentation-str printer))))

(defn- increment-indentation-level
  "Increments the indentation level of `printer`."
  [printer]
  (update printer :indentation-level + (:indentation printer)))

#?(:clj (defmacro with-next-indentation-level
          "Binds `printer` to `print-sym` with an incremented indentation level."
          [[printer-sym printer] & body]
          `(let [~printer-sym (alumbra.printer/increment-indentation-level ~printer)]
             ~@body)))

(defn- emit-newline
  "Emits a newline to `printer."
  [printer]
  (if (zero? (:indentation printer))
    (append printer " ")
    (do (append printer "\n")
        (reset! (:beginning-of-line? printer) true))))

(defn- emit-str
  "Emits the string `s`, which must not contain newlines to `printer."
  [printer & s]
  (emit-indent-if-necessarry printer)
  (let [s (apply str s)]
    (append printer s)
    (when-not (empty? s)
      (reset! (:beginning-of-line? printer) false))))

(defn- emit-freshline
  "Emits a newline to the `printer` if not at the beginning of a line."
  [printer]
  (when-not (beginning-of-line? printer)
    (emit-newline printer)))

(defn- emit-close-brace
  "Emits a closing brace to the `printer`."
  [printer]
  (emit-str printer ")"))

(defn- emit-close-curly-brace
  "Emits a closing curly brace to the `printer`."
  [printer]
  (emit-str printer "}"))

(defn- emit-separated
  "Emits the items in `coll` separated with `separator` using `emit-fn`
  to the `printer`."
  [printer emit-fn separator coll]
  (when (seq coll)
    (doseq [item (butlast coll)]
      (emit-fn printer item)
      (emit-str printer separator))
    (emit-fn printer (last coll))))

(defn- emit-comma-separated
  "Emits the items in `coll` comma separated using `emit-fn` to the `printer`."
  [printer emit-fn coll]
  (emit-separated printer emit-fn ", " coll))

(defn- emit-space-separated
  "Emits the items in `coll` space separated using `emit-fn` to the `printer`."
  [printer emit-fn coll]
  (emit-separated printer emit-fn " " coll))

(defn- emit-pipe-separated
  "Emits the items in `coll` pipe separated using `emit-fn` to the `printer`."
  [printer emit-fn coll]
  (emit-separated printer emit-fn " | " coll))

(defn- emit-open-brace
  "Emits a opening brace to the `printer`."
  [printer]
  (emit-str printer "("))

(defn- emit-open-curly-brace
  "Emits a opening curly brace to the `printer`."
  [printer]
  (emit-str printer "{"))

(defn- emit-seq
  "Emits the items in `coll` using `emit-fn` to the `printer`."
  [printer emit-fn coll]
  (doseq [item coll]
    (emit-fn printer item)
    (emit-freshline printer)))

(defn- emit-whitespace
  "Emits whitespace to the `printer`."
  [printer]
  (emit-str printer " "))

#?(:clj (defmacro with-emit-braces
          "Emits `body` within braces to the `printer`."
          [printer & body]
          `(let [printer# ~printer]
             (alumbra.printer/emit-open-brace printer#)
             ~@body
             (alumbra.printer/emit-close-brace printer#))))

#?(:clj (defmacro with-emit-curly-braces
          "Emits `body` within curly braces to the `printer`."
          [printer & body]
          `(let [printer# ~printer]
             (alumbra.printer/emit-open-curly-brace printer#)
             ~@body
             (alumbra.printer/emit-close-curly-brace printer#))))

(defn- emit-type-condition
  "Emits the Alumbra `type-condition` to the `printer`."
  [printer {:keys [alumbra/type-name] :as type-condition}]
  (emit-str printer " on " type-name))

(defmulti ^:private emit-value
  "Emits the Alumbra `value` to the `printer`."
  (fn [printer value] (:alumbra/value-type value)))

(defmethod emit-value nil [printer _]
  (emit-str printer "null"))

(defmethod emit-value :boolean
  [printer {:keys [alumbra/boolean]}]
  (emit-str printer boolean))

(defmethod emit-value :enum
  [printer {:keys [alumbra/enum]}]
  (emit-str printer enum))

(defmethod emit-value :float
  [printer {:keys [alumbra/float]}]
  (emit-str printer float))

(defmethod emit-value :integer
  [printer {:keys [alumbra/integer]}]
  (emit-str printer integer))

(defmethod emit-value :list
  [printer {:keys [alumbra/list]}]
  (emit-str printer "[")
  (emit-comma-separated printer emit-value list)
  (emit-str printer "]"))

(defmethod emit-value :null
  [printer {:keys [alumbra/value-type]}]
  (emit-str printer (name value-type)))

(defn- emit-object-field
  "Emits the Alumbra `object-field` to the `printer`."
  [printer {:keys [alumbra/field-name alumbra/value] :as object-field}]
  (emit-str printer field-name ": ")
  (emit-value printer value))

(defmethod emit-value :object
  [printer {:keys [alumbra/object]}]
  (with-emit-curly-braces printer
    (emit-comma-separated printer emit-object-field object)))

(defmethod emit-value :string
  [printer {:keys [alumbra/string]}]
  (emit-str printer \" string \"))

(defmethod emit-value :variable
  [printer {:keys [alumbra/variable-name]}]
  (emit-str printer "$" variable-name))

(defmulti ^:private emit-type
  "Emits the Alumbra `type` to the `printer`."
  (fn [printer type] (:alumbra/type-class type)))

(defmethod emit-type :named-type
  [printer {:keys [alumbra/non-null?
                   alumbra/type-class
                   alumbra/type-name]}]
  (emit-str printer type-name)
  (when non-null? (emit-str printer "!")))

(defmethod emit-type :list-type
  [printer {:keys [alumbra/element-type alumbra/non-null?]}]
  (emit-str printer "[")
  (emit-type printer element-type)
  (emit-str printer "]")
  (when non-null? (emit-str printer "!")))

(defn- emit-argument
  "Emits the Alumbra `argument` to the `printer`."
  [printer {:keys [alumbra/argument-name alumbra/argument-value] :as argument}]
  (emit-str printer argument-name ": ")
  (emit-value printer argument-value))

(defn- emit-default-value
  "Emits the Alumbra `default-value` to the `printer`."
  [printer default-value]
  (when default-value
    (emit-str printer " = ")
    (emit-value printer default-value)))

(defn- emit-argument-definition
  "Emits the Alumbra `argument-definition` to the `printer`."
  [printer {:keys [alumbra/argument-name
                   alumbra/argument-type
                   alumbra/default-value]
            :as argument-definition}]
  (emit-str printer argument-name ": ")
  (emit-type printer argument-type)
  (emit-default-value printer default-value))

(defn- emit-arguments
  "Emits the Alumbra `arguments` to the `printer`."
  [printer arguments]
  (with-emit-braces printer
    (emit-comma-separated printer emit-argument arguments)))

(defn- emit-argument-definitions
  "Emits the Alumbra `argument-definitions` to the `printer`."
  [printer argument-definitions]
  (when (seq argument-definitions)
    (with-emit-braces printer
      (emit-comma-separated printer emit-argument-definition argument-definitions))))

(defn- emit-directive
  "Emits the Alumbra `directive` to the `printer`."
  [printer {:keys [alumbra/arguments alumbra/directive-name] :as directive}]
  (emit-str printer "@" directive-name)
  (when arguments (emit-arguments printer arguments)))

(defn- emit-directives
  "Emits the Alumbra `directives` to the `printer`."
  [printer directives]
  (emit-space-separated printer emit-directive directives))

(defn- emit-enum-field
  "Emits the Alumbra `enum-field` to the `printer`."
  [printer {:keys [alumbra/enum] :as enum-field}]
  (emit-str printer enum)
  (emit-freshline printer))

(defn- emit-enum-fields
  "Emits the Alumbra `enum-fields` to the `printer`."
  [printer enum-fields]
  (with-emit-curly-braces printer
    (emit-freshline printer)
    (with-next-indentation-level [printer printer]
      (doseq [enum-field enum-fields]
        (emit-enum-field printer enum-field)))))

(defn- emit-enum-definition
  "Emits the Alumbra `enum-definition` to the `printer`."
  [printer {:keys [alumbra/directives
                   alumbra/type-name
                   alumbra/enum-fields]
            :as enum-definition}]
  (emit-str printer "enum")
  (emit-whitespace printer)
  (emit-str printer type-name)
  (emit-whitespace printer)
  (when directives
    (emit-directives printer directives)
    (emit-whitespace printer))
  (emit-enum-fields printer enum-fields)
  (emit-freshline printer))

(declare emit-selection-set)

(defn- emit-selection
  "Emits the Alumbra `selection` to the `printer`."
  [printer {:keys [alumbra/arguments
                   alumbra/directives
                   alumbra/field-alias
                   alumbra/field-name
                   alumbra/fragment-name
                   alumbra/selection-set
                   alumbra/type-condition]
            :as selection}]
  (when field-alias
    (emit-str printer field-alias ": "))
  (when field-name
    (emit-str printer field-name))
  (when (or (not field-name) fragment-name type-condition)
    (emit-str printer "..."))
  (when fragment-name
    (emit-str printer fragment-name))
  (when type-condition
    (emit-type-condition printer type-condition))
  (when arguments
    (emit-arguments printer arguments))
  (when directives
    (emit-whitespace printer)
    (emit-directives printer directives))
  (when selection-set
    (emit-whitespace printer)
    (with-emit-curly-braces printer
      (emit-freshline printer)
      (with-next-indentation-level [printer printer]
        (emit-selection-set printer selection-set))))
  (emit-freshline printer))

(defn- emit-selection-set
  "Emits the Alumbra `selection-set` to the `printer`."
  [printer selection-set]
  (emit-seq printer emit-selection selection-set))

(defn- emit-variable
  "Emits the Alumbra `variable` to the `printer`."
  [printer {:keys [alumbra/default-value
                   alumbra/type
                   alumbra/variable-name]}]
  (emit-str printer "$" variable-name ": ")
  (emit-type printer type)
  (emit-default-value printer default-value))

(defn- emit-variables
  "Emits the Alumbra `variables` to the `printer`."
  [printer variables]
  (with-emit-braces printer
    (emit-comma-separated printer emit-variable variables)))

(defn- emit-operation
  "Emits the Alumbra `operation` set to the `printer`."
  [printer {:keys [alumbra/directives
                   alumbra/operation-name
                   alumbra/operation-type
                   alumbra/selection-set
                   alumbra/variables]
            :as operation}]
  (when operation-type
    (emit-str printer operation-type " "))
  (when operation-name
    (emit-str printer operation-name " "))
  (when variables
    (emit-variables printer variables)
    (emit-whitespace printer))
  (when directives
    (emit-directives printer directives)
    (emit-whitespace printer))
  (with-emit-curly-braces printer
    (emit-freshline printer)
    (with-next-indentation-level [printer printer]
      (emit-selection-set printer selection-set)))
  (emit-freshline printer))

(defn- emit-fragment
  "Emits the Alumbra `fragment` to the `printer`."
  [printer {:keys [alumbra/directives
                   alumbra/fragment-name
                   alumbra/type-condition
                   alumbra/selection-set]
            :as fragment}]
  (emit-str printer "fragment " fragment-name)
  (emit-type-condition printer type-condition)
  (when directives
    (emit-whitespace printer)
    (emit-directives printer directives))
  (when selection-set
    (emit-whitespace printer)
    (with-emit-curly-braces printer
      (emit-freshline printer)
      (with-next-indentation-level [printer printer]
        (emit-selection-set printer selection-set))
      (emit-freshline printer)))
  (emit-freshline printer))

(defn- emit-type-name
  "Emits :alumbra/type-name to the `printer`."
  [printer {:keys [alumbra/type-name]}]
  (emit-str printer type-name))

(defn- emit-field-definition
  "Emits the Alumbra `field-definition` to the `printer`."
  [printer {:keys [alumbra/argument-definitions
                   alumbra/field-name
                   alumbra/type]
            :as field-definition}]
  (emit-str printer field-name)
  (emit-argument-definitions printer argument-definitions)
  (emit-str printer ": ")
  (emit-type printer type))

(defn- emit-field-definitions
  "Emits the Alumbra `field-definitions` to the `printer`."
  [printer field-definitions]
  (with-emit-curly-braces printer
    (emit-freshline printer)
    (with-next-indentation-level [printer printer]
      (doseq [field-definition field-definitions]
        (emit-field-definition printer field-definition)
        (emit-freshline printer))))
  (emit-freshline printer))

(defn- emit-interface-definition
  "Emits the Alumbra `interface-definition` to the `printer`."
  [printer {:keys [alumbra/field-definitions
                   alumbra/type-name]
            :as interface-definition}]
  (emit-str printer "interface " type-name)
  (emit-whitespace printer)
  (emit-field-definitions printer field-definitions))

(defn- emit-interface-types
  "Emits the Alumbra `interface-types` to the `printer`."
  [printer interface-types]
  (when (seq interface-types)
    (emit-str printer " implements ")
    (emit-comma-separated printer emit-type-name interface-types)))

(defn- emit-type-definition
  "Emits the Alumbra `type-definition` to the `printer`."
  [printer {:keys [alumbra/directives
                   alumbra/field-definitions
                   alumbra/interface-types
                   alumbra/type-name]
            :as type-definition}]
  (emit-str printer "type " type-name)
  (emit-interface-types printer interface-types)
  (emit-whitespace printer)
  (emit-directives printer directives)
  (emit-whitespace printer)
  (emit-field-definitions printer field-definitions))

(defn- emit-input-type-definition
  "Emits the Alumbra `input-type-definition` to the `printer`."
  [printer {:keys [alumbra/directives
                   alumbra/input-field-definitions
                   alumbra/type-name]
            :as type-definition}]
  (emit-str printer "input " type-name " ")
  (when directives
    (emit-directives printer directives)
    (emit-whitespace printer))
  (emit-field-definitions printer input-field-definitions))

(defn- emit-extend
  "Emits the Alumbra `type-extension` to the `printer`."
  [printer emit-definition-fn extension]
  (emit-str printer "extend")
  (emit-whitespace printer)
  (emit-definition-fn printer extension))

(defn- emit-type-extension
  "Emits the Alumbra `type-extension` to the `printer`."
  [printer type-extension]
  (emit-extend printer emit-type-definition type-extension))

(defn- emit-schema-field
  "Emits the Alumbra `schema-field` to the `printer`."
  [printer {:keys [alumbra/operation-type
                   alumbra/schema-type]
            :as schema-field}]
  (emit-str printer operation-type ": ")
  (emit-type-name printer schema-type))

(defn- emit-scalar-definition
  "Emits the Alumbra `scalar-definition` to the `printer`."
  [printer {:keys [alumbra/type-name] :as scalar-definition}]
  (emit-str printer "scalar " type-name)
  (emit-freshline printer))

(defn- emit-union-definition
  "Emits the Alumbra `union-definition` to the `printer`."
  [printer {:keys [alumbra/directives
                   alumbra/type-name
                   alumbra/union-types]
            :as union-definition}]
  (emit-str printer "union " type-name " = ")
  (emit-pipe-separated printer emit-type-name union-types)
  (when directives
    (emit-whitespace printer)
    (emit-directives printer directives))
  (emit-freshline printer))

(defn- directive-location-name
  "Returns the name of the directive location."
  [directive-location]
  (-> directive-location name str/upper-case (str/replace "-" "_")))

(defn- emit-directive-location
  "Emits the Alumbra `directive-location` to the `printer`."
  [printer directive-location]
  (emit-str printer (directive-location-name directive-location)))

(defn- emit-directive-locations
  "Emits the Alumbra `union-definition` to the `printer`."
  [printer directive-locations]
  (emit-str printer " on ")
  (emit-pipe-separated printer emit-directive-location directive-locations))

(defn- emit-directive-definition
  "Emits the Alumbra `directive-definition` to the `printer`."
  [printer {:keys [alumbra/argument-definitions
                   alumbra/directive-name
                   alumbra/directive-locations]
            :as directive-definition}]
  (emit-str printer "directive @" directive-name)
  (emit-argument-definitions printer argument-definitions)
  (emit-directive-locations printer directive-locations)
  (emit-freshline printer))

(defn- emit-schema-definition
  "Emits the Alumbra `schema-definition` to the `printer`."
  [printer {:keys [alumbra/directives alumbra/schema-fields] :as schema-definition}]
  (emit-str printer "schema ")
  (emit-directives printer directives)
  (when (seq directives) (emit-whitespace printer))
  (with-emit-curly-braces printer
    (emit-freshline printer)
    (with-next-indentation-level [printer printer]
      (doseq [schema-field schema-fields]
        (emit-schema-field printer schema-field)
        (emit-freshline printer))))
  (emit-freshline printer))

(defn- emit-document
  "Emits the Alumbra `document` to the `printer`."
  [printer {:keys [alumbra/enum-definitions
                   alumbra/directive-definitions
                   alumbra/fragments
                   alumbra/interface-definitions
                   alumbra/input-type-definitions
                   alumbra/operations
                   alumbra/scalar-definitions
                   alumbra/schema-definitions
                   alumbra/type-definitions
                   alumbra/type-extensions
                   alumbra/union-definitions]
            :as document}]
  (emit-seq printer emit-schema-definition schema-definitions)
  (emit-seq printer emit-scalar-definition scalar-definitions)
  (emit-seq printer emit-enum-definition enum-definitions)
  (emit-seq printer emit-interface-definition interface-definitions)
  (emit-seq printer emit-type-definition type-definitions)
  (emit-seq printer emit-union-definition union-definitions)
  (emit-seq printer emit-type-extension type-extensions)
  (emit-seq printer emit-input-type-definition input-type-definitions)
  (emit-seq printer emit-directive-definition directive-definitions)
  (emit-seq printer emit-operation operations)
  (emit-seq printer emit-fragment fragments))

(defn pr-str
  "Returns the Alumbra `doc` as a string."
  [doc & [opts]]
  (let [printer (printer opts)]
    (emit-document printer doc)
    (str (:buffer printer))))

(defn print
  "Prints the Alumbra GraphQL `doc`."
  [doc & [opts]]
  (clojure.core/print (pr-str doc opts)))
