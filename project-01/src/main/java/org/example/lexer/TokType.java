package org.example.lexer;

import lombok.Getter;

import java.util.regex.Pattern;

@Getter
public enum TokType {
    A_STRING("^\\\"[^\\\"]*\\\""),
    A_NUMBER("^[0-9]+(\\.([0-9]+))?"),
    B_LBRACE("^\\{"),
    B_RBRACE("^\\}"),
    B_COLON("^\\:"),
    B_COMMA("^\\,"),
    C_COMMENT("^//[^\\\n]*[\\\n]?"),
    C_WS("^[ \\\t\\\r\\\n]*"),
    D_EOF("EOF"),
        ;

    final String regex;
    final Pattern pattern;

    TokType(String regex) {
        this.regex = regex;
        this.pattern = Pattern.compile(regex);
    }
}
