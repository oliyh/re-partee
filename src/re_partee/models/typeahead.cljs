(ns re-partee.models.typeahead
  (:require [re-frame.core :as re-frame]
            [re-partee.fake.server :as server]
            [clojure.string :as string]))

(re-frame/reg-event-db
 ::on-suggestions
 (fn [db [_ suggestions]]
   (assoc-in db [:typeahead :suggestions] suggestions)))

(re-frame/reg-event-fx
 ::on-query
 (fn [{:keys [db]} [_ q]]
   (merge
    {:db (assoc-in db [:typeahead :query] q)}
    (if (string/blank? q)
      {:dispatch [::on-suggestions []]}
      {:dispatch [::server/fetch-suggestions q [::on-suggestions]]}))))

(re-frame/reg-sub
 ::suggestions
 (fn [db [_ query]]
   (get-in db [:typeahead :suggestions])))

(re-frame/reg-sub
 ::query
 (fn [db [_ query]]
   (get-in db [:typeahead :query])))

@re-frame.db/app-db
