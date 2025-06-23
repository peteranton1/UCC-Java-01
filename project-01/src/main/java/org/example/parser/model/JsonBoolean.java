package org.example.parser.model;

import lombok.Builder;

@Builder
public record JsonBoolean(boolean b) implements JsonValue {
}
