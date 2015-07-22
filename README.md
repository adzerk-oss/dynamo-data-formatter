# dynamo-data-formatter

A little helper library for the niche purpose of formatting data so that it can be imported into DynamoDB via AWS Data Pipeline. In order to import data into Dynamo, AWS requires that the data be formatted [in kind of a strange way](http://docs.aws.amazon.com/datapipeline/latest/DeveloperGuide/dp-importexport-ddb-pipelinejson-verifydata2.html), making the importing process not the most intuitive.
If you're exporting your data from another existing Dynamo table, then it's already in this weird, control character-peppered format, but often, this isn't the case. You might want to import a bunch of new data, say from a spreadsheet, into Dynamo. This library can help you generate the file that Dynamo needs, in the correct format for importing into Dynamo.

## Usage

*TODO*

## TODO

(PRs warmly welcomed.)

* Support more Dynamo value types. Currently only number and string fields are supported. 

## License

Copyright © 2015 Adzerk

Distributed under the Eclipse Public License version 1.0. 
