// Generated from src/org/mar9000/salt/grammar/Salt.g4 by ANTLR 4.4
package org.mar9000.salt.grammar;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link SaltParser}.
 */
public interface SaltListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link SaltParser#blanks}.
	 * @param ctx the parse tree
	 */
	void enterBlanks(@NotNull SaltParser.BlanksContext ctx);
	/**
	 * Exit a parse tree produced by {@link SaltParser#blanks}.
	 * @param ctx the parse tree
	 */
	void exitBlanks(@NotNull SaltParser.BlanksContext ctx);
	/**
	 * Enter a parse tree produced by {@link SaltParser#menuBar}.
	 * @param ctx the parse tree
	 */
	void enterMenuBar(@NotNull SaltParser.MenuBarContext ctx);
	/**
	 * Exit a parse tree produced by {@link SaltParser#menuBar}.
	 * @param ctx the parse tree
	 */
	void exitMenuBar(@NotNull SaltParser.MenuBarContext ctx);
	/**
	 * Enter a parse tree produced by {@link SaltParser#text}.
	 * @param ctx the parse tree
	 */
	void enterText(@NotNull SaltParser.TextContext ctx);
	/**
	 * Exit a parse tree produced by {@link SaltParser#text}.
	 * @param ctx the parse tree
	 */
	void exitText(@NotNull SaltParser.TextContext ctx);
	/**
	 * Enter a parse tree produced by {@link SaltParser#emptyCell}.
	 * @param ctx the parse tree
	 */
	void enterEmptyCell(@NotNull SaltParser.EmptyCellContext ctx);
	/**
	 * Exit a parse tree produced by {@link SaltParser#emptyCell}.
	 * @param ctx the parse tree
	 */
	void exitEmptyCell(@NotNull SaltParser.EmptyCellContext ctx);
	/**
	 * Enter a parse tree produced by {@link SaltParser#cell}.
	 * @param ctx the parse tree
	 */
	void enterCell(@NotNull SaltParser.CellContext ctx);
	/**
	 * Exit a parse tree produced by {@link SaltParser#cell}.
	 * @param ctx the parse tree
	 */
	void exitCell(@NotNull SaltParser.CellContext ctx);
	/**
	 * Enter a parse tree produced by {@link SaltParser#menu}.
	 * @param ctx the parse tree
	 */
	void enterMenu(@NotNull SaltParser.MenuContext ctx);
	/**
	 * Exit a parse tree produced by {@link SaltParser#menu}.
	 * @param ctx the parse tree
	 */
	void exitMenu(@NotNull SaltParser.MenuContext ctx);
	/**
	 * Enter a parse tree produced by {@link SaltParser#dropList}.
	 * @param ctx the parse tree
	 */
	void enterDropList(@NotNull SaltParser.DropListContext ctx);
	/**
	 * Exit a parse tree produced by {@link SaltParser#dropList}.
	 * @param ctx the parse tree
	 */
	void exitDropList(@NotNull SaltParser.DropListContext ctx);
	/**
	 * Enter a parse tree produced by {@link SaltParser#radioChecked}.
	 * @param ctx the parse tree
	 */
	void enterRadioChecked(@NotNull SaltParser.RadioCheckedContext ctx);
	/**
	 * Exit a parse tree produced by {@link SaltParser#radioChecked}.
	 * @param ctx the parse tree
	 */
	void exitRadioChecked(@NotNull SaltParser.RadioCheckedContext ctx);
	/**
	 * Enter a parse tree produced by {@link SaltParser#radioUnchecked}.
	 * @param ctx the parse tree
	 */
	void enterRadioUnchecked(@NotNull SaltParser.RadioUncheckedContext ctx);
	/**
	 * Exit a parse tree produced by {@link SaltParser#radioUnchecked}.
	 * @param ctx the parse tree
	 */
	void exitRadioUnchecked(@NotNull SaltParser.RadioUncheckedContext ctx);
	/**
	 * Enter a parse tree produced by {@link SaltParser#tab}.
	 * @param ctx the parse tree
	 */
	void enterTab(@NotNull SaltParser.TabContext ctx);
	/**
	 * Exit a parse tree produced by {@link SaltParser#tab}.
	 * @param ctx the parse tree
	 */
	void exitTab(@NotNull SaltParser.TabContext ctx);
	/**
	 * Enter a parse tree produced by {@link SaltParser#tabBar}.
	 * @param ctx the parse tree
	 */
	void enterTabBar(@NotNull SaltParser.TabBarContext ctx);
	/**
	 * Exit a parse tree produced by {@link SaltParser#tabBar}.
	 * @param ctx the parse tree
	 */
	void exitTabBar(@NotNull SaltParser.TabBarContext ctx);
	/**
	 * Enter a parse tree produced by {@link SaltParser#row}.
	 * @param ctx the parse tree
	 */
	void enterRow(@NotNull SaltParser.RowContext ctx);
	/**
	 * Exit a parse tree produced by {@link SaltParser#row}.
	 * @param ctx the parse tree
	 */
	void exitRow(@NotNull SaltParser.RowContext ctx);
	/**
	 * Enter a parse tree produced by {@link SaltParser#document}.
	 * @param ctx the parse tree
	 */
	void enterDocument(@NotNull SaltParser.DocumentContext ctx);
	/**
	 * Exit a parse tree produced by {@link SaltParser#document}.
	 * @param ctx the parse tree
	 */
	void exitDocument(@NotNull SaltParser.DocumentContext ctx);
	/**
	 * Enter a parse tree produced by {@link SaltParser#checkedBox}.
	 * @param ctx the parse tree
	 */
	void enterCheckedBox(@NotNull SaltParser.CheckedBoxContext ctx);
	/**
	 * Exit a parse tree produced by {@link SaltParser#checkedBox}.
	 * @param ctx the parse tree
	 */
	void exitCheckedBox(@NotNull SaltParser.CheckedBoxContext ctx);
	/**
	 * Enter a parse tree produced by {@link SaltParser#button}.
	 * @param ctx the parse tree
	 */
	void enterButton(@NotNull SaltParser.ButtonContext ctx);
	/**
	 * Exit a parse tree produced by {@link SaltParser#button}.
	 * @param ctx the parse tree
	 */
	void exitButton(@NotNull SaltParser.ButtonContext ctx);
	/**
	 * Enter a parse tree produced by {@link SaltParser#string}.
	 * @param ctx the parse tree
	 */
	void enterString(@NotNull SaltParser.StringContext ctx);
	/**
	 * Exit a parse tree produced by {@link SaltParser#string}.
	 * @param ctx the parse tree
	 */
	void exitString(@NotNull SaltParser.StringContext ctx);
	/**
	 * Enter a parse tree produced by {@link SaltParser#uncheckedBox}.
	 * @param ctx the parse tree
	 */
	void enterUncheckedBox(@NotNull SaltParser.UncheckedBoxContext ctx);
	/**
	 * Exit a parse tree produced by {@link SaltParser#uncheckedBox}.
	 * @param ctx the parse tree
	 */
	void exitUncheckedBox(@NotNull SaltParser.UncheckedBoxContext ctx);
	/**
	 * Enter a parse tree produced by {@link SaltParser#cellContent}.
	 * @param ctx the parse tree
	 */
	void enterCellContent(@NotNull SaltParser.CellContentContext ctx);
	/**
	 * Exit a parse tree produced by {@link SaltParser#cellContent}.
	 * @param ctx the parse tree
	 */
	void exitCellContent(@NotNull SaltParser.CellContentContext ctx);
	/**
	 * Enter a parse tree produced by {@link SaltParser#group}.
	 * @param ctx the parse tree
	 */
	void enterGroup(@NotNull SaltParser.GroupContext ctx);
	/**
	 * Exit a parse tree produced by {@link SaltParser#group}.
	 * @param ctx the parse tree
	 */
	void exitGroup(@NotNull SaltParser.GroupContext ctx);
}