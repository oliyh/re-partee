(ns re-partee.models.typeahead-test
  (:require [re-partee.models.typeahead :as model]
            [re-partee.specs.typeahead :as spec]
            [re-partee.fake.server :as server]
            [re-frame.core :as re-frame]
            [day8.re-frame.test :refer [run-test-sync]]
            [cljs.test :refer [use-fixtures] :refer-macros [testing is]]
            [devcards.core :refer-macros [deftest]]
            [clojure.spec.alpha :as s]))

(deftest initial-state-test
  (run-test-sync
   (let [t (re-frame/subscribe [::model/typeahead])]

     (testing "initial state"
       (is (= {:query nil
               :suggestions nil} @t))
       (is (s/valid? ::spec/component @t))))))

(defn- stub-server-response [expected-request response]
  (re-frame/reg-event-fx
   ::server/fetch-suggestions
   (fn [_ [_ q callback]]
     (is (= expected-request q))
     {:dispatch (conj callback response)})))

(deftest querying-test
  (run-test-sync
   (stub-server-response "foo" ["bar"])

   (let [t (re-frame/subscribe [::model/typeahead])]
     (testing "user types something"
       (re-frame/dispatch [::model/on-query "foo"])

       (is (= {:query "foo"
               :suggestions {:loading? false
                             :values ["bar"]}} @t))
       (is (s/valid? ::spec/component @t))))))

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
               :suggestions {:loading? true}} @t))
       (is (s/valid? ::spec/component @t))))))
