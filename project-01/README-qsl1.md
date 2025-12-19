project-01
==================

## Description

This project is to develop a Qsl1 parser. 

Qsl1 Values can be as follows

```java
sealed interface Qsl1Value { }

record Qsl1Id(String s) implements Qsl1Value {}
record Qsl1Text(String s) implements Qsl1Value {}
record Qsl1Number(String s) implements Qsl1Value {}
record Qsl1Null() implements Qsl1Value {}
record Qsl1Boolean(boolean b) implements Qsl1Value {}
record Qsl1List(List<Qsl1Value> values) implements Qsl1Value {}
record Qsl1Map(Map<Qsl1Id,Qsl1Value> pairs) implements Qsl1Value {}
```

The point of this project is to implement a parser 
for turning a Qsl1 document into a Tree of Qsl1Values. 

Optional extra point is to write a Qsl1 document 
from a Tree of Qsl1Values.


