// Generated from C:/Users/HP/Desktop/Compiladores/ProjetoTuga/src/Tuga.g4 by ANTLR 4.13.2
package Tuga;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link TugaParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface TugaVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link TugaParser#tuga}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTuga(TugaParser.TugaContext ctx);
	/**
	 * Visit a parse tree produced by the {@code DeclVar}
	 * labeled alternative in {@link TugaParser#declare_var}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclVar(TugaParser.DeclVarContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Vars}
	 * labeled alternative in {@link TugaParser#variable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVars(TugaParser.VarsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Var}
	 * labeled alternative in {@link TugaParser#variable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVar(TugaParser.VarContext ctx);
	/**
	 * Visit a parse tree produced by {@link TugaParser#inst}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInst(TugaParser.InstContext ctx);
	/**
	 * Visit a parse tree produced by the {@code InstPrint}
	 * labeled alternative in {@link TugaParser#print}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInstPrint(TugaParser.InstPrintContext ctx);
	/**
	 * Visit a parse tree produced by the {@code InstAssign}
	 * labeled alternative in {@link TugaParser#assign}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInstAssign(TugaParser.InstAssignContext ctx);
	/**
	 * Visit a parse tree produced by the {@code InstScope}
	 * labeled alternative in {@link TugaParser#scope}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInstScope(TugaParser.InstScopeContext ctx);
	/**
	 * Visit a parse tree produced by {@link TugaParser#scopeOrInst}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitScopeOrInst(TugaParser.ScopeOrInstContext ctx);
	/**
	 * Visit a parse tree produced by the {@code InstIf}
	 * labeled alternative in {@link TugaParser#if}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInstIf(TugaParser.InstIfContext ctx);
	/**
	 * Visit a parse tree produced by the {@code InstIfElse}
	 * labeled alternative in {@link TugaParser#ifelse}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInstIfElse(TugaParser.InstIfElseContext ctx);
	/**
	 * Visit a parse tree produced by the {@code InstWhile}
	 * labeled alternative in {@link TugaParser#while}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInstWhile(TugaParser.InstWhileContext ctx);
	/**
	 * Visit a parse tree produced by the {@code InstEmpty}
	 * labeled alternative in {@link TugaParser#empty}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInstEmpty(TugaParser.InstEmptyContext ctx);
	/**
	 * Visit a parse tree produced by the {@code EqualsOp}
	 * labeled alternative in {@link TugaParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEqualsOp(TugaParser.EqualsOpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code OrOp}
	 * labeled alternative in {@link TugaParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOrOp(TugaParser.OrOpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code NegateOp}
	 * labeled alternative in {@link TugaParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNegateOp(TugaParser.NegateOpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code SumSubOp}
	 * labeled alternative in {@link TugaParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSumSubOp(TugaParser.SumSubOpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code LogicNegateOp}
	 * labeled alternative in {@link TugaParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLogicNegateOp(TugaParser.LogicNegateOpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code LiteralExpr}
	 * labeled alternative in {@link TugaParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLiteralExpr(TugaParser.LiteralExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code VarExpr}
	 * labeled alternative in {@link TugaParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarExpr(TugaParser.VarExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code RelOp}
	 * labeled alternative in {@link TugaParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRelOp(TugaParser.RelOpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ParenExpr}
	 * labeled alternative in {@link TugaParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParenExpr(TugaParser.ParenExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code MultDivModOp}
	 * labeled alternative in {@link TugaParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultDivModOp(TugaParser.MultDivModOpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code AndOp}
	 * labeled alternative in {@link TugaParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAndOp(TugaParser.AndOpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Int}
	 * labeled alternative in {@link TugaParser#literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInt(TugaParser.IntContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Double}
	 * labeled alternative in {@link TugaParser#literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDouble(TugaParser.DoubleContext ctx);
	/**
	 * Visit a parse tree produced by the {@code String}
	 * labeled alternative in {@link TugaParser#literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitString(TugaParser.StringContext ctx);
	/**
	 * Visit a parse tree produced by the {@code True}
	 * labeled alternative in {@link TugaParser#literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTrue(TugaParser.TrueContext ctx);
	/**
	 * Visit a parse tree produced by the {@code False}
	 * labeled alternative in {@link TugaParser#literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFalse(TugaParser.FalseContext ctx);
}