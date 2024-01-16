package ru.rusanoph;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import ru.rusanoph.grammar.parser.ExprLexer;
import ru.rusanoph.grammar.parser.ExprParser;
import ru.rusanoph.grammar.parser.visitor.EvalVisitor;

public class Calc {
    
    public void printEval(CharStream input) {
        ExprLexer lexer = new ExprLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        ExprParser parser = new ExprParser(tokens);
        ParseTree tree = parser.prog();

        EvalVisitor eval = new EvalVisitor();
        
        eval.visit(tree);
    }

}
