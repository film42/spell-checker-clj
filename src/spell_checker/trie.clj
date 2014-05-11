(ns spell-checker.trie)

;;
;; Trie: TBD
;; Do I need to use a Trie? A map seems very
;; powerful in that it has const time lookup.
;; Search could use some work maybe?
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defprotocol INode
  (word [this] "The word")
  (children [this] "Character children")
  (freq [this] "Number of times this word appears in dictionary list"))

(deftype Node
  [w c f]
  INode
  (word [this] w)
  (children [this] c)
  (freq [this] f))

(defprotocol ITrie
  (find [this word] "Look for word")
  (insert [this word] "Add and return updated Trie"))

(deftype Trie []
  ITrie
  (find [this word] nil)
  (insert [this word] nil))
