package ru.rusanoph.grammar.parser.visitor;

import ru.rusanoph.grammar.parser.ExprBaseVisitor;
import ru.rusanoph.grammar.parser.ExprParser;
import ru.rusanoph.grammar.parser.ExprParser.AddSubContext;
import ru.rusanoph.grammar.parser.ExprParser.AssignContext;
import ru.rusanoph.grammar.parser.ExprParser.ClearContext;
import ru.rusanoph.grammar.parser.ExprParser.IdContext;
import ru.rusanoph.grammar.parser.ExprParser.IntContext;
import ru.rusanoph.grammar.parser.ExprParser.MulDivContext;
import ru.rusanoph.grammar.parser.ExprParser.ParensContext;
import ru.rusanoph.grammar.parser.ExprParser.PrintExprContext;

import java.util.Map;
import java.util.HashMap;

public class EvalVisitor extends ExprBaseVisitor<Integer> {
    private Map<String, Integer> memory = new HashMap<String, Integer>();



    @Override
    public Integer visitAddSub(AddSubContext ctx) {
        int left = (int) visit(ctx.expr(0));
        int right = (int) visit(ctx.expr(1));
        if (ctx.op.getType() == ExprParser.ADD) return left + right;
        return left - right;
    }

    @Override
    public Integer visitAssign(AssignContext ctx) {
        String id = ctx.ID().getText();
        int value = visit(ctx.expr());
        memory.put(id, value);
        return value;
    }

    @Override
    public Integer visitId(IdContext ctx) {
        String id = ctx.ID().getText();
        if (memory.containsKey(id)) return memory.get(id);
        return 0;
    }

    @Override
    public Integer visitInt(IntContext ctx) {
        return Integer.valueOf(ctx.INT().getText());
    }

    @Override
    public Integer visitMulDiv(MulDivContext ctx) {
        int left = (int) visit(ctx.expr(0));
        int right = (int) visit(ctx.expr(1));

        if (ctx.op.getType() == ExprParser.MUL) return left * right;
        return left / right;
    }

    @Override
    public Integer visitParens(ParensContext ctx) {
        return (Integer) visit(ctx.expr());
    }

    @Override
    public Integer visitPrintExpr(PrintExprContext ctx) {
        Integer value = (Integer) visit(ctx.expr());
        String print = String.format("%s\t\t -> %s", ctx.getText().trim(), value);
        System.out.println(print);
        return 0;
    }

    @Override
    public Integer visitClear(ClearContext ctx) {
        for (int i=0; i< 100; i++) System.out.println();
        System.out.println("=== Arithmetic Expression Evaluator ===");
        return 0;
    }


    
}
