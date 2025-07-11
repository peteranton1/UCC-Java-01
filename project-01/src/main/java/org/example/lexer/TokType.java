package org.example.lexer;

import lombok.Getter;

import java.util.regex.Pattern;

/**
 *  <code>
        (quoted_string> ::= (quote> ( (unescaped_char> | (escaped_char> )+ (quote>

        (escaped_char> ::= (escape> ( "\"" | "/" | "b" | "f" | "n" | "r" | "t"
                        | (unicode> | (escape> )
        (escaped_literal> ::= (escaped_char> | (escape> "`"
        (unescaped_char> ::= (digit> | (letter> | " " | "!" | "#" | "$" | "%"
                        | "&" | "'" | "(" | ")" | "*+" | "," | "-" | "." | "/"
                        | ":" | ";" | "<" | ">" | "?" | "@" | "[" | "]" | "^"
                        | "_" | "`" | "{" | "|" | "}" | "~"
        (unescaped_literal> ::= (digit> | (letter> | " " | "!" | "#" | "$" | "%"
                        | "&" | "'" | "(" | ")" | "*+" | "," | "-" | "." | "/"
                        | ":" | ";" | "<" | ">" | "?" | "@" | "[" | "]" | "^"
                        | "_" | "{" | "|" | "}" | "~"

        (unicode> ::= "u" (digit> (digit> (digit> (digit>

        (escape> ::= "\\"
        (digit> ::= [0-9]
        (letter> ::= [A-Z] | [a-z] | "_"
        (quote> ::= "\""

        // The ``json-value`` is any valid JSON value with the one exception that the
        // ``%x60`` character must be escaped.  While it's encouraged that implementations
        // use any existing JSON parser for this grammar rule (after handling the escaped
        // literal characters), the grammar rule is shown below for completeness::

         (json_value> ::= (json_array>
          | (json_boolean>
          | (json_null>
          | (json_number>
          | (json_object>
          | (json_string>

         (json_null> ::= "null"
         (json_boolean> ::= "true" | "false"
         (json_number> ::= "-"? ( "0" | [1-9] [0-9]* ) ( "." [0-9]+ )? ( "e" ( "-" | "+" )
                        [0-9]+ )?
         (json_array> ::= (ws> "[" ( (ws> (json_value> (ws>
                        ( "," (ws> (json_value> (ws> )* )? "]" (ws>
         (json_object> ::=  (ws> "{" (ws> ( (member> (ws> ( "," (ws>
                        (member> (ws> )* )? "}" (ws>
         (json_string> ::= (quote> ( (unescaped_literal>
                            | (escaped_literal> )* (quote>

         (member> ::= (quoted_string> (ws> ":" (ws> (json_value>
         (ws> ::= " "*

    </code>
    */

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
