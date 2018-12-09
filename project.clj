(defproject r0man/alumbra.printer "0.1.0-SNAPSHOT"
  :description "Pretty printer for the Alumbra AST."
  :url "https://github.com/r0man/alumbra.printer"
  :license {:name "MIT License"
            :url "https://opensource.org/licenses/MIT"
            :year 2018
            :key "mit"}
  :dependencies [[org.clojure/clojure "1.10.0"]]
  :profiles {:dev {:dependencies [[alumbra/analyzer "0.1.17"]
                                  [alumbra/generators "0.2.2"]
                                  [alumbra/parser "0.1.7"]
                                  [alumbra/spec "0.1.11"]
                                  [org.clojure/clojurescript "1.10.439"]
                                  [org.clojure/test.check "0.9.0"]]
                   :plugins [[jonase/eastwood "0.3.3"]
                             [lein-cljsbuild "1.1.7"]
                             [lein-doo "0.1.10"]]}}
  :aliases
  {"ci" ["do"
         ["clean"]
         ["test"]
         ["doo" "node" "node" "once"]
         ["lint"]]
   "lint" ["do"  ["eastwood"]]}
  :cljsbuild
  {:builds
   [{:id "node"
     :compiler
     {:main alumbra.printer.test.runnter
      :optimizations :none
      :output-dir "target/node"
      :output-to "target/alumbra.printer.test.js"
      :pretty-print true
      :target :nodejs}
     :source-paths ["src" "test"]}]}
  ;; :pedantic? :abort
  )
