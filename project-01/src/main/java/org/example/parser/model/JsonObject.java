package org.example.parser.model;

import lombok.Builder;

import java.util.Map;

@Builder
public record JsonObject(Map<String, JsonValue> pairs) implements JsonValue {
}
