package org.example.parser;

import org.example.lexer.JsonLexer;

public class JsonParser {

    private final JsonLexer lexer;

    /**
     * <p>eBNF definition of the JSON language.</p>
     *
     * <code>
     *     singleValue ::=
     *                  objectStmt pairList
     *              |   arrayStmt
     *              ;
     *     namedValue ::=
     *                  IDENT ':'
     *                  (objectStmt
     *              |   pairList)
     *              ;
     *
     *     objectStmt ::=
     *                  '{' valueList  '}'
     *              ;
     *     arrayStmt ::=
     *                  '[' objectList ']'
     *              ;
     *     objectList ::=
     *              |   objectStmt (',' objectStmt)*
     *              ;
     *     pairList ::=
     *              |   (pairExpr (',' pairExpr)* )?
     *
     * </code>
     */
    public JsonParser(JsonLexer lexer) {
        this.lexer = lexer;
    }
}
