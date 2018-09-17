(defproject re-partee "0.1.0-SNAPSHOT"
  :description "How I like to structure UIs"
  :url "https://github.com/oliyh/re-partee"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}

  :min-lein-version "2.7.1"

  :dependencies [[org.clojure/clojure "1.10.0-alpha6"]
                 [org.clojure/clojurescript "1.10.339"]
                 [re-frame "0.10.5"]
                 [reagent "0.8.1"]]

  :source-paths ["src"]

  :aliases {"fig"       ["trampoline" "run" "-m" "figwheel.main"]
            "fig:build" ["trampoline" "run" "-m" "figwheel.main" "-b" "dev" "-r"]
            "fig:min"   ["run" "-m" "figwheel.main" "-O" "advanced" "-bo" "dev"]
            "fig:test"  ["run" "-m" "figwheel.main" "-co" "test.cljs.edn" "-m" re-partee.test-runner]}

  :profiles {:dev {:dependencies [[com.bhauman/figwheel-main "0.1.9"]
                                  [com.bhauman/rebel-readline-cljs "0.1.4"]
                                  [binaryage/devtools "0.8.3"]
                                  [devcards "0.2.6"]
                                  [day8.re-frame/test "0.1.3"]]
                   :source-paths ["dev"]
                   :resource-paths ["target"]
                   ;; need to add the compliled assets to the :clean-targets
                   :clean-targets ^{:protect false} ["target"]}})
