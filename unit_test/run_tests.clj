(ns unit-test.run-tests
  (:gen-class
   :main true)
  (:use unit-test))

(defn do-test [ns regex]
  (if regex
    (exec-all-tests ns regex)
    (exec-all-tests ns)))

(defn- -main
  ([ns-arg] (-main ns-arg nil))
  ([ns-arg regex-arg]
     (System/exit
      (let [ns (symbol ns-arg)
	    regex (when regex-arg (re-pattern regex-arg))]
	(require ns)
	(let [tresult (do-test ns regex)]
	  (show-test-result tresult)
	  (flush)
	  (+ (count (:failures tresult)) (count (:errors tresult))))))))



