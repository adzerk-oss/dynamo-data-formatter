(ns dynamo-data-formatter.core
  (:require [clojure.string :as str]))

(defn dynamo-field-data
  [type data]
  (format "{\"%s\":\"%s\"}" type (str/escape data {\" "\\\""})))

(defn dynamo-string [s]
  (dynamo-field-data "s" s))

(defn dynamo-number [n]
  (dynamo-field-data "n" (str n)))

(defn format-row
  "Per AWS docs, the beginning and end of data fields/columns are delimited by 
   Start of Text (STX/ASCII 02) and End of Text (ETX/ASCII 03) characters, and
   a single line feed (LF/ASCII 10) indicates the end of records.
   
   The first line, for whatever reason, doesn't need to (and perhaps may not?)
   start with STX.
   
   Each value is provided as a string resembling a JSON object with a single
   key-value pair, the key being the Dynamo content type (e.g. 's' for string)
   and the value being the value as a string, even if the value is a number
   (e.g. {'n': '200'}, and pretend those single quotes are double quotes).
   
   This library determines the appropriate content type based on the type of 
   value passed in. The `data-map` argument should be a mapping of column names
   to values."
  [row]
  (let [STX (char 2)
        ETX (char 3)
        LF  (char 10)
        fields (map (fn [[column value]]
                      (str column ETX (cond 
                                        (string? value) (dynamo-string value)
                                        (number? value) (dynamo-number value))))
                    row)]
    (str (str/join STX fields) LF)))

(defn format-rows
  [rows]
  (apply str (map format-row rows)))
