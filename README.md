# dynamo-data-formatter

[![Clojars Project](http://clojars.org/dynamo-data-formatter/latest-version.svg)](http://clojars.org/dynamo-data-formatter)

A little helper library for the niche purpose of formatting data so that it can be imported into DynamoDB via AWS Data Pipeline. In order to import data into Dynamo, AWS requires that the data be formatted [in kind of a strange way](http://docs.aws.amazon.com/datapipeline/latest/DeveloperGuide/dp-importexport-ddb-pipelinejson-verifydata2.html), making the importing process not the most intuitive.
If you're exporting your data from another existing Dynamo table, then it's already in this weird, control character-peppered format, but often, this isn't the case. You might want to import a bunch of new data, say from a spreadsheet, into Dynamo. This library can help you generate the file that Dynamo needs, in the correct format for importing into Dynamo.

## Usage

Here is an example of a [Boot](http://www.boot-clj.com) script one could write in order to read some data from an Excel spreadsheet using [docjure](https://github.com/mjul/docjure) and then format it as Dynamo data.

```clj
#!/usr/bin/env boot

(set-env! :dependencies
  '[[dk.ative/docjure      "1.8.0"]
    [dynamo-data-formatter "0.1.1"]])

(require '[dk.ative.docjure.spreadsheet :refer :all]
         '[dynamo-data-formatter.core   :refer (format-rows)])

(defn records [filename]
  (->> (load-workbook filename)
       (select-sheet "Sheet1")
       (select-columns {:A "UserKey" :B "NetworkId" :C "Impressions"})
       rest)) ; discards the title row

(defn -main [filename]
  (println "Converting spreadsheet data...")
  (let [formatted (format-rows (records filename))
        out-file  (str filename ".out")]
    (spit out-file formatted)
    (println "Formatted file written to" (str out-file \.))))
```
## TODO

(PRs warmly welcomed.)

* Support more Dynamo value types. Currently only number and string fields are supported. 
* Add optional validation that there are no duplicate keys, as AWS will choke on the file if this is the case. Should throw an exception in this scenario. (The user will need to specify which fields are the keys.)

## License

Copyright © 2015 Adzerk

Distributed under the Eclipse Public License version 1.0. 
