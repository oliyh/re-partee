(ns re-partee.specs.typeahead
  (:require [clojure.spec.alpha :as s]))

(s/def ::query (s/nilable string?))
(s/def ::loading? boolean?)
(s/def ::value string?)
(s/def ::values (s/coll-of ::value))
(s/def ::suggestions (s/nilable (s/keys :req-un [::loading?]
                                        :opt-un [::values])))

(s/def ::component
  (s/keys :req-un [::query ::suggestions]))
