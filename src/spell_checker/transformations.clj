(ns spell-checker.transformations)

;; Transformations Impl

(defn deletion [word]
  (for [i (range (count word))]
    (let [f (subs word 0 i)
          t (subs word (inc i))]
      (str f t))))

(defn transposition [word]
  (for [i (range (dec (count word)))]
    (let [l1 (subs word i (inc i))
          l2 (subs word (inc i) (+ 2 i))
          f (subs word 0 i)
          t (subs word (+ 2 i))]
      (str f l2 l1 t))))

(defn alteration [word]
  (apply concat (for [i (range (count word))]
                  (let [f (subs word 0 i)
                        t (subs word (inc i))]
                    (for [l (range 97 (+ 97 26))]
                      (str f (char l) t))))))

(defn insertion [word]
  (apply concat (for [i (range (count word))]
                  (let [f (subs word 0 i)
                        t (subs word i)]
                    (for [l (range 97 (+ 97 26))]
                      (str f (char l) t))))))
