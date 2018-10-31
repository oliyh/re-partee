(ns re-partee.views.typeahead
  (:require [re-partee.models.typeahead :as model]
            [re-partee.specs.typeahead :as spec]
            [clojure.spec.alpha :as s]
            [re-frame.core :as re-frame]))

(defn- suggestions [{:keys [loading? values] :as any?}]
  (when any?
    [:div.suggestions
     (if loading?
       [:div.loading "Loading suggestions..."]
       (if (seq values)
         [:ul
          (map-indexed
           (fn [i v]
             [:li {:key (str i v)
                   :on-click #(re-frame/dispatch [::model/on-choose v])} v])
           values)]
         "No suggestions found, please try another search"))]))

(defn typeahead-component [model]
  [:div.typeahead
   [:input {:type "text"
            :value (:query model)
            :placeholder "Start typing to see suggestions"
            :on-change #(re-frame/dispatch [::model/on-query (.. % -target -value)])}]
   [suggestions (:suggestions model)]])

(s/fdef typeahead-component
        :args (s/cat :model ::spec/component))

(defn typeahead []
  (let [model (re-frame/subscribe [::model/typeahead])]
    (fn []
      [typeahead-component @model])))
