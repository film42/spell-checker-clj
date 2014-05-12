# Spell Checker in Clojure

This was an attempt to port the C++ / Java projects I made priviously into Clojure while fllying over the gulf of mexico. It doesn't include a Trie, though there are protocols defined to do so. Still, the slowest part is loading the database with slurp + doseq (fix coming).

## License

Copyright © 2014 Garrett T

Distributed under the Eclipse Public License, the same as Clojure.
