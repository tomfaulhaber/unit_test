
(use 'unit-test)

(defn do-test [ns regex]
  (if regex
    (exec-all-tests ns regex)
    (exec-all-tests ns)))

(System/exit
 (let [ns (symbol (first *command-line-args*))
       regex (second *command-line-args*)
       regex (when regex (re-pattern regex))]
   (require ns)
   (let [tresult (do-test ns regex)]
     (show-test-result tresult)
     (flush)
     (+ (count (:failures tresult)) (count (:errors tresult))))))

