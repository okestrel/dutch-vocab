(ns adder.core)

(defn foo
  "I don't do a whole lot."
  [x]
  (println x "Hello, World!"))

(def testmap {})

(defn testf
  []
  (println (read-line)))


(assoc testmap :dutch_word "verdomme")

;interactive adding
(defn testf2
  []
  (do (println "dutch word?"))
  (assoc testmap :dutch_word (read-line)))


;assoc vs. update- update can take function as argument for updating

;within nested map, pass vector of keys
;(assoc-in gemstone-db [:ruby :properties :color] "Near colorless"

;permanent database reference

(def memory-db (atom {}))

(defn read-db [] @memory-db)

(defn write-db [new-db] (reset! memory-db new-db))

;create table "vocab"
(write-db (assoc (read-db) :vocab {:word {}}))
;table name vocab is inside map as key?


;insert new words and nested maps



;read and write to text file
(slurp "vocab.txt")
(spit "vocab.txt" "test 1\n" :append true)

(defn testf3
  []
  (do (println "dutch word?"))
  (spit "vocab.txt" (read-line) :append true)
  (spit "vocab.txt" "\n" :append true))

;working code begins here

(def vocab_lists_map (sorted-map
  1 "(mind/thought)"
   2 "(personal character/qualities)"
   3 "(situations/relations)"
   4 "(magic/fantasy)"
   5 "(impersonal qualities)"
   6 "(work/money)"
   7 "(change/discovery)"
   8 "(household)"
   9 "(strife/adversity)"
   10 "(personal/small objects)"
   11 "(relationships/collectives)"
   12 "(religion/spirituality)"
   13 "(time)"
   14 "(body/health/life)"
   15 "(crime and punishment)"
   16 "(chance/success)"
   17 "(communication)"
   18 "(places)"
   19 "(visual/attention)"
   20 "(art/entertainment)"
   21 "(text)"
   22 "(natural world)"
   23 "(truth/education)"
   24 "(quantity/presence)"
   25 "(negative/worsen)"
   26 "(positive/superlative/improve)"
   27 "(possession)"
   28 "(effort/giving)"
   29 "(food/drink)"
   30 "(moving/transportation)"
   31 "(sports/action)"
   32 "(common actions)"
   33 "(ability/potential)"
   34 "(emotions/reactions/provocations)"
   35 "(cause/effect)"
   36 "(colloquialisms)"
   37 "(abstract concepts)"
   38 "(power/society)"
  39 "(conjunctions/prepositions)"))

(def part_spch_map {
"noun" "(noun/pronoun)"
"vb" "(verb)"
"ad" "(adjective/adverb)"
"cp" "(conjunction/preposition)"})

(defn inserter
  []
  (let [dutch_word (do (println "Dutch word?")(read-line))
        word_freq (do (println "Word Frequency?")(read-line))
        eng_word (do (println "English translation(s)?")(read-line)) ;multiple entries separated by commas
        part_spch (do (println "Part(s) of Speech?" "\n" part_spch_map)(read-line)) ;multiple entries separated by commas
        de_het (do (println "De/Het?")(read-line))
        vocab_list (do (println "Vocab List(s)?" "\n" vocab_lists_map)(read-line))] ;multiple entries separated by commas
  (if (clojure.string/includes? eng_word ",")
    (def eng_words (clojure.string/split eng_word #", "))
    (def eng_words eng_word))
  (if (clojure.string/includes? part_spch ",")
    (def parts_spch (clojure.string/split part_spch #", "))
    (def parts_spch part_spch))
  (if (clojure.string/includes? vocab_list ",")
    (def vocab_lists (clojure.string/split vocab_list #", "))
    (def vocab_lists vocab_list))                          ;can condense this block with fn?
  (spit "vocab.txt" {dutch_word [word_freq eng_words parts_spch de_het vocab_lists]} :append true)
  (spit "vocab.txt" "\n" :append true)))

;format prettier print of vocab_lists
;confirm (assert) de/het entry (+others?- consistent capitalization)
;print parts of speech map after prompt: :noun "noun/pronoun", :vb "verb", :ad "adjective/adverb", :c/p "conjunction/preposition"
;branching prompts for vocab list (show lists)?

;if only one part of speech or vocab list given for two english translations, duplicates

;(flush)(read-line)?

(defn adder
  []
  (do (println "add word? (y?)") ;change to after word added, "add another?"
  (if (= (read-line) "y")
    (do (inserter) (adder)))))

;option to quit in middle if mistake made
;add println after read-line

;(read-string)/(load-string)

(def all_word_maps
  (apply merge {}
        (for [word_map (clojure.string/split (slurp "vocab.txt") #"\n")]
        {(first (keys (read-string word_map)))(first (vals (read-string word_map)))})))

 ;can use conj instead of merge?
 ;reads key (dutch word), value (translation vector)


;summary message ("x" loaded...)
;function to prompt for dutch word, print chosen value (translation, frequency... )

(((all_word_maps "gelijk") 1) 1)

(defn lookup1
  [dutch_word]
  (let [word (name 'dutch_word)]
    (println word)))

(defn lookup
  [dutch_word]
  (name dutch_word))

;(str
;(keyword

;way to enter not as string (or ' prefix)? or just use read-line prompt?

;(defn access


;lookup word- given word
;lookup word (freq, part of speech,...)- each functions that can be passed to lookup- 2-arity
;then addl. functions to lookup by (other than word)- return all that match category or range- passed functions above

;"what would you like to do?": add, lookup



;test uses lookup functions
