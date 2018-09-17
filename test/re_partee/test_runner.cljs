;; This test runner is intended to be run from the command line
(ns re-partee.test-runner
  (:require
    [re-partee.all-tests]
    [figwheel.main.testing :refer [run-tests-async]]))

(defn -main [& args]
  (run-tests-async 5000))
