(ns re-partee.fake.macros
  #?(:clj (:require [clojure.edn :as edn]
                    [clojure.java.io :as io])))

#?(:clj (defmacro defdata [sym resource-name]
          (let [data (edn/read-string (slurp (io/resource resource-name)))]
            `(def ~sym ~data))))
