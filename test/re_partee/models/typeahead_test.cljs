(ns re-partee.models.typeahead-test
  (:require [re-partee.models.typeahead :as model]
            [re-frame.core :as re-frame]
            [day8.re-frame.test :refer [run-test-sync]]
            [cljs.test :refer-macros [testing is]]
            [devcards.core :refer-macros [deftest]]))

(deftest foo-test
  (testing "hi"
    (is (= true true))))
