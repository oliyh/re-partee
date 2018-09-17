(ns re-partee.view
  (:require [re-partee.views.typeahead :as typeahead]))

(defn home []
  [:div
   [:h1 "Typeahead"]
   [typeahead/typeahead]])
