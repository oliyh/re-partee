(ns re-partee.views.typeahead
  (:require [re-partee.models.typeahead :as model]
            [re-frame.core :as re-frame]))

(defn- suggestions [{:keys [loading? suggestions] :as any?}]
  (when any?
    [:div.suggestions
     (if loading?
       [:div.loading "Loading suggestions..."]
       (if (seq suggestions)
         [:ul
          (doall
           (for [suggestion suggestions]
             ^{:key suggestion}
             [:li {:on-click #(re-frame/dispatch [::model/on-choose suggestion])} suggestion]))]
         "No suggestions found, please try another search"))]))

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
