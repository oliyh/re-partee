(ns re-partee.views.typeahead
  (:require [re-partee.models.typeahead :as model]
            [re-frame.core :as re-frame]))

(defn- suggestions [suggestions]
  (when (seq suggestions)
    [:div.suggestions "Suggestions"
     [:ul
      (doall
       (for [suggestion suggestions]
         ^{:key suggestion}
         [:li suggestion]))]]))

(defn typeahead-component [model]
  [:div.typeahead
   [:input {:type "text"
            :value (:query model)
            :placeholder "Start typing to see suggestions"
            :on-change #(re-frame/dispatch [::model/on-query (.. % -target -value)])}]
   [suggestions (:suggestions model)]])

(defn typeahead []
  (let [model (re-frame/subscribe [::model/typeahead])]
    (fn []
      [typeahead-component @model])))
