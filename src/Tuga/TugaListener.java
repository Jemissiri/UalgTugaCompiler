// Generated from C:/Users/HP/Desktop/Compiladores/ProjetoTuga/src/Tuga.g4 by ANTLR 4.13.2
package Tuga;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link TugaParser}.
 */
public interface TugaListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link TugaParser#tuga}.
	 * @param ctx the parse tree
	 */
	void enterTuga(TugaParser.TugaContext ctx);
	/**
	 * Exit a parse tree produced by {@link TugaParser#tuga}.
	 * @param ctx the parse tree
	 */
	void exitTuga(TugaParser.TugaContext ctx);
	/**
	 * Enter a parse tree produced by the {@code DeclVar}
	 * labeled alternative in {@link TugaParser#declare_var}.
	 * @param ctx the parse tree
	 */
	void enterDeclVar(TugaParser.DeclVarContext ctx);
	/**
	 * Exit a parse tree produced by the {@code DeclVar}
	 * labeled alternative in {@link TugaParser#declare_var}.
	 * @param ctx the parse tree
	 */
	void exitDeclVar(TugaParser.DeclVarContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Vars}
	 * labeled alternative in {@link TugaParser#variable}.
	 * @param ctx the parse tree
	 */
	void enterVars(TugaParser.VarsContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Vars}
	 * labeled alternative in {@link TugaParser#variable}.
	 * @param ctx the parse tree
	 */
	void exitVars(TugaParser.VarsContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Var}
	 * labeled alternative in {@link TugaParser#variable}.
	 * @param ctx the parse tree
	 */
	void enterVar(TugaParser.VarContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Var}
	 * labeled alternative in {@link TugaParser#variable}.
	 * @param ctx the parse tree
	 */
	void exitVar(TugaParser.VarContext ctx);
	/**
	 * Enter a parse tree produced by {@link TugaParser#inst}.
	 * @param ctx the parse tree
	 */
	void enterInst(TugaParser.InstContext ctx);
	/**
	 * Exit a parse tree produced by {@link TugaParser#inst}.
	 * @param ctx the parse tree
	 */
	void exitInst(TugaParser.InstContext ctx);
	/**
	 * Enter a parse tree produced by the {@code InstPrint}
	 * labeled alternative in {@link TugaParser#print}.
	 * @param ctx the parse tree
	 */
	void enterInstPrint(TugaParser.InstPrintContext ctx);
	/**
	 * Exit a parse tree produced by the {@code InstPrint}
	 * labeled alternative in {@link TugaParser#print}.
	 * @param ctx the parse tree
	 */
	void exitInstPrint(TugaParser.InstPrintContext ctx);
	/**
	 * Enter a parse tree produced by the {@code InstAssign}
	 * labeled alternative in {@link TugaParser#assign}.
	 * @param ctx the parse tree
	 */
	void enterInstAssign(TugaParser.InstAssignContext ctx);
	/**
	 * Exit a parse tree produced by the {@code InstAssign}
	 * labeled alternative in {@link TugaParser#assign}.
	 * @param ctx the parse tree
	 */
	void exitInstAssign(TugaParser.InstAssignContext ctx);
	/**
	 * Enter a parse tree produced by the {@code InstScope}
	 * labeled alternative in {@link TugaParser#scope}.
	 * @param ctx the parse tree
	 */
	void enterInstScope(TugaParser.InstScopeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code InstScope}
	 * labeled alternative in {@link TugaParser#scope}.
	 * @param ctx the parse tree
	 */
	void exitInstScope(TugaParser.InstScopeContext ctx);
	/**
	 * Enter a parse tree produced by {@link TugaParser#scopeOrInst}.
	 * @param ctx the parse tree
	 */
	void enterScopeOrInst(TugaParser.ScopeOrInstContext ctx);
	/**
	 * Exit a parse tree produced by {@link TugaParser#scopeOrInst}.
	 * @param ctx the parse tree
	 */
	void exitScopeOrInst(TugaParser.ScopeOrInstContext ctx);
	/**
	 * Enter a parse tree produced by the {@code InstIf}
	 * labeled alternative in {@link TugaParser#if}.
	 * @param ctx the parse tree
	 */
	void enterInstIf(TugaParser.InstIfContext ctx);
	/**
	 * Exit a parse tree produced by the {@code InstIf}
	 * labeled alternative in {@link TugaParser#if}.
	 * @param ctx the parse tree
	 */
	void exitInstIf(TugaParser.InstIfContext ctx);
	/**
	 * Enter a parse tree produced by the {@code InstIfElse}
	 * labeled alternative in {@link TugaParser#ifelse}.
	 * @param ctx the parse tree
	 */
	void enterInstIfElse(TugaParser.InstIfElseContext ctx);
	/**
	 * Exit a parse tree produced by the {@code InstIfElse}
	 * labeled alternative in {@link TugaParser#ifelse}.
	 * @param ctx the parse tree
	 */
	void exitInstIfElse(TugaParser.InstIfElseContext ctx);
	/**
	 * Enter a parse tree produced by the {@code InstWhile}
	 * labeled alternative in {@link TugaParser#while}.
	 * @param ctx the parse tree
	 */
	void enterInstWhile(TugaParser.InstWhileContext ctx);
	/**
	 * Exit a parse tree produced by the {@code InstWhile}
	 * labeled alternative in {@link TugaParser#while}.
	 * @param ctx the parse tree
	 */
	void exitInstWhile(TugaParser.InstWhileContext ctx);
	/**
	 * Enter a parse tree produced by the {@code InstEmpty}
	 * labeled alternative in {@link TugaParser#empty}.
	 * @param ctx the parse tree
	 */
	void enterInstEmpty(TugaParser.InstEmptyContext ctx);
	/**
	 * Exit a parse tree produced by the {@code InstEmpty}
	 * labeled alternative in {@link TugaParser#empty}.
	 * @param ctx the parse tree
	 */
	void exitInstEmpty(TugaParser.InstEmptyContext ctx);
	/**
	 * Enter a parse tree produced by the {@code EqualsOp}
	 * labeled alternative in {@link TugaParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterEqualsOp(TugaParser.EqualsOpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code EqualsOp}
	 * labeled alternative in {@link TugaParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitEqualsOp(TugaParser.EqualsOpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code OrOp}
	 * labeled alternative in {@link TugaParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterOrOp(TugaParser.OrOpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code OrOp}
	 * labeled alternative in {@link TugaParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitOrOp(TugaParser.OrOpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code NegateOp}
	 * labeled alternative in {@link TugaParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterNegateOp(TugaParser.NegateOpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code NegateOp}
	 * labeled alternative in {@link TugaParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitNegateOp(TugaParser.NegateOpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code SumSubOp}
	 * labeled alternative in {@link TugaParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterSumSubOp(TugaParser.SumSubOpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code SumSubOp}
	 * labeled alternative in {@link TugaParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitSumSubOp(TugaParser.SumSubOpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code LogicNegateOp}
	 * labeled alternative in {@link TugaParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterLogicNegateOp(TugaParser.LogicNegateOpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code LogicNegateOp}
	 * labeled alternative in {@link TugaParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitLogicNegateOp(TugaParser.LogicNegateOpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code LiteralExpr}
	 * labeled alternative in {@link TugaParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterLiteralExpr(TugaParser.LiteralExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code LiteralExpr}
	 * labeled alternative in {@link TugaParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitLiteralExpr(TugaParser.LiteralExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code VarExpr}
	 * labeled alternative in {@link TugaParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterVarExpr(TugaParser.VarExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code VarExpr}
	 * labeled alternative in {@link TugaParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitVarExpr(TugaParser.VarExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code RelOp}
	 * labeled alternative in {@link TugaParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterRelOp(TugaParser.RelOpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code RelOp}
	 * labeled alternative in {@link TugaParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitRelOp(TugaParser.RelOpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ParenExpr}
	 * labeled alternative in {@link TugaParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterParenExpr(TugaParser.ParenExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ParenExpr}
	 * labeled alternative in {@link TugaParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitParenExpr(TugaParser.ParenExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code MultDivModOp}
	 * labeled alternative in {@link TugaParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterMultDivModOp(TugaParser.MultDivModOpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code MultDivModOp}
	 * labeled alternative in {@link TugaParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitMultDivModOp(TugaParser.MultDivModOpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AndOp}
	 * labeled alternative in {@link TugaParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterAndOp(TugaParser.AndOpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AndOp}
	 * labeled alternative in {@link TugaParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitAndOp(TugaParser.AndOpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Int}
	 * labeled alternative in {@link TugaParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterInt(TugaParser.IntContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Int}
	 * labeled alternative in {@link TugaParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitInt(TugaParser.IntContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Double}
	 * labeled alternative in {@link TugaParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterDouble(TugaParser.DoubleContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Double}
	 * labeled alternative in {@link TugaParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitDouble(TugaParser.DoubleContext ctx);
	/**
	 * Enter a parse tree produced by the {@code String}
	 * labeled alternative in {@link TugaParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterString(TugaParser.StringContext ctx);
	/**
	 * Exit a parse tree produced by the {@code String}
	 * labeled alternative in {@link TugaParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitString(TugaParser.StringContext ctx);
	/**
	 * Enter a parse tree produced by the {@code True}
	 * labeled alternative in {@link TugaParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterTrue(TugaParser.TrueContext ctx);
	/**
	 * Exit a parse tree produced by the {@code True}
	 * labeled alternative in {@link TugaParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitTrue(TugaParser.TrueContext ctx);
	/**
	 * Enter a parse tree produced by the {@code False}
	 * labeled alternative in {@link TugaParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterFalse(TugaParser.FalseContext ctx);
	/**
	 * Exit a parse tree produced by the {@code False}
	 * labeled alternative in {@link TugaParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitFalse(TugaParser.FalseContext ctx);
}