(ns re-partee.views.typeahead
  (:require [re-partee.models.typeahead :as model]
            [re-frame.core :as re-frame]))

(defn- suggestions []
  (let [suggestions (re-frame/subscribe [::model/suggestions])]
    (fn []
      (when (seq @suggestions)
        [:div.suggestions "Suggestions"
         [:ul
          (doall
           (for [suggestion @suggestions]
             ^{:key suggestion}
             [:li suggestion]))]]))))

(defn typeahead []
  (let [query (re-frame/subscribe [::model/query])]
    (fn []
      [:div.typeahead
       [:input {:type "text"
                :value @query
                :placeholder "Start typing to see suggestions"
                :on-change #(re-frame/dispatch [::model/on-query (.. % -target -value)])}]
       [suggestions]])))
