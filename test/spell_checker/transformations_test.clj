(ns spell-checker.transformations-test
  (:use [spell-checker.transformations])
  (:require [clojure.test :refer :all]))

 (deftest test-deletion
   (testing "Testing word: bird"
     (let [r (deletion "bird")]
       (is (=
            '("ird" "brd" "bid" "bir")
            r)))))

(deftest test-transposition
  (testing "Testing word: house"
    (let [r (transposition "house")]
      (is (=
           '("ohuse" "huose" "hosue" "houes")
           r)))))

(deftest test-insertion
  (testing "Testing word: a"
    (let [r (insertion "a")]
      (is (=
           '("aa" "ba" "ca" "da" "ea" "fa" "ga" "ha" "ia" "ja" "ka"
             "la" "ma" "na" "oa" "pa" "qa" "ra" "sa" "ta" "ua" "va"
             "wa" "xa" "ya" "za")
           r)))))

(deftest test-alteration
  (testing "Testing word: ab"
    (let [r (alteration "ab")]
      (is (=
           '("ab" "bb" "cb" "db" "eb" "fb" "gb" "hb" "ib" "jb" "kb" "lb"
             "mb" "nb" "ob" "pb" "qb" "rb" "sb" "tb" "ub" "vb" "wb" "xb"
             "yb" "zb" "aa" "ab" "ac" "ad" "ae" "af" "ag" "ah" "ai" "aj"
             "ak" "al" "am" "an" "ao" "ap" "aq" "ar" "as" "at" "au" "av"
             "aw" "ax" "ay" "az")
           r)))))
