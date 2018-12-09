(ns alumbra.printer-test
  (:require [alumbra.printer :as printer]
            [clojure.test :refer [deftest is]]))

(def document-ast
  {:alumbra/operations
   [{:alumbra/operation-type "query"
     :alumbra/operation-name "HAsS9tHg"
     :alumbra/selection-set
     [{:alumbra/field-name "__schema"
       :alumbra/selection-set
       [{:alumbra/field-name "mutationType"
         :alumbra/directives
         [{:alumbra/directive-name "include"
           :alumbra/arguments
           [{:alumbra/argument-name "if"
             :alumbra/argument-value
             {:alumbra/value-type :boolean :alumbra/boolean false}}]}]
         :alumbra/selection-set
         [{:alumbra/field-name "name"}
          {:alumbra/field-name "possibleTypes"
           :alumbra/directives
           [{:alumbra/directive-name "include"
             :alumbra/arguments
             [{:alumbra/argument-name "if"
               :alumbra/argument-value
               {:alumbra/value-type :boolean :alumbra/boolean true}}]}]
           :alumbra/selection-set [{:alumbra/field-name "name"}]}]}
        {:alumbra/field-name "types"
         :alumbra/directives
         [{:alumbra/directive-name "include"
           :alumbra/arguments
           [{:alumbra/argument-name "if"
             :alumbra/argument-value
             {:alumbra/value-type :boolean :alumbra/boolean true}}]}]
         :alumbra/selection-set
         [{:alumbra/field-name "ofType"
           :alumbra/directives
           [{:alumbra/directive-name "include"
             :alumbra/arguments
             [{:alumbra/argument-name "if"
               :alumbra/argument-value
               {:alumbra/value-type :boolean
                :alumbra/boolean false}}]}]
           :alumbra/selection-set
           [{:alumbra/field-name "interfaces"
             :alumbra/selection-set
             [{:alumbra/field-name "ofType"
               :alumbra/directives
               [{:alumbra/directive-name "skip"
                 :alumbra/arguments
                 [{:alumbra/argument-name "if"
                   :alumbra/argument-value
                   {:alumbra/value-type :boolean
                    :alumbra/boolean false}}]}]
               :alumbra/selection-set
               [{:alumbra/field-name
                 "fields"
                 :alumbra/selection-set
                 [{:alumbra/field-name "args"
                   :alumbra/selection-set
                   [{:alumbra/field-name "name"
                     :alumbra/directives
                     [{:alumbra/directive-name "include"
                       :alumbra/arguments
                       [{:alumbra/argument-name "if"
                         :alumbra/argument-value
                         {:alumbra/value-type :boolean
                          :alumbra/boolean true}}]}]}]}
                  {:alumbra/field-name "isDeprecated"}
                  {:alumbra/field-name "type"
                   :alumbra/directives
                   [{:alumbra/directive-name "skip"
                     :alumbra/arguments
                     [{:alumbra/argument-name "if"
                       :alumbra/argument-value
                       {:alumbra/value-type :boolean
                        :alumbra/boolean false}}]}]
                   :alumbra/selection-set
                   [{:alumbra/field-name
                     "interfaces"
                     :alumbra/directives
                     [{:alumbra/directive-name "skip"
                       :alumbra/arguments
                       [{:alumbra/argument-name "if"
                         :alumbra/argument-value
                         {:alumbra/value-type :boolean
                          :alumbra/boolean false}}]}]
                     :alumbra/selection-set
                     [{:alumbra/field-name "ofType"
                       :alumbra/selection-set
                       [{:alumbra/field-name "interfaces"
                         :alumbra/directives
                         [{:alumbra/directive-name "skip"
                           :alumbra/arguments
                           [{:alumbra/argument-name "if"
                             :alumbra/argument-value
                             {:alumbra/value-type :boolean
                              :alumbra/boolean false}}]}]
                         :alumbra/selection-set
                         [{:alumbra/field-name "enumValues"

                           :alumbra/directives
                           [{:alumbra/directive-name "include"
                             :alumbra/arguments
                             [{:alumbra/argument-name "if"
                               :alumbra/argument-value
                               {:alumbra/value-type :boolean
                                :alumbra/boolean true}}]}]
                           :alumbra/selection-set
                           [{:alumbra/field-name "isDeprecated"}
                            {:alumbra/field-name "description"}]}
                          {:alumbra/field-name "possibleTypes"
                           :alumbra/directives
                           [{:alumbra/directive-name "skip"
                             :alumbra/arguments
                             [{:alumbra/argument-name "if"
                               :alumbra/argument-value
                               {:alumbra/value-type :boolean
                                :alumbra/boolean true}}]}]
                           :alumbra/selection-set

                           [{:alumbra/field-name "enumValues"
                             :alumbra/selection-set

                             [{:alumbra/field-name "description"
                               :alumbra/directives
                               [{:alumbra/directive-name "skip"
                                 :alumbra/arguments
                                 [{:alumbra/argument-name "if"
                                   :alumbra/argument-value
                                   {:alumbra/value-type :boolean
                                    :alumbra/boolean true}}]}]}]}
                            {:alumbra/field-name "kind"}]}]}]}
                      {:alumbra/field-name "name"
                       :alumbra/directives
                       [{:alumbra/directive-name "skip"
                         :alumbra/arguments
                         [{:alumbra/argument-name "if"
                           :alumbra/argument-value
                           {:alumbra/value-type :boolean
                            :alumbra/boolean false}}]}]}
                      {:alumbra/field-name "possibleTypes"
                       :alumbra/selection-set

                       [{:alumbra/field-name "interfaces"
                         :alumbra/directives
                         [{:alumbra/directive-name "include"
                           :alumbra/arguments
                           [{:alumbra/argument-name "if"
                             :alumbra/argument-value
                             {:alumbra/value-type :boolean
                              :alumbra/boolean false}}]}]
                         :alumbra/selection-set
                         [{:alumbra/field-name "inputFields"
                           :alumbra/selection-set
                           [{:alumbra/field-name "type"
                             :alumbra/directives
                             [{:alumbra/directive-name "include"
                               :alumbra/arguments
                               [{:alumbra/argument-name "if"
                                 :alumbra/argument-value
                                 {:alumbra/value-type :boolean
                                  :alumbra/boolean false}}]}]

                             :alumbra/selection-set
                             [{:alumbra/field-name "ofType"
                               :alumbra/directives
                               [{:alumbra/directive-name "include"
                                 :alumbra/arguments
                                 [{:alumbra/argument-name "if"
                                   :alumbra/argument-value
                                   {:alumbra/value-type :boolean
                                    :alumbra/boolean true}}]}]
                               :alumbra/selection-set
                               [{:alumbra/field-name "kind"

                                 :alumbra/directives
                                 [{:alumbra/directive-name "skip"
                                   :alumbra/arguments
                                   [{:alumbra/argument-name "if"
                                     :alumbra/argument-value
                                     {:alumbra/value-type :boolean
                                      :alumbra/boolean true}}]}]}
                                {:alumbra/field-name "description"
                                 :alumbra/directives
                                 [{:alumbra/directive-name "skip"
                                   :alumbra/arguments
                                   [{:alumbra/argument-name "if"
                                     :alumbra/argument-value
                                     {:alumbra/value-type :boolean
                                      :alumbra/boolean true}}]}]}]}
                              {:alumbra/field-name "fields"
                               :alumbra/directives
                               [{:alumbra/directive-name
                                 "include"
                                 :alumbra/arguments
                                 [{:alumbra/argument-name "if"
                                   :alumbra/argument-value
                                   {:alumbra/value-type :boolean
                                    :alumbra/boolean false}}]}]
                               :alumbra/selection-set
                               [{:alumbra/field-name "args"
                                 :alumbra/directives
                                 [{:alumbra/directive-name "include"
                                   :alumbra/arguments
                                   [{:alumbra/argument-name "if"
                                     :alumbra/argument-value
                                     {:alumbra/value-type :boolean
                                      :alumbra/boolean true}}]}]
                                 :alumbra/selection-set
                                 [{:alumbra/field-name "name"
                                   :alumbra/directives

                                   [{:alumbra/directive-name "skip"
                                     :alumbra/arguments
                                     [{:alumbra/argument-name "if"
                                       :alumbra/argument-value
                                       {:alumbra/value-type :boolean
                                        :alumbra/boolean false}}]}]}
                                  {:alumbra/field-name "defaultValue"
                                   :alumbra/directives
                                   [{:alumbra/directive-name "skip"
                                     :alumbra/arguments
                                     [{:alumbra/argument-name "if"
                                       :alumbra/argument-value
                                       {:alumbra/value-type :boolean
                                        :alumbra/boolean false}}]}]}]}]}
                              {:alumbra/field-name "description"
                               :alumbra/directives
                               [{:alumbra/directive-name
                                 "include"
                                 :alumbra/arguments
                                 [{:alumbra/argument-name "if"
                                   :alumbra/argument-value
                                   {:alumbra/value-type :boolean
                                    :alumbra/boolean false}}]}]}]}]}
                          {:alumbra/field-name "fields"
                           :alumbra/directives
                           [{:alumbra/directive-name "skip"
                             :alumbra/arguments
                             [{:alumbra/argument-name "if"
                               :alumbra/argument-value
                               {:alumbra/value-type :boolean
                                :alumbra/boolean true}}]}]
                           :alumbra/selection-set
                           [{:alumbra/field-name "__typename"
                             :alumbra/directives
                             [{:alumbra/directive-name "include"
                               :alumbra/arguments

                               [{:alumbra/argument-name "if"
                                 :alumbra/argument-value
                                 {:alumbra/value-type :boolean
                                  :alumbra/boolean false}}]}]}
                            {:alumbra/field-name "isDeprecated"
                             :alumbra/directives
                             [{:alumbra/directive-name "skip"
                               :alumbra/arguments
                               [{:alumbra/argument-name "if"
                                 :alumbra/argument-value
                                 {:alumbra/value-type :boolean
                                  :alumbra/boolean true}}]}]}]}]}
                        {:alumbra/field-name "fields"
                         :alumbra/selection-set
                         [{:alumbra/field-name "description"
                           :alumbra/directives
                           [{:alumbra/directive-name "include"
                             :alumbra/arguments
                             [{:alumbra/argument-name
                               "if"
                               :alumbra/argument-value
                               {:alumbra/value-type :boolean
                                :alumbra/boolean true}}]}]}]}
                        {:alumbra/field-name "description"}]}]}
                    {:alumbra/field-name "description"
                     :alumbra/directives
                     [{:alumbra/directive-name "include"
                       :alumbra/arguments
                       [{:alumbra/argument-name "if"
                         :alumbra/argument-value
                         {:alumbra/value-type :boolean
                          :alumbra/boolean false}}]}]}]}]}
                {:alumbra/field-name "possibleTypes"
                 :alumbra/directives
                 [{:alumbra/directive-name "include"
                   :alumbra/arguments
                   [{:alumbra/argument-name "if"
                     :alumbra/argument-value
                     {:alumbra/value-type :boolean
                      :alumbra/boolean true}}]}]
                 :alumbra/selection-set

                 [{:alumbra/field-name "interfaces"
                   :alumbra/directives
                   [{:alumbra/directive-name "skip"
                     :alumbra/arguments
                     [{:alumbra/argument-name "if"
                       :alumbra/argument-value
                       {:alumbra/value-type :boolean
                        :alumbra/boolean true}}]}]
                   :alumbra/selection-set
                   [{:alumbra/field-name "inputFields"
                     :alumbra/directives
                     [{:alumbra/directive-name "skip"
                       :alumbra/arguments
                       [{:alumbra/argument-name "if"
                         :alumbra/argument-value
                         {:alumbra/value-type :boolean
                          :alumbra/boolean true}}]}]
                     :alumbra/selection-set
                     [{:alumbra/field-name "name"
                       :alumbra/directives
                       [{:alumbra/directive-name "skip"
                         :alumbra/arguments

                         [{:alumbra/argument-name "if"
                           :alumbra/argument-value
                           {:alumbra/value-type :boolean
                            :alumbra/boolean true}}]}]}]}
                    {:alumbra/field-name "ofType"
                     :alumbra/directives
                     [{:alumbra/directive-name "skip"
                       :alumbra/arguments
                       [{:alumbra/argument-name "if"
                         :alumbra/argument-value
                         {:alumbra/value-type :boolean
                          :alumbra/boolean true}}]}]
                     :alumbra/selection-set
                     [{:alumbra/field-name "inputFields"

                       :alumbra/directives
                       [{:alumbra/directive-name "include"
                         :alumbra/arguments
                         [{:alumbra/argument-name "if"
                           :alumbra/argument-value
                           {:alumbra/value-type :boolean
                            :alumbra/boolean true}}]}]
                       :alumbra/selection-set
                       [{:alumbra/field-name "__typename"}
                        {:alumbra/field-name "type"
                         :alumbra/selection-set
                         [{:alumbra/field-name "interfaces"
                           :alumbra/directives
                           [{:alumbra/directive-name "skip"
                             :alumbra/arguments
                             [{:alumbra/argument-name "if"
                               :alumbra/argument-value
                               {:alumbra/value-type :boolean
                                :alumbra/boolean false}}]}]
                           :alumbra/selection-set

                           [{:alumbra/field-name "kind"
                             :alumbra/directives
                             [{:alumbra/directive-name "skip"
                               :alumbra/arguments
                               [{:alumbra/argument-name "if"
                                 :alumbra/argument-value
                                 {:alumbra/value-type :boolean
                                  :alumbra/boolean true}}]}]}
                            {:alumbra/field-name "description"
                             :alumbra/directives
                             [{:alumbra/directive-name "include"
                               :alumbra/arguments
                               [{:alumbra/argument-name "if"
                                 :alumbra/argument-value
                                 {:alumbra/value-type :boolean
                                  :alumbra/boolean false}}]}]}]}
                          {:alumbra/field-name "name"
                           :alumbra/directives
                           [{:alumbra/directive-name
                             "include"
                             :alumbra/arguments
                             [{:alumbra/argument-name "if"
                               :alumbra/argument-value
                               {:alumbra/value-type :boolean
                                :alumbra/boolean true}}]}]}
                          {:alumbra/field-name "possibleTypes"
                           :alumbra/selection-set
                           [{:alumbra/field-name "interfaces"
                             :alumbra/directives
                             [{:alumbra/directive-name "skip"
                               :alumbra/arguments
                               [{:alumbra/argument-name "if"
                                 :alumbra/argument-value
                                 {:alumbra/value-type :boolean

                                  :alumbra/boolean true}}]}]
                             :alumbra/selection-set
                             [{:alumbra/field-name "interfaces"
                               :alumbra/directives
                               [{:alumbra/directive-name "include"
                                 :alumbra/arguments
                                 [{:alumbra/argument-name "if"
                                   :alumbra/argument-value
                                   {:alumbra/value-type :boolean
                                    :alumbra/boolean false}}]}]
                               :alumbra/selection-set
                               [{:alumbra/field-name "fields"
                                 :alumbra/selection-set
                                 [{:alumbra/field-name "isDeprecated"
                                   :alumbra/directives
                                   [{:alumbra/directive-name "include"
                                     :alumbra/arguments
                                     [{:alumbra/argument-name
                                       "if"
                                       :alumbra/argument-value
                                       {:alumbra/value-type :boolean
                                        :alumbra/boolean false}}]}]}
                                  {:alumbra/field-name "type"
                                   :alumbra/selection-set
                                   [{:alumbra/field-name "kind"
                                     :alumbra/directives
                                     [{:alumbra/directive-name "skip"
                                       :alumbra/arguments
                                       [{:alumbra/argument-name "if"
                                         :alumbra/argument-value
                                         {:alumbra/value-type :boolean
                                          :alumbra/boolean
                                          false}}]}]}]}]}]}
                              {:alumbra/field-name "enumValues"
                               :alumbra/directives
                               [{:alumbra/directive-name
                                 "skip"
                                 :alumbra/arguments
                                 [{:alumbra/argument-name "if"
                                   :alumbra/argument-value
                                   {:alumbra/value-type :boolean
                                    :alumbra/boolean true}}]}]
                               :alumbra/selection-set
                               [{:alumbra/field-name "name"}
                                {:alumbra/field-name "deprecationReason"
                                 :alumbra/directives
                                 [{:alumbra/directive-name "include"
                                   :alumbra/arguments
                                   [{:alumbra/argument-name "if"
                                     :alumbra/argument-value
                                     {:alumbra/value-type :boolean
                                      :alumbra/boolean true}}]}]}
                                {:alumbra/field-name "description"
                                 :alumbra/directives

                                 [{:alumbra/directive-name "include"
                                   :alumbra/arguments
                                   [{:alumbra/argument-name "if"
                                     :alumbra/argument-value
                                     {:alumbra/value-type :boolean
                                      :alumbra/boolean false}}]}]}]}
                              {:alumbra/field-name "name"}]}
                            {:alumbra/field-name "name"
                             :alumbra/directives
                             [{:alumbra/directive-name "include"
                               :alumbra/arguments
                               [{:alumbra/argument-name "if"
                                 :alumbra/argument-value
                                 {:alumbra/value-type :boolean
                                  :alumbra/boolean false}}]}]}
                            {:alumbra/field-name "possibleTypes"
                             :alumbra/directives

                             [{:alumbra/directive-name "skip"
                               :alumbra/arguments
                               [{:alumbra/argument-name "if"
                                 :alumbra/argument-value
                                 {:alumbra/value-type :boolean
                                  :alumbra/boolean false}}]}]
                             :alumbra/selection-set
                             [{:alumbra/field-name "interfaces"
                               :alumbra/directives
                               [{:alumbra/directive-name "skip"
                                 :alumbra/arguments
                                 [{:alumbra/argument-name "if"
                                   :alumbra/argument-value
                                   {:alumbra/value-type :boolean
                                    :alumbra/boolean false}}]}]
                               :alumbra/selection-set
                               [{:alumbra/field-name "enumValues"
                                 :alumbra/directives

                                 [{:alumbra/directive-name "include"
                                   :alumbra/arguments
                                   [{:alumbra/argument-name "if"
                                     :alumbra/argument-value
                                     {:alumbra/value-type :boolean
                                      :alumbra/boolean true}}]}]
                                 :alumbra/selection-set
                                 [{:alumbra/field-name "name"}
                                  {:alumbra/field-name
                                   "deprecationReason"
                                   :alumbra/directives
                                   [{:alumbra/directive-name "include"
                                     :alumbra/arguments
                                     [{:alumbra/argument-name "if"
                                       :alumbra/argument-value
                                       {:alumbra/value-type :boolean
                                        :alumbra/boolean false}}]}]}]}

                                {:alumbra/field-name "ofType"
                                 :alumbra/directives
                                 [{:alumbra/directive-name "skip"
                                   :alumbra/arguments
                                   [{:alumbra/argument-name "if"
                                     :alumbra/argument-value
                                     {:alumbra/value-type :boolean
                                      :alumbra/boolean false}}]}]
                                 :alumbra/selection-set
                                 [{:alumbra/field-name "ofType"
                                   :alumbra/selection-set
                                   [{:alumbra/field-name "interfaces"
                                     :alumbra/selection-set
                                     [{:alumbra/field-name "interfaces"
                                       :alumbra/directives
                                       [{:alumbra/directive-name "skip"
                                         :alumbra/arguments

                                         [{:alumbra/argument-name "if"
                                           :alumbra/argument-value
                                           {:alumbra/value-type :boolean
                                            :alumbra/boolean true}}]}]
                                       :alumbra/selection-set
                                       [{:alumbra/field-name
                                         "inputFields"
                                         :alumbra/selection-set
                                         [{:alumbra/field-name "type"
                                           :alumbra/selection-set
                                           [{:alumbra/field-name
                                             "possibleTypes"
                                             :alumbra/directives
                                             [{:alumbra/directive-name
                                               "skip"
                                               :alumbra/arguments
                                               [
                                                {:alumbra/argument-name
                                                 "if"
                                                 :alumbra/argument-value
                                                 {:alumbra/value-type
                                                  :boolean
                                                  :alumbra/boolean
                                                  true}}]}]
                                             :alumbra/selection-set
                                             [{:alumbra/field-name
                                               "fields"
                                               :alumbra/selection-set
                                               [{:alumbra/field-name

                                                 "deprecationReason"
                                                 :alumbra/directives
                                                 [{:alumbra/directive-name
                                                   "include"
                                                   :alumbra/arguments
                                                   [{:alumbra/argument-name
                                                     "if"
                                                     :alumbra/argument-value
                                                     {:alumbra/value-type
                                                      :boolean
                                                      :alumbra/boolean
                                                      true}}]}]}
                                                {:alumbra/field-name
                                                 "isDeprecated"}]}]}
                                            {:alumbra/field-name

                                             "description"
                                             :alumbra/directives
                                             [{:alumbra/directive-name
                                               "include"
                                               :alumbra/arguments
                                               [{:alumbra/argument-name
                                                 "if"
                                                 :alumbra/argument-value
                                                 {:alumbra/value-type
                                                  :boolean
                                                  :alumbra/boolean
                                                  true}}]}]}]}
                                          {:alumbra/field-name
                                           "defaultValue"
                                           :alumbra/directives
                                           [{:alumbra/directive-name
                                             "skip"

                                             :alumbra/arguments
                                             [{:alumbra/argument-name
                                               "if"
                                               :alumbra/argument-value
                                               {:alumbra/value-type
                                                :boolean
                                                :alumbra/boolean
                                                true}}]}]}
                                          {:alumbra/field-name
                                           "description"}]}
                                        {:alumbra/field-name "fields"
                                         :alumbra/directives
                                         [{:alumbra/directive-name
                                           "skip"
                                           :alumbra/arguments
                                           [{:alumbra/argument-name "if"
                                             :alumbra/argument-value

                                             {:alumbra/value-type
                                              :boolean
                                              :alumbra/boolean
                                              false}}]}]
                                         :alumbra/selection-set
                                         [{:alumbra/field-name
                                           "deprecationReason"}
                                          {:alumbra/field-name
                                           "description"

                                           :alumbra/directives
                                           [{:alumbra/directive-name
                                             "include"
                                             :alumbra/arguments
                                             [{:alumbra/argument-name
                                               "if"
                                               :alumbra/argument-value
                                               {:alumbra/value-type
                                                :boolean
                                                :alumbra/boolean
                                                false}}]}]}]}
                                        {:alumbra/field-name
                                         "description"
                                         :alumbra/directives
                                         [{:alumbra/directive-name
                                           "include"
                                           :alumbra/arguments

                                           [{:alumbra/argument-name "if"
                                             :alumbra/argument-value
                                             {:alumbra/value-type
                                              :boolean
                                              :alumbra/boolean
                                              true}}]}]}]}
                                      {:alumbra/field-name "name"
                                       :alumbra/directives
                                       [{:alumbra/directive-name
                                         "include"
                                         :alumbra/arguments
                                         [{:alumbra/argument-name "if"
                                           :alumbra/argument-value
                                           {:alumbra/value-type :boolean
                                            :alumbra/boolean false}}]}]}
                                      {:alumbra/field-name "description"
                                       :alumbra/directives

                                       [{:alumbra/directive-name
                                         "include"
                                         :alumbra/arguments
                                         [{:alumbra/argument-name "if"
                                           :alumbra/argument-value
                                           {:alumbra/value-type :boolean
                                            :alumbra/boolean true}}]}]}]}
                                    {:alumbra/field-name "name"
                                     :alumbra/directives
                                     [{:alumbra/directive-name "skip"
                                       :alumbra/arguments
                                       [{:alumbra/argument-name "if"
                                         :alumbra/argument-value
                                         {:alumbra/value-type :boolean
                                          :alumbra/boolean true}}]}]}
                                    {:alumbra/field-name "fields"
                                     :alumbra/selection-set
                                     [{:alumbra/field-name "name"
                                       :alumbra/directives
                                       [{:alumbra/directive-name "skip"
                                         :alumbra/arguments
                                         [{:alumbra/argument-name "if"
                                           :alumbra/argument-value
                                           {:alumbra/value-type :boolean
                                            :alumbra/boolean false}}]}]}
                                      {:alumbra/field-name
                                       "isDeprecated"}
                                      {:alumbra/field-name "type"
                                       :alumbra/selection-set
                                       [{:alumbra/field-name "name"
                                         :alumbra/directives
                                         [{:alumbra/directive-name "include"
                                           :alumbra/arguments
                                           [{:alumbra/argument-name "if"
                                             :alumbra/argument-value
                                             {:alumbra/value-type
                                              :boolean
                                              :alumbra/boolean true}}]}]}
                                        {:alumbra/field-name
                                         "possibleTypes"
                                         :alumbra/selection-set
                                         [{:alumbra/field-name
                                           "enumValues"
                                           :alumbra/arguments
                                           [{:alumbra/argument-name
                                             "includeDeprecated"
                                             :alumbra/argument-value
                                             {:alumbra/value-type

                                              :boolean
                                              :alumbra/boolean true}}]
                                           :alumbra/directives
                                           [{:alumbra/directive-name
                                             "include"
                                             :alumbra/arguments
                                             [{:alumbra/argument-name
                                               "if"
                                               :alumbra/argument-value
                                               {:alumbra/value-type
                                                :boolean
                                                :alumbra/boolean
                                                false}}]}]
                                           :alumbra/selection-set
                                           [{:alumbra/field-name
                                             "isDeprecated"
                                             :alumbra/directives

                                             [{:alumbra/directive-name
                                               "skip"
                                               :alumbra/arguments
                                               [{:alumbra/argument-name
                                                 "if"
                                                 :alumbra/argument-value
                                                 {:alumbra/value-type
                                                  :boolean
                                                  :alumbra/boolean
                                                  false}}]}]}]}
                                          {:alumbra/field-name "name"}
                                          {:alumbra/field-name
                                           "possibleTypes"
                                           :alumbra/directives
                                           [{:alumbra/directive-name
                                             "include"
                                             :alumbra/arguments

                                             [{:alumbra/argument-name
                                               "if"
                                               :alumbra/argument-value
                                               {:alumbra/value-type
                                                :boolean
                                                :alumbra/boolean
                                                false}}]}]
                                           :alumbra/selection-set
                                           [{:alumbra/field-name
                                             "fields"
                                             :alumbra/directives
                                             [{:alumbra/directive-name
                                               "skip"
                                               :alumbra/arguments
                                               [{:alumbra/argument-name
                                                 "if"
                                                 :alumbra/argument-value

                                                 {:alumbra/value-type
                                                  :boolean
                                                  :alumbra/boolean
                                                  true}}]}]
                                             :alumbra/selection-set
                                             [{:alumbra/field-name
                                               "deprecationReason"
                                               :alumbra/directives
                                               [{:alumbra/directive-name
                                                 "include"
                                                 :alumbra/arguments
                                                 [{:alumbra/argument-name
                                                   "if"
                                                   :alumbra/argument-value
                                                   {:alumbra/value-type
                                                    :boolean

                                                    :alumbra/boolean
                                                    true}}]}]}
                                              {:alumbra/field-name
                                               "description"}]}]}]}
                                        {:alumbra/field-name
                                         "description"
                                         :alumbra/directives
                                         [{:alumbra/directive-name
                                           "include"
                                           :alumbra/arguments
                                           [{:alumbra/argument-name "if"
                                             :alumbra/argument-value
                                             {:alumbra/value-type
                                              :boolean
                                              :alumbra/boolean

                                              false}}]}]}]}]}]}
                                  {:alumbra/field-name "kind"}]}]}
                              {:alumbra/field-name "kind"}
                              {:alumbra/field-name "description"
                               :alumbra/directives
                               [{:alumbra/directive-name "skip"
                                 :alumbra/arguments
                                 [{:alumbra/argument-name "if"
                                   :alumbra/argument-value
                                   {:alumbra/value-type :boolean
                                    :alumbra/boolean
                                    false}}]}]}]}]}]}]}]}]}
                  {:alumbra/field-name "enumValues"
                   :alumbra/selection-set
                   [{:alumbra/field-name "deprecationReason"
                     :alumbra/directives
                     [{:alumbra/directive-name "skip"
                       :alumbra/arguments
                       [{:alumbra/argument-name "if"
                         :alumbra/argument-value

                         {:alumbra/value-type :boolean
                          :alumbra/boolean true}}]}]}
                    {:alumbra/field-name "isDeprecated"}]}
                  {:alumbra/field-name "fields"
                   :alumbra/selection-set
                   [{:alumbra/field-name "name"}]}]}]}
              {:alumbra/field-name "description"}]}
            {:alumbra/field-name "enumValues"
             :alumbra/directives
             [{:alumbra/directive-name "skip"
               :alumbra/arguments
               [{:alumbra/argument-name "if"
                 :alumbra/argument-value
                 {:alumbra/value-type :boolean
                  :alumbra/boolean true}}]}]
             :alumbra/selection-set
             [{:alumbra/field-name "name"}
              {:alumbra/field-name "deprecationReason"
               :alumbra/directives
               [{:alumbra/directive-name "include"
                 :alumbra/arguments
                 [{:alumbra/argument-name "if"
                   :alumbra/argument-value

                   {:alumbra/value-type :boolean
                    :alumbra/boolean false}}]}]}]}]}
          {:alumbra/field-name "__typename"
           :alumbra/directives
           [{:alumbra/directive-name "include"
             :alumbra/arguments
             [{:alumbra/argument-name "if"

               :alumbra/argument-value
               {:alumbra/value-type :boolean
                :alumbra/boolean false}}]}]}
          {:alumbra/field-name "fields"
           :alumbra/directives
           [{:alumbra/directive-name "include"
             :alumbra/arguments
             [{:alumbra/argument-name "if"
               :alumbra/argument-value
               {:alumbra/value-type :boolean
                :alumbra/boolean false}}]}]
           :alumbra/selection-set
           [{:alumbra/field-name "args"
             :alumbra/directives
             [{:alumbra/directive-name "skip"
               :alumbra/arguments
               [{:alumbra/argument-name "if"
                 :alumbra/argument-value
                 {:alumbra/value-type :boolean
                  :alumbra/boolean false}}]}]
             :alumbra/selection-set
             [{:alumbra/field-name "type"
               :alumbra/selection-set
               [{:alumbra/field-name "ofType"
                 :alumbra/directives
                 [{:alumbra/directive-name "include"

                   :alumbra/arguments
                   [{:alumbra/argument-name "if"
                     :alumbra/argument-value
                     {:alumbra/value-type :boolean
                      :alumbra/boolean true}}]}]
                 :alumbra/selection-set
                 [{:alumbra/field-name "interfaces"
                   :alumbra/directives
                   [{:alumbra/directive-name "include"
                     :alumbra/arguments
                     [{:alumbra/argument-name "if"
                       :alumbra/argument-value
                       {:alumbra/value-type :boolean
                        :alumbra/boolean false}}]}]
                   :alumbra/selection-set
                   [{:alumbra/field-name "interfaces"
                     :alumbra/directives
                     [{:alumbra/directive-name "skip"
                       :alumbra/arguments
                       [{:alumbra/argument-name "if"
                         :alumbra/argument-value
                         {:alumbra/value-type :boolean

                          :alumbra/boolean true}}]}]
                     :alumbra/selection-set
                     [{:alumbra/field-name "kind"
                       :alumbra/directives
                       [{:alumbra/directive-name "include"
                         :alumbra/arguments
                         [{:alumbra/argument-name "if"
                           :alumbra/argument-value
                           {:alumbra/value-type :boolean
                            :alumbra/boolean false}}]}]}]}
                    {:alumbra/field-name "description"}]}
                  {:alumbra/field-name "enumValues"
                   :alumbra/directives
                   [{:alumbra/directive-name "include"
                     :alumbra/arguments
                     [{:alumbra/argument-name "if"
                       :alumbra/argument-value
                       {:alumbra/value-type :boolean
                        :alumbra/boolean false}}]}]
                   :alumbra/selection-set
                   [{:alumbra/field-name "deprecationReason"

                     :alumbra/directives
                     [{:alumbra/directive-name "skip"
                       :alumbra/arguments
                       [{:alumbra/argument-name "if"
                         :alumbra/argument-value
                         {:alumbra/value-type :boolean
                          :alumbra/boolean false}}]}]}]}
                  {:alumbra/field-name "kind"}]}]}]}
            {:alumbra/field-name "deprecationReason"}
            {:alumbra/field-name "type"
             :alumbra/selection-set
             [{:alumbra/field-name "inputFields"
               :alumbra/directives
               [{:alumbra/directive-name "skip"
                 :alumbra/arguments
                 [{:alumbra/argument-name "if"
                   :alumbra/argument-value
                   {:alumbra/value-type :boolean
                    :alumbra/boolean false}}]}]
               :alumbra/selection-set
               [{:alumbra/field-name "defaultValue"}
                {:alumbra/field-name "description"}]}]}]}]}
        {:alumbra/field-name "subscriptionType"
         :alumbra/directives
         [{:alumbra/directive-name "include"
           :alumbra/arguments
           [{:alumbra/argument-name "if"
             :alumbra/argument-value
             {:alumbra/value-type :boolean :alumbra/boolean false}}]}]
         :alumbra/selection-set
         [{:alumbra/field-name "enumValues"
           :alumbra/selection-set
           [{:alumbra/field-name "isDeprecated"}
            {:alumbra/field-name "description"}]}]}]}]}]})

(def document-str
  "query HAsS9tHg {
  __schema {
    mutationType @include(if: false) {
      name
      possibleTypes @include(if: true) {
        name
      }
    }
    types @include(if: true) {
      ofType @include(if: false) {
        interfaces {
          ofType @skip(if: false) {
            fields {
              args {
                name @include(if: true)
              }
              isDeprecated
              type @skip(if: false) {
                interfaces @skip(if: false) {
                  ofType {
                    interfaces @skip(if: false) {
                      enumValues @include(if: true) {
                        isDeprecated
                        description
                      }
                      possibleTypes @skip(if: true) {
                        enumValues {
                          description @skip(if: true)
                        }
                        kind
                      }
                    }
                  }
                  name @skip(if: false)
                  possibleTypes {
                    interfaces @include(if: false) {
                      inputFields {
                        type @include(if: false) {
                          ofType @include(if: true) {
                            kind @skip(if: true)
                            description @skip(if: true)
                          }
                          fields @include(if: false) {
                            args @include(if: true) {
                              name @skip(if: false)
                              defaultValue @skip(if: false)
                            }
                          }
                          description @include(if: false)
                        }
                      }
                      fields @skip(if: true) {
                        __typename @include(if: false)
                        isDeprecated @skip(if: true)
                      }
                    }
                    fields {
                      description @include(if: true)
                    }
                    description
                  }
                }
                description @include(if: false)
              }
            }
            possibleTypes @include(if: true) {
              interfaces @skip(if: true) {
                inputFields @skip(if: true) {
                  name @skip(if: true)
                }
                ofType @skip(if: true) {
                  inputFields @include(if: true) {
                    __typename
                    type {
                      interfaces @skip(if: false) {
                        kind @skip(if: true)
                        description @include(if: false)
                      }
                      name @include(if: true)
                      possibleTypes {
                        interfaces @skip(if: true) {
                          interfaces @include(if: false) {
                            fields {
                              isDeprecated @include(if: false)
                              type {
                                kind @skip(if: false)
                              }
                            }
                          }
                          enumValues @skip(if: true) {
                            name
                            deprecationReason @include(if: true)
                            description @include(if: false)
                          }
                          name
                        }
                        name @include(if: false)
                        possibleTypes @skip(if: false) {
                          interfaces @skip(if: false) {
                            enumValues @include(if: true) {
                              name
                              deprecationReason @include(if: false)
                            }
                            ofType @skip(if: false) {
                              ofType {
                                interfaces {
                                  interfaces @skip(if: true) {
                                    inputFields {
                                      type {
                                        possibleTypes @skip(if: true) {
                                          fields {
                                            deprecationReason @include(if: true)
                                            isDeprecated
                                          }
                                        }
                                        description @include(if: true)
                                      }
                                      defaultValue @skip(if: true)
                                      description
                                    }
                                    fields @skip(if: false) {
                                      deprecationReason
                                      description @include(if: false)
                                    }
                                    description @include(if: true)
                                  }
                                  name @include(if: false)
                                  description @include(if: true)
                                }
                                name @skip(if: true)
                                fields {
                                  name @skip(if: false)
                                  isDeprecated
                                  type {
                                    name @include(if: true)
                                    possibleTypes {
                                      enumValues(includeDeprecated: true) @include(if: false) {
                                        isDeprecated @skip(if: false)
                                      }
                                      name
                                      possibleTypes @include(if: false) {
                                        fields @skip(if: true) {
                                          deprecationReason @include(if: true)
                                          description
                                        }
                                      }
                                    }
                                    description @include(if: false)
                                  }
                                }
                              }
                              kind
                            }
                          }
                          kind
                          description @skip(if: false)
                        }
                      }
                    }
                  }
                }
              }
              enumValues {
                deprecationReason @skip(if: true)
                isDeprecated
              }
              fields {
                name
              }
            }
          }
          description
        }
        enumValues @skip(if: true) {
          name
          deprecationReason @include(if: false)
        }
      }
      __typename @include(if: false)
      fields @include(if: false) {
        args @skip(if: false) {
          type {
            ofType @include(if: true) {
              interfaces @include(if: false) {
                interfaces @skip(if: true) {
                  kind @include(if: false)
                }
                description
              }
              enumValues @include(if: false) {
                deprecationReason @skip(if: false)
              }
              kind
            }
          }
        }
        deprecationReason
        type {
          inputFields @skip(if: false) {
            defaultValue
            description
          }
        }
      }
    }
    subscriptionType @include(if: false) {
      enumValues {
        isDeprecated
        description
      }
    }
  }
}
")

(deftest test-pr-str
  (is (= document-str (printer/pr-str document-ast))))

(deftest test-print
  (is (= (printer/pr-str document-ast)
         (with-out-str (printer/print document-ast)))))
