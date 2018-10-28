(ns re-partee.models.typeahead-test
  (:require [re-partee.models.typeahead :as model]
            [re-partee.fake.server :as server]
            [re-frame.core :as re-frame]
            [day8.re-frame.test :refer [run-test-sync]]
            [cljs.test :refer [use-fixtures] :refer-macros [testing is]]
            [devcards.core :refer-macros [deftest]]))

(deftest initial-state-test
  (run-test-sync
   (let [t (re-frame/subscribe [::model/typeahead])]

     (testing "initial state"
       (is (= {:query nil
               :suggestions nil} @t))))))

(deftest querying-test
  (run-test-sync

   (re-frame/reg-event-fx
    ::server/fetch-suggestions
    (fn [_ [_ q callback]]
      (is (= "foo" q))
      {:dispatch (conj callback ["bar"])}))

   (let [t (re-frame/subscribe [::model/typeahead])]
     (testing "user types something"

       (re-frame/dispatch [::model/on-query "foo"])

       (is (= {:query "foo"
               :suggestions {:loading? false
                             :suggestions ["bar"]}} @t))))))

(deftest loading-suggestions-test
  (run-test-sync

   (re-frame/reg-event-fx
    ::server/fetch-suggestions
    (fn [_ [_ q callback]]
      (is (= "foo" q))
      ;; do not dispatch callback in order to observe the state before the server has returned a response
      {}))

   (let [t (re-frame/subscribe [::model/typeahead])]
     (testing "user types something"

       (re-frame/dispatch [::model/on-query "foo"])

       (is (= {:query "foo"
               :suggestions {:loading? true}} @t))))))
