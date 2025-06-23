package org.example.parser.model;

import lombok.Builder;

import java.util.List;

@Builder
public record JsonArray(List<JsonValue> values) implements JsonValue {
}
