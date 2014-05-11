(ns spell-checker.core
  (:use [spell-checker.interfaces])
  (:use [spell-checker.transformations])
  (:require [clojure.string :as s]))

(deftype Spelling
  [dict _ws]

  ISpelling
  ISpellingUtils

  (add [this words]
    (swap! _ws concat words)
    (reset! _ws (set @_ws)))

  (search [this]
    ;; I need to filter for trans words in the Dict, then sort-by
    ;; -value
    (let [res (filter #(contains? @_ws (key %)) @dict)
          word (first (sort-by last > res))]
      (if (nil? word)
        nil
        (key word))))

  (use-dictionary
    [this path]
    (let [d (slurp path)
          ws (->> (s/split d #"\n")
                     (map #(s/replace % #"\s" "")))]
      (doseq [word ws]
        (swap! dict #(if-not (nil? (% %2))
                          (conj % {%2 (inc (% %2))})
                          (conj % {%2 1})) word))))

  (suggest-word [this word]
    (if-not (nil? (@dict word))
      word
      ;; There is no word in dictionary, let's transform
      (do
        (add this (deletion word))
        (add this (transposition word))
        (add this (insertion word))
        (add this (alteration word))

        ;; Check again
        (let [res1 (search this)] 
             (if-not (nil? res1)
               res1
               (let [old @_ws]
                                               
                 (add this (apply concat (map deletion old)))
                 (add this (apply concat (map transposition old)))
                 (add this (apply concat (map insertion old)))
                 (add this (apply concat (map alteration old)))

                 ;; RET or no change
                 (let [res2 (search this)]
                   (if-not (nil? res2)
                     res2
                     word)))))))))

(defn checker [path]
  (let [_spll (->Spelling (atom {}) (atom #{}))]
    (use-dictionary _spll path)
    _spll))

;; Runner
(defn -main [& args]
  (let [c (checker (first args ))]
    (time (println
           (suggest-word c (nth args 1))))))
