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

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import org.antlr.v4.runtime.tree.AbstractParseTreeVisitor;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.mar9000.salt.grammar.SaltParser;
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

public class SaltSwingVisitor extends AbstractParseTreeVisitor<Container> implements SaltVisitor<Container> {
	
	private static String OPEN_BOTH = null;
	static {
		OPEN_BOTH = SaltParser.tokenNames[SaltParser.OPEN_BOTH]
				.substring(1, SaltParser.tokenNames[SaltParser.OPEN_BOTH].length()-1);
	}
	
	private JFrame frame = null;
			
	@Override
	public Container visitTerminal(TerminalNode node) {
		return null;
	}
	
	/**
	 * Called every time a JPanel is needed to control how many JPanel are created.
	 */
	@Override
	protected JPanel defaultResult() {
		JPanel panel = new JPanel();
		// Default is horizontal layout to allocate cells.
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		return panel;
	}
	
	/**
	 * Used only to layout row cells with BoxLayout.
	 */
	@Override
	protected Container aggregateResult(Container aggregate,
			Container nextResult) {
		if (nextResult != null)
			aggregate.add(nextResult);
		return aggregate;
	}

	/**
	 * Should handle an optional menu bar and several groups.
	 * Groups are allocated vertically with a BoxLayout.
	 */
	@Override
	public JFrame visitDocument(DocumentContext ctx) {
		frame = new JFrame("SaltSwingVisitor frame");
		frame.getContentPane().setLayout(new BorderLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationByPlatform(true);
		// The main group.
		frame.getContentPane().add(visitGroup(ctx.group()), BorderLayout.CENTER);
		//
		return frame;
	}

	@Override
	public JMenuBar visitMenuBar(MenuBarContext ctx) {
		JMenuBar menuBar = new JMenuBar();
		int c = ctx.getChildCount();
		for (int i = 0; i < c; i++) {
			ParseTree child = ctx.getChild(i);
			if (child instanceof MenuContext) {
				menuBar.add(child.accept(this));
			}
		}
		return menuBar;
	}

	@Override
	public JMenu visitMenu(MenuContext ctx) {
		return new JMenu(ctx.text().getText());
	}

	/**
	 * A group return a JPanel with vertical BoxLayout, with or without a tabbed pane.
	 */
	@Override
	public JPanel visitGroup(GroupContext ctx) {
		// Always exists.
		JPanel returnPanel = defaultResult();
		// Vertical layout to allocate rows.
		returnPanel.setLayout(new BoxLayout(returnPanel, BoxLayout.Y_AXIS));
		// JTable?
		// TODO: decide if continue to used JTable or switch to BoxLayout to better compatibility with standard Salt.
		if (ctx.OPEN().getText().equals(OPEN_BOTH)) {
			// Handle only text cells.
			Vector<Vector<String>> grid = new Vector<Vector<String>>();
			Iterator<RowContext> iter = ctx.row().iterator();
			int maxColumns = 0;
			int columnCount = 0;
			while (iter.hasNext()) {
				RowContext rowCtx = iter.next();
				Vector<String> row = new Vector<String>();
				columnCount = 0;
				grid.add(row);
				Iterator<CellContext> cellIter = rowCtx.cell().iterator();
				while (cellIter.hasNext()) {
					CellContext cellCtx = cellIter.next();
					if (cellCtx.cellContent() != null) {
						if (cellCtx.cellContent().text() != null) {
							row.add(cellCtx.cellContent().text().getText());
						} else
							row.add(null);
						columnCount++;
						if (maxColumns < columnCount)
							maxColumns = columnCount;
					}
				}
			}
			//
			Vector<String> columnNames = null;
			if (grid.size() > 1) {
				columnNames = grid.get(0);
				grid.remove(0);
			}
			JTable table = new JTable(grid, columnNames);
			returnPanel.add(table.getTableHeader());
			returnPanel.add(table);
			// Return.
			return returnPanel;
		}
		// Handle rows.
		Iterator<RowContext> iter = ctx.row().iterator();
		while (iter.hasNext()) {
			returnPanel.add(visitRow(iter.next()));
		}
		// Return.
		return returnPanel;
	}

	@Override
	public JTabbedPane visitTabBar(TabBarContext ctx) {
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.SCROLL_TAB_LAYOUT);
		//
		Iterator<TabContext> iter = ctx.tab().iterator();
		int index = -1;
		while (iter.hasNext()) {
			// Vertical box layout to allocate group rows.
			JPanel p = new JPanel();
			p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
			TabContext tabContext = iter.next();
			tabbedPane.addTab(tabContext.text().getText(), p);
			index++;
			if (tabContext.SELECTED_TAB() != null) {
				tabbedPane.setSelectedIndex(index);
			}
		}
		//
		return tabbedPane;
	}
	
	// Not used.
	@Override
	public Container visitTab(TabContext ctx) {
		return null;
	}

	@Override
	public JPanel visitRow(RowContext ctx) {
		JPanel returnPanel = (JPanel)visitChildren(ctx);
		BoxLayout layout = new BoxLayout(returnPanel, BoxLayout.X_AXIS);
		returnPanel.setLayout(layout);
		returnPanel.add(Box.createHorizontalGlue());
		//
		return returnPanel;
	}

	@Override
	public Container visitCell(CellContext ctx) {
		if (ctx.cellContent() != null)
			return visitCellContent(ctx.cellContent());
		else if (ctx.group() != null)
			return visitGroup(ctx.group());
		else if (ctx.menuBar() != null && frame.getJMenuBar() == null) {
			/**
			 * The original Salt syntax permits more than one menu bars but this is not possible with Swing
			 * Here we consider only the first one ignoring the others silently.
			 */
			JMenuBar menuBar = visitMenuBar(ctx.menuBar());
			frame.setJMenuBar(menuBar);
			return null;
		} else if (ctx.tabBar() != null) {
			return visitTabBar(ctx.tabBar());
		} else
			return null;
	}

	@Override
	public Container visitCellContent(CellContentContext ctx) {
		// There is only one child.
		ParseTree child = ctx.getChild(0);
		return child.accept(this);
	}
	
	@Override
	public Container visitText(TextContext ctx) {
		return new JLabel(ctx.getText());
	}

	@Override
	public Container visitBlanks(BlanksContext ctx) {
		return null;
	}

	@Override
	public Container visitEmptyCell(EmptyCellContext ctx) {
		return new JLabel(" ");
	}

	@Override
	public Container visitDropList(DropListContext ctx) {
		return new JComboBox(new String[] {ctx.text().getText()});
	}

	@Override
	public JRadioButton visitRadioChecked(RadioCheckedContext ctx) {
		return new JRadioButton(ctx.text().getText(), true);
	}

	@Override
	public JRadioButton visitRadioUnchecked(RadioUncheckedContext ctx) {
		return new JRadioButton(ctx.text().getText(), false);
	}

	@Override
	public JCheckBox visitCheckedBox(CheckedBoxContext ctx) {
		return new JCheckBox(ctx.text().getText(), true);
	}

	@Override
	public JCheckBox visitUncheckedBox(UncheckedBoxContext ctx) {
		return new JCheckBox(ctx.text().getText(), false);
	}

	@Override
	public JButton visitButton(ButtonContext ctx) {
		JButton button = new JButton(ctx.text().getText());
		button.setAlignmentX(Component.LEFT_ALIGNMENT);
		return button;
	}

	@Override
	public JTextField visitString(StringContext ctx) {
		String string = ctx.getText();
		string = string.substring(1, string.length()-1);
		JTextField field = new JTextField(string, string.length());
		return field;
	}

}
