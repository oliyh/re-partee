(ns re-partee.views.typeahead-test
  (:require [re-partee.views.typeahead :refer [typeahead-component]]
            [devcards.core :refer-macros [defcard-rg]]))

(defcard-rg typeahead
  [:div
   [:h4 "Initial state"]
   [typeahead-component {}]

   [:h4 "Loading suggestions"]
   [typeahead-component {:query "Matching query"
                         :suggestions nil}]

   [:h4 "Some suggestions"]
   [typeahead-component {:query "Matching query"
                         :suggestions ["Foo" "Bar" "Baz"]}]])
