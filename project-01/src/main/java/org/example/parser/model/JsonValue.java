package org.example.parser.model;

public sealed interface JsonValue permits
    JsonString, JsonNumber, JsonNull, JsonBoolean, JsonArray, JsonObject {
}
