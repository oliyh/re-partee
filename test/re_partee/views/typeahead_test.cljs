(ns re-partee.views.typeahead-test
  (:require [re-partee.views.typeahead :refer [typeahead-component]]
            [re-partee.specs.typeahead :as spec]
            [clojure.spec.alpha :as s]
            [clojure.spec.gen.alpha :as gen]
            [devcards.core :refer-macros [defcard-rg]]))

(defn- typeahead* [m]
  (if (s/valid? ::spec/component m)
    [typeahead-component m]
    [:div.error
     [:h4 "Bad input"]
     [:pre [:code (s/explain-str ::spec/component m)]]]))

(defcard-rg typeahead
  [:div
   [:h1 [:i "Initial state"]]
   [typeahead* {:query nil
                :suggestions nil}]

   [:h1 [:i "Loading suggestions"]]
   [typeahead* {:query "my computer is a"
                :suggestions {:loading? true}}]

   [:h1 [:i "Some suggestions"]]
   [typeahead* {:query "my computer is a"
                :suggestions {:loading? false
                              :values ["My computer is acting weird" "My computer is a potato"]}}]

   [:h1 [:i "No suggestions"]]
   [typeahead* {:query "bz"
                :suggestions {:loading? false
                              :values []}}]])

(defcard-rg generated-typeahead
  (let [models (gen/sample (s/gen ::spec/component))]
    [:div
     (map-indexed (fn [i m]
            [:div {:key i}
             [:pre (pr-str m)]
             [typeahead* m]])
          models)]))
