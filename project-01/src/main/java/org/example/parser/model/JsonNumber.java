package org.example.parser.model;

import lombok.Builder;

@Builder
public record JsonNumber(String bigDecimal) implements JsonValue {
}
