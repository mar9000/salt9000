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
package org.mar9000.salt.grammar.test;

import static org.junit.Assert.*;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Test;
import org.mar9000.salt.grammar.SaltLexer;
import org.mar9000.salt.grammar.SaltParser;
import org.mar9000.salt.visitor.SaltTextVisitor;

public class SaltTest {
	
	private class SyntaxException extends RuntimeException {
		
		private SyntaxException(String message) {
			super(message);
		}
		
	}
	
	/**
	 * Parse the string passed as parameter returning the string representing the structure of the UI
	 * described by the parameter.
	 * @param document
	 * @return
	 * @throws Exception
	 */
	private String parse(String document) throws Exception {
		ANTLRInputStream input = new ANTLRInputStream(document);
		SaltLexer lexer = new SaltLexer(input);
		// The lexer should not recover errors.
		lexer.addErrorListener(new BaseErrorListener() {
			
			@Override
			public void syntaxError(Recognizer<?, ?> arg0, Object arg1, int arg2,
					int arg3, String arg4, RecognitionException arg5) {
				throw new RuntimeException(arg5);
			}
			
		});
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		SaltParser parser = new SaltParser(tokens);
		ParseTree tree = parser.document();
		if (parser.getNumberOfSyntaxErrors() > 0) {
			throw new SyntaxException("Syntax error into the document: " + document);
		}
		//
		SaltTextVisitor visitor = new SaltTextVisitor(parser);
		return visitor.visit(tree);
	}
	
	// ---------- Document rule.
	
	@Test
	public void testEmptyDocument() throws Exception {
		String document = " \n  { }  \n  ";
		String expected = "(document (group ))";
		String actual = parse(document);
		assertEquals(expected, actual);
		//
		document = " \n  { \n \n  \n }  \n  ";
		actual = parse(document);
		assertEquals(expected, actual);
	}
	
	// ---------- Group/row rule.
	
	@Test
	public void testGroup() throws Exception {
		String document = "{ \"login\" | ssss}";
		String expected = "(document (group (row (\"login\")(ssss))))";
		String actual = parse(document);
		assertEquals(expected, actual);
		//
		document = "{ \"login\" | ssss  \n \n}";
		actual = parse(document);
		assertEquals(expected, actual);
		//
		document = "{ \"login\" | ssss  \n \n   }";
		actual = parse(document);
		assertEquals(expected, actual);
	}
	
	@Test
	public void testGroup2Row() throws Exception {
		String document = "{ \"login\" | ssss \n aa | bb }";
		String expected = "(document (group (row (\"login\")(ssss))(row (aa)(bb))))";
		String actual = parse(document);
		assertEquals(expected, actual);
		// Single cell.
		document = "{ \"login\" | ssss \n aa }";
		expected = "(document (group (row (\"login\")(ssss))(row (aa))))";
		actual = parse(document);
		assertEquals(expected, actual);
	}
	
	@Test
	public void testRowHeadingAndTrailing() throws Exception {
		String document = "{ \n \n aa | bb \n \n}";
		String expected = "(document (group (row (aa)(bb))))";
		String actual = parse(document);
		System.out.println(actual);
		assertEquals(expected, actual);
	}
	
	// ---------- Cell rule.
	
	@Test
	public void testEmptyCell() throws Exception {
		String document = "{ . | bb \n \n  aa | . }";
		String expected = "(document (group (row (emptyCell)(bb))"
				+ "(row (aa)(emptyCell))))";
		String actual = parse(document);
		assertEquals(expected, actual);
	}
	
	@Test
	public void testInnerGroup() throws Exception {
		String document = "{ { a | b | c} \n {d | e} }";
		String expected = "(document (group (row (group (row (a)(b)(c))))"
				+ "(row (group (row (d)(e))))))";
		String actual = parse(document);
		assertEquals(expected, actual);
	}
	
	@Test
	public void testMenu() throws Exception {
		String document = "{{* menu1 | menu2 } \n { \"login\" | ssss}}";
		String expected = "(document (group (row (menuBar (menu1)(menu2)))"
				+ "(row (group (row (\"login\")(ssss))))))";
		String actual = parse(document);
		assertEquals(expected, actual);
	}
	
	@Test
	public void testHorizontalTabs() throws Exception {
		String document = "{{/ Tab 1 | <b>Selected } \n \"Role\" | ^Admin role^ }";
		String expected = "(document (group (row (tabBar (Tab 1)(#Selected)))"
				+ "(row (\"Role\")(dropList Admin role))))";
		String actual = parse(document);
		assertEquals(expected, actual);
	}
	
	// ---------- Cell content rule.
	
	@Test
	public void testButtons() throws Exception {
		String document = "{{/ Tab 1 | <b>Selected } \n \"Role\" | ^Admin role^ \n [Cancel] | [Ok]}";
		String expected = "(document (group (row (tabBar (Tab 1)(#Selected)))"
				+ "(row (\"Role\")(dropList Admin role))"
				+ "(row (button Cancel)(button Ok))))";
		String actual = parse(document);
		System.out.println(actual);
		assertEquals(expected, actual);
	}
	
	@Test
	public void testRadioButtons() throws Exception {
		String document = "{ \"Role\" | ^Admin role^ \n () To be selected \n (X) Selected }";
		String expected = "(document (group (row (\"Role\")(dropList Admin role))"
				+ "(row (radioUnchecked To be selected))"
				+ "(row (radioChecked Selected))))";
		String actual = parse(document);
		System.out.println(actual);
		assertEquals(expected, actual);
	}
	
	@Test
	public void testCheckBoxes() throws Exception {
		String document = "{ \"Role\" | ^Admin role^ \n [] To be selected \n [X] Selected }";
		String expected = "(document (group (row (\"Role\")(dropList Admin role))"
				+ "(row (uncheckedBox To be selected))"
				+ "(row (checkedBox Selected))))";
		String actual = parse(document);
		System.out.println(actual);
		assertEquals(expected, actual);
	}
	
	@Test
	public void testDroplist() throws Exception {
		String document = "{ \"Role\" | ^Admin role^ }";
		String expected = "(document (group (row (\"Role\")(dropList Admin role))))";
		String actual = parse(document);
		System.out.println(actual);
		assertEquals(expected, actual);
	}
	
	@Test
	public void testSpecialChars() throws Exception {
		String document = "{ @#&% | !$ +? }";
		String expected = "(document (group (row (@#&%)(!$ +?))))";
		String actual = parse(document);
		System.out.println(actual);
		assertEquals(expected, actual);
	}
	
	// ---------- Error handling.
	
	@Test(expected=RuntimeException.class)
	public void testRecognitionError() throws Exception {
		// ~Tab 2 is not recognized.
		String document = "{{/ Tab 1 | ~Tab 2 } \n \"Role\" | ^Admin role^ }";
		String actual = parse(document);   // Throw the exception.
	}
	
	@Test(expected=SyntaxException.class)
	public void testSyntaxError() throws Exception {
		// More than one tabBar definition is not allowed.
		String document = "{{/ Tab 1 | Tab 2 }  {/ Not allowed | Not allowed } \n \"Role\" | ^Admin role^ }";
		String actual = parse(document);   // Throw the exception.
	}
	
}
