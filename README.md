# alumbra.printer

This library contains a pretty printer for the Alumbra AST.

[![Build Status](https://travis-ci.org/alumbra/alumbra.printer.svg?branch=master)](https://travis-ci.org/alumbra/alumbra.printer)
[![Clojars Project](https://img.shields.io/clojars/v/alumbra/printer.svg)](https://clojars.org/alumbra/printer)

## Usage

```clojure
(require '[alumbra.parser :as parser])
(require '[alumbra.printer :as printer])

(def my-document
  (parser/parse-document "{ human(id: \"1000\") { name height(unit: FOOT) } }"))

;; Without indentation

(printer/print my-document {:indentation 0})
;;=> query { human(id: "1000") { name height(unit: FOOT) } }

;; With an indentation of 2 spaces

(printer/print my-document {:indentation 2})
;;=> query {
;;=>   human(id: "1000") {
;;=>     name
;;=>     height(unit: FOOT)
;;=>   }
;;=> }
```

## License

```
MIT License

Copyright (c) 2018 Roman Scherer

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```
