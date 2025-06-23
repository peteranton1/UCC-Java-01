package org.example;

import org.antlr.v4.runtime.*;
import org.example.parser.*;

public class JsonParserMain {
    public static void main(String[] args) {
        CharStream inputStream = CharStreams.fromString(
            """
                11 + 22 - 33
                """);
        JsonLexer jsonLexer = new JsonLexer(inputStream);
        CommonTokenStream commonTokenStream = new CommonTokenStream(jsonLexer);
        JsonParser jsonParser = new JsonParser(commonTokenStream);
        JsonParser.FileStmtContext fileContext = jsonParser.fileStmt();
        JsonBaseVisitor<JsonParser.FileStmtContext> visitor = new JsonBaseVisitor<>();
        visitor.visit(fileContext);
    }
}