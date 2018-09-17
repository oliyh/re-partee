(ns re-partee.fake.server
  (:require [re-frame.core :as re-frame])
  (:require-macros [re-partee.fake.macros :refer [defdata]]))

(defdata suggestions "suggestions.edn")

(re-frame/reg-event-fx
 ::fetch-suggestions
 (fn [_ [_ q callback]]
   (js/setTimeout
    (fn []
      (re-frame/dispatch
       (conj callback
             (let [re (re-pattern (str "(?i)^" q))]
               (filter #(re-find re %) suggestions)))))
    250)
   {}))
