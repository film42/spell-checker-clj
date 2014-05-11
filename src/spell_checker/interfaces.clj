(ns spell-checker.interfaces)

(defprotocol ISpelling
  (use-dictionary [this path] "Load dictionary from file")
  (suggest-word [this word] "Search through dictionary for word"))

(defprotocol ISpellingUtils
  (add [this words] "Add words here until Trie comes")
  (search [this] "Look for word"))
