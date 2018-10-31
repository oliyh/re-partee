(ns re-partee.cards
  (:require
   [devcards.core]
   [clojure.spec.test.alpha :as st]
   [re-partee.all-tests]))

(st/instrument)

(devcards.core/start-devcard-ui!)
