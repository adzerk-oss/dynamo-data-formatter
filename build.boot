(set-env! 
  :source-paths #{"src"}
  :dependencies '[[org.clojure/clojure "1.7.0"]
                  [adzerk/bootlaces "0.1.11" :scope "test"]])

(require '[adzerk.bootlaces :refer :all])

(def +version+ "0.1.0")
(bootlaces! +version+)

(task-options!
  pom {:project 'dynamo-data-formatter
       :version +version+
       :description "a tool for formatting data to be imported into DynamoDB"
       :url "https://github.com/adzerk-oss/dynamo-data-formatter"
       :scm {:url "https://github.com/adzerk-oss/dynamo-data-formatter"}
       :license {"name" "Eclipse Public License"
                 "url"  "http://www.eclipse.org/legal/epl-v10.html"}})

