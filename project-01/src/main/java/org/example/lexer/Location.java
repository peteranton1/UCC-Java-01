package org.example.lexer;

import lombok.Builder;

@Builder
public record Location(int rowPos, int colPos) {
}
