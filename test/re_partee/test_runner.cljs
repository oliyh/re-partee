;; This test runner is intended to be run from the command line
(ns re-partee.test-runner
  (:require
   [re-partee.all-tests]
   [clojure.spec.test.alpha :as st]
   [figwheel.main.testing :refer [run-tests-async]]))

(defn -main [& args]
  (st/instrument)
  (run-tests-async 5000))
