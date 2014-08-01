(ns scissors.core)

(def rules
  {["P" "R"] "P"
   ["P" "S"] "S"
   ["R" "S"] "R"
   ;; both players made the same choice
   ["P"] "P"
   ["R"] "R"
   ["S"] "S"})

(defn- normalize-input
  [players]
  (letfn [(extract [[player figure]]
            [figure [player figure]])]
    (->> players
         reverse
         (map extract)
         (into {}))))

(defn winner [& players]
  (let [players-map (normalize-input players)]
    (or (-> players-map
            keys
            sort
            rules
            players-map)
        (throw "RockPaperScissors"))))




