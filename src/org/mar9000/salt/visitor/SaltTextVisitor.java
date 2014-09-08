/**
 * This file is part of the Salt9000 project.
 * 
 * Salt9000 is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * Salt9000 is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with the Salt9000 project.  If not, see <http://www.gnu.org/licenses/>.
 * 
 * Copyright 2014 Marco LOMBARDO, mar9000 near google.com .
 */
package org.mar9000.salt.visitor;

import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.AbstractParseTreeVisitor;
import org.mar9000.salt.grammar.SaltParser.BlanksContext;
import org.mar9000.salt.grammar.SaltParser.ButtonContext;
import org.mar9000.salt.grammar.SaltParser.CellContentContext;
import org.mar9000.salt.grammar.SaltParser.CellContext;
import org.mar9000.salt.grammar.SaltParser.CheckedBoxContext;
import org.mar9000.salt.grammar.SaltParser.DocumentContext;
import org.mar9000.salt.grammar.SaltParser.DropListContext;
import org.mar9000.salt.grammar.SaltParser.EmptyCellContext;
import org.mar9000.salt.grammar.SaltParser.GroupContext;
import org.mar9000.salt.grammar.SaltParser.MenuBarContext;
import org.mar9000.salt.grammar.SaltParser.MenuContext;
import org.mar9000.salt.grammar.SaltParser.RadioCheckedContext;
import org.mar9000.salt.grammar.SaltParser.RadioUncheckedContext;
import org.mar9000.salt.grammar.SaltParser.RowContext;
import org.mar9000.salt.grammar.SaltParser.StringContext;
import org.mar9000.salt.grammar.SaltParser.TabBarContext;
import org.mar9000.salt.grammar.SaltParser.TabContext;
import org.mar9000.salt.grammar.SaltParser.TextContext;
import org.mar9000.salt.grammar.SaltParser.UncheckedBoxContext;
import org.mar9000.salt.grammar.SaltVisitor;

public class SaltTextVisitor extends AbstractParseTreeVisitor<String> implements SaltVisitor<String> {

	private static final String OPEN_RULE = "(";
	private static final String CLOSE_RULE = ")";
	private String[] ruleNames = null;
	
	public SaltTextVisitor(Parser parser) {
		ruleNames = parser.getRuleNames();
	}
	
	@Override
	protected String defaultResult() {
		return "";
	}
	
	@Override
	protected String aggregateResult(String aggregate, String nextResult) {
		return aggregate + nextResult;
	}
	
	private void afterRule(StringBuffer buf) {
		buf.append(" ");
	}
	
	/**
	 * Used when a rule should not generate any output.
	 */
	private String skipRule() {
		return "";
	}

	private String genericRule(ParserRuleContext ctx) {
		StringBuffer buf = new StringBuffer(OPEN_RULE);
		int i = ctx.getRuleIndex();
		String ruleName = ruleNames[i];
		buf.append(ruleName);
		afterRule(buf);
		String childrenResult = visitChildren(ctx);
		buf.append(childrenResult)
		.append(CLOSE_RULE);
		return buf.toString();
	}

	@Override
	public String visitDocument(DocumentContext ctx) {
		return genericRule(ctx);
	}
	
	@Override
	public String visitGroup(GroupContext ctx) {
		return genericRule(ctx);
	}

	@Override
	public String visitText(TextContext ctx) {
		return ctx.getText().trim();
	}

	@Override
	public String visitBlanks(BlanksContext ctx) {
		return skipRule();
	}

	@Override
	public String visitEmptyCell(EmptyCellContext ctx) {
		return "(emptyCell)";
	}

	/**
	 * delegate to cellContent.
	 */
	@Override
	public String visitCell(CellContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public String visitRadioChecked(RadioCheckedContext ctx) {
		return "radioChecked " + visitChildren(ctx);
	}

	@Override
	public String visitRadioUnchecked(RadioUncheckedContext ctx) {
		return "radioUnchecked " + visitChildren(ctx);
	}

	@Override
	public String visitRow(RowContext ctx) {
		return genericRule(ctx);
	}

	@Override
	public String visitCheckedBox(CheckedBoxContext ctx) {
		return "checkedBox " + visitChildren(ctx);
	}

	@Override
	public String visitButton(ButtonContext ctx) {
		return "button " + visitChildren(ctx);
	}

	@Override
	public String visitString(StringContext ctx) {
		return ctx.getText();
	}

	@Override
	public String visitUncheckedBox(UncheckedBoxContext ctx) {
		return "uncheckedBox " + visitChildren(ctx);
	}

	/**
	 * Add open and close parenthesis without the rule name.
	 */
	@Override
	public String visitCellContent(CellContentContext ctx) {
		return OPEN_RULE + visitChildren(ctx) + CLOSE_RULE;
	}

	@Override
	public String visitMenuBar(MenuBarContext ctx) {
		return genericRule(ctx);
	}

	@Override
	public String visitMenu(MenuContext ctx) {
		return OPEN_RULE + visitChildren(ctx) + CLOSE_RULE;
	}

	@Override
	public String visitDropList(DropListContext ctx) {
		return "dropList " + visitChildren(ctx);
	}

	@Override
	public String visitTabBar(TabBarContext ctx) {
		return genericRule(ctx);
	}
	
	@Override
	public String visitTab(TabContext ctx) {
		return OPEN_RULE + (ctx.SELECTED_TAB() != null ? "#" : "") + visitChildren(ctx) + CLOSE_RULE;
	}

}
