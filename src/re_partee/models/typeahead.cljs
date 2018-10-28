(ns re-partee.models.typeahead
  (:require [re-frame.core :as re-frame]
            [re-partee.fake.server :as server]
            [clojure.string :as string]))

(re-frame/reg-event-db
 ::on-suggestions
 (fn [db [_ suggestions]]
   (assoc-in db [:typeahead :suggestions] {:loading? false
                                           :suggestions suggestions})))

(re-frame/reg-event-fx
 ::on-query
 (fn [{:keys [db]} [_ q]]
   (merge
    {:db (-> db
             (assoc-in [:typeahead :query] q)
             (assoc-in [:typeahead :suggestions] {:loading? true}))}
    (if (string/blank? q)
      {:dispatch [::on-suggestions []]}
      {:dispatch [::server/fetch-suggestions q [::on-suggestions]]}))))

(re-frame/reg-event-db
 ::on-choose
 (fn [db [_ suggestion]]
   (assoc-in db [:typeahead] {:query suggestion})))

(re-frame/reg-sub
 ::suggestions
 (fn [db [_ query]]
   (get-in db [:typeahead :suggestions])))

(re-frame/reg-sub
 ::query
 (fn [db [_ query]]
   (get-in db [:typeahead :query])))

(re-frame/reg-sub
 ::typeahead
 (fn []
   [(re-frame/subscribe [::query])
    (re-frame/subscribe [::suggestions])])
 (fn [[query suggestions]]
   {:query query
    :suggestions suggestions}))
