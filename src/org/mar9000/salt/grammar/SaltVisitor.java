// Generated from src/org/mar9000/salt/grammar/Salt.g4 by ANTLR 4.4
package org.mar9000.salt.grammar;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link SaltParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface SaltVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link SaltParser#blanks}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlanks(@NotNull SaltParser.BlanksContext ctx);
	/**
	 * Visit a parse tree produced by {@link SaltParser#menuBar}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMenuBar(@NotNull SaltParser.MenuBarContext ctx);
	/**
	 * Visit a parse tree produced by {@link SaltParser#text}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitText(@NotNull SaltParser.TextContext ctx);
	/**
	 * Visit a parse tree produced by {@link SaltParser#emptyCell}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEmptyCell(@NotNull SaltParser.EmptyCellContext ctx);
	/**
	 * Visit a parse tree produced by {@link SaltParser#cell}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCell(@NotNull SaltParser.CellContext ctx);
	/**
	 * Visit a parse tree produced by {@link SaltParser#menu}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMenu(@NotNull SaltParser.MenuContext ctx);
	/**
	 * Visit a parse tree produced by {@link SaltParser#dropList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDropList(@NotNull SaltParser.DropListContext ctx);
	/**
	 * Visit a parse tree produced by {@link SaltParser#radioChecked}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRadioChecked(@NotNull SaltParser.RadioCheckedContext ctx);
	/**
	 * Visit a parse tree produced by {@link SaltParser#radioUnchecked}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRadioUnchecked(@NotNull SaltParser.RadioUncheckedContext ctx);
	/**
	 * Visit a parse tree produced by {@link SaltParser#tab}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTab(@NotNull SaltParser.TabContext ctx);
	/**
	 * Visit a parse tree produced by {@link SaltParser#tabBar}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTabBar(@NotNull SaltParser.TabBarContext ctx);
	/**
	 * Visit a parse tree produced by {@link SaltParser#row}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRow(@NotNull SaltParser.RowContext ctx);
	/**
	 * Visit a parse tree produced by {@link SaltParser#document}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDocument(@NotNull SaltParser.DocumentContext ctx);
	/**
	 * Visit a parse tree produced by {@link SaltParser#checkedBox}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCheckedBox(@NotNull SaltParser.CheckedBoxContext ctx);
	/**
	 * Visit a parse tree produced by {@link SaltParser#button}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitButton(@NotNull SaltParser.ButtonContext ctx);
	/**
	 * Visit a parse tree produced by {@link SaltParser#string}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitString(@NotNull SaltParser.StringContext ctx);
	/**
	 * Visit a parse tree produced by {@link SaltParser#uncheckedBox}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUncheckedBox(@NotNull SaltParser.UncheckedBoxContext ctx);
	/**
	 * Visit a parse tree produced by {@link SaltParser#cellContent}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCellContent(@NotNull SaltParser.CellContentContext ctx);
	/**
	 * Visit a parse tree produced by {@link SaltParser#group}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGroup(@NotNull SaltParser.GroupContext ctx);
}