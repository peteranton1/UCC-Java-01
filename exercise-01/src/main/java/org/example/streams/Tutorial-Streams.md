Streams Tutorial
===============

## What are Streams?

Streams bring functional programming to java. They are supported starting in Java 8. 

## Advantages of Streams

* Will make you a more efficient programmer
* Make heavy use of lambda expressions
* Parallel Streams make it easy to do multi-thread operations
* Promotes good practices

A Stream pipeline consists of a source, followed 
by zero or more intermediate operations, 
and a terminal operation. 

Latest java has stream gatherers. 

 ## Typical pipeline of a stream:

    Source
    -> Filter
    -> Sort
    -> Map
    -> FlatMap
    -> Collect

### Stream Source

Streams can be created from Collections, Lists, Sets,
ints, longs, doubles, lines of a file, etc. 

### Stream operations

Stream operations are either intermediate or terminal.

* **intermediate operations** are methods such as filter, map, or sort, which return
* a new stream in which we can chain multiple intermediate operations. 
* **terminal operations** such as forEach, collect, reduce etc are either void
* or return a non-stream result.

## Intermediate Operations

Intermediate operations may occur after a stream is defined. 

Zero or more intermediate operations are allowed.

Order matters for large datasets. 

Therefore **filter first** then sort, then map, if possible. 

For very large datasets use ParallelStream to enable multi-threading.

Intermediate operations include the following:

| operation   | meaning                                                                         |
|-------------|---------------------------------------------------------------------------------|
| anyMatch()  | Return any sengle item from the stream that matches the supplied predicate.     |
| distinct()  | Return a stream with only the elements that are unique given a criteria         |
| filter()    | reduce the stream to only the elements matching the filter condition            |
| findFirst() | Select the first item in the stream that matches the criteria supplied          |
| flatMap()   | Same as map() but reduce the nesting to one functor                             |
| map()       | Perform an operation on each item that may change the item into some other item |
| skip()      | skip a certain number of items in the stream                                    |
| sorted()    | sort the stream according to the supplied sorting algorith or natural sorting.  |

## Terminal Operations 

Only one terminal operation is allowed. 

forEach applies the same function to each element in the stream. 
collect saves the elements into a collection. 
other options reduce the stream to a single summary element. 

| operation | meaning |
|------------|---------|
| count() | Return a count of the elements that match the predicate or all items |
| max() | Return a number representing the maximum numeric value in the stream |
| min() | Return a number representing the minimum value in the stream. |
| reduce() | Convert the stream of elemnts into a single element by performing a reduce operation across all the elements. |
| summaryStatistics() | Produce a summary of the above operations. |





