package org.example;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.example.parser.Qsl02Lexer;
import org.example.parser.Qsl02Parser;

import java.io.IOException;

public class Qsl02ParserMain {
    public static void main(String[] args) throws IOException {
        CharStream inputStream = CharStreams.fromFileName(
            "./parser-02/src/test/resources/examples/simple02.qsl");
        Qsl02Lexer Qsl02Lexer = new Qsl02Lexer(inputStream);
        CommonTokenStream commonTokenStream = new CommonTokenStream(Qsl02Lexer);
        Qsl02Parser Qsl02Parser = new Qsl02Parser(commonTokenStream);
        Qsl02Parser.ProgContext progContext = Qsl02Parser.prog();
        System.out.println(progContext);
    }
}