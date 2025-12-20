package org.example;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.example.parser.Qsl1BuildPrettyVisitor;
import org.example.parser.Qsl1Lexer;
import org.example.parser.Qsl1Parser;

import java.io.IOException;

public class Qsl1ParserMain2 {
    public static void main(String[] args) throws IOException {
        CharStream inputStream = CharStreams.fromFileName(
            "./parser-02/src/test/resources/examples/simple01.qsl");
        Qsl1Lexer qsl1Lexer = new Qsl1Lexer(inputStream);
        CommonTokenStream commonTokenStream = new CommonTokenStream(qsl1Lexer);
        Qsl1Parser qsl1Parser = new Qsl1Parser(commonTokenStream);
        Qsl1Parser.ProgContext progContext = qsl1Parser.prog();

        Qsl1BuildPrettyVisitor visitor = new Qsl1BuildPrettyVisitor();
        String result = visitor.visit(progContext);
        System.out.println("Result:\n" + result);
    }
}