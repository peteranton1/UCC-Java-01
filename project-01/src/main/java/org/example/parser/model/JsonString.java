package org.example.parser.model;

import lombok.Builder;

@Builder
public record JsonString(String s) implements JsonValue {
}
