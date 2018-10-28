(ns re-partee.views.typeahead-test
  (:require [re-partee.views.typeahead :refer [typeahead-component]]
            [devcards.core :refer-macros [defcard-rg]]))

(defcard-rg typeahead
  [:div
   [:h1 [:i "Initial state"]]
   [typeahead-component {}]

   [:h1 [:i "Loading suggestions"]]
   [typeahead-component {:query "Matching query"
                         :suggestions {:loading? true}}]

   [:h1 [:i "Some suggestions"]]
   [typeahead-component {:query "Matching query"
                         :suggestions {:loading? false
                                       :suggestions ["Foo" "Bar" "Baz"]}}]

   [:h1 [:i "No suggestions"]]
   [typeahead-component {:query "Matching query"
                         :suggestions {:loading? false
                                       :suggestions []}}]])
