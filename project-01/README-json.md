project-01
==================

## Description

This project is to develop a JSON parser. 

JSON Values can be as follows

```java
sealed interface JsonValue permits 
    JsonString { }

record JsonString(String s) implements JsonValue {}
record JsonNumber(double d) implements JsonValue {}
record JsonNull() implements JsonValue {}
record JsonBoolean(boolean b) implements JsonValue {}
record JsonArray(List<JsonValue> values) implements JsonValue {}
record JsonObject(Map<String,JsonValue> pairs) implements JsonValue {}
```

The point of this project is to implement a parser for turning a Json document into a Tree of JsonValues. 

Optional extra point is to write a Json document from a Tree of JsonValues.


