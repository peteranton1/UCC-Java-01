package org.example;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.example.parser.MathBaseVisitor;
import org.example.parser.MathLexer;
import org.example.parser.MathParser;

import java.io.IOException;

public class MathParserMain {
    public static void main(String[] args) throws IOException {
        CharStream inputStream = CharStreams.fromFileName(
            "./parser-02/src/main/resources/math/math.txt");
        MathLexer jsonLexer = new MathLexer(inputStream);
        CommonTokenStream commonTokenStream = new CommonTokenStream(jsonLexer);
        MathParser jsonParser = new MathParser(commonTokenStream);
        MathParser.ProgContext fileContext = jsonParser.prog();
        PrettyMathVisitor visitor = new PrettyMathVisitor();
        String str = visitor.visit(fileContext);
        System.out.println(str);
    }
}