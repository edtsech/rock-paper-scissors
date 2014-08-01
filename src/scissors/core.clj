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
    ;; the same as (more natural to read: top to down instead of right to left)
    ;;   (into {} (map extract (reverse players))
    (->> players
         reverse
         (map extract)
         (into {}))))

(defn winner
  [& players]
  (let [players-map (normalize-input players)
        combination (sort (keys players-map))
        winning-figure (rules combination)]
    (or (players-map winning-figure)
        (throw "RockPaperScissors"))))

(defn winner-> [& players]
  (let [players-map (normalize-input players)]
    (or (-> players-map
            keys
            sort
            rules
            players-map)
        (throw "RockPaperScissors"))))




