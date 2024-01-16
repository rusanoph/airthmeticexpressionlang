package ru.rusanoph;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;

import ru.rusanoph.grammar.parser.ExprLexer;
import ru.rusanoph.grammar.parser.ExprParser;

public class App 
{
    public static void main( String[] args ) throws IOException
    {
        for (String arg : args) System.out.println(arg);

        String inputFile = null;
        if (args.length > 0) inputFile = args[0];
        InputStream is = System.in;

        if ( inputFile != null ) is = new FileInputStream(inputFile);

        CharStream input = CharStreams.fromStream(is);

        Calc calc = new Calc();
        calc.printEval(input);
    }

    public static void parse(CharStream input) {
        ExprLexer lexer = new ExprLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        ExprParser parser = new ExprParser(tokens);
        ParseTree tree = parser.prog();

        System.out.println(tree.toStringTree(parser));
    }
}
