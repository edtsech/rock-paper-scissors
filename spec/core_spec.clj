(ns core-spec
  (:use speclj.core
        scissors.core))

(describe "winner"
          (it "raises an error for invalid strategies"
              (should-throw (winner ["Michael" "X"] ["Ing" "R"])))
          (it "returns :tie when both players made the same choice"
              (should= ["Michael" "R"]
                       (winner ["Michael" "R"] ["Ing" "R"])))
          (it "picks rock over scissors"
              (should= ["Michael", "R"]
                       (winner ["Michael" "R"] ["Ing" "S"])))
          (it "pick scissors over paper"
              (should= ["Michael", "S"]
                       (winner ["Michael" "S"] ["Ing" "P"])))
          (it "picks paper over rock"
              (should= ["Michael", "P"]
                       (winner ["Michael" "P"] ["Ing" "R"]))
              ))

(describe "normalize-input"
  (it "converts players vectors to map"
      (should== {"P" ["Michael" "P"] "R" ["Ing" "R"]}
                (#'scissors.core/normalize-input [["Michael" "P"] ["Ing" "R"]])))
  (it "returns only one player when both players made the same choice"
      (should== {"R" ["Michael" "R"]}
                (#'scissors.core/normalize-input [["Michael" "R"] ["Ing" "R"]]))))
