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
package org.mar9000.salt;

import java.awt.Container;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.LookAndFeel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.tree.ParseTree;
import org.mar9000.salt.grammar.SaltLexer;
import org.mar9000.salt.grammar.SaltParser;
import org.mar9000.salt.visitor.SaltSwingVisitor;

/**
 * Main class to process a string or a file.
 * @author mar9000.
 */
public class SaltProcessor {
	
	/** The input file.   */
	private File inputFile = null;
	/** The input data, eventually loaded from inputFile.   */
	private String data;
	
	/** File constructor.   */
	public SaltProcessor(File inputFile) {
		this.inputFile = inputFile;
	}
	/** String constructor.   */
	public SaltProcessor(String data) {
		this.data = data;
	}
	/** Load input file into data.   */
	private void loadInputFile() {
		if (data != null)
			return;
		if (inputFile == null)
			throw new IllegalArgumentException("Input file is missing.");
		// Load.
		try {
			data = readFileAsString(inputFile);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Enable to open file: " + inputFile);
			return;
		}
	}
	
	/** The parse tree.   */
	private static ParseTree tree = null;
	/** Parse data or input file and initialize the parse tree.   */
	private void parseInput() {
		if (data == null)
			loadInputFile();
		if (data == null)
			throw new IllegalArgumentException("Unable to load input file or data is missing.");
		ANTLRInputStream input = new ANTLRInputStream(data);
		SaltLexer lexer = new SaltLexer(input);
		lexer.addErrorListener(new BaseErrorListener() {
			@Override
			public void syntaxError(Recognizer<?, ?> arg0, Object arg1, int arg2,
					int arg3, String arg4, RecognitionException arg5) {
				throw new RuntimeException(arg5);
			}
		});
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		SaltParser parser = new SaltParser(tokens);
		tree = parser.document();
		if (parser.getNumberOfSyntaxErrors() > 0) {
			System.out.println("Syntax error in file " + inputFile);
			return;
		}
	}
	
	/** The resulting image.   */
	private BufferedImage image = null;
	/** Create the image visiting the existing parse tree.   */
	private void createImage() {
		// Input parsed?
		if (tree == null)
			parseInput();
		if (tree == null)
			throw new RuntimeException("Unable to parse input.");
		SaltSwingVisitor visitor = new SaltSwingVisitor();
		JFrame frame = (JFrame)visitor.visit(tree);
		frame.pack();
		frame.setVisible(true);
		// Render the component to image.
		Container componentToRender = frame.getRootPane();
		image = new BufferedImage(componentToRender.getWidth(), componentToRender.getHeight()
				, BufferedImage.TYPE_INT_RGB);
		componentToRender.paint(image.getGraphics());
	}
	
	/** Output method that return the created image.   */
	public BufferedImage getImage() {
		if (image == null)
			createImage();
		return image;
	}
	/**
	 * Dump image to output file.
	 * @param path The complete filename to use. If null the path is calculated from inputFile if present.
	 */
	public void dumpImage(String path) {
		if (image == null)
			createImage();
		if (image == null)
			throw new RuntimeException("Unable to parse input or create the image.");
		// Calculate output file.
		File outputFile = null;
		if (path == null) {
			String inputFilename = inputFile.getName();
			int i = inputFilename.lastIndexOf('.');
			String outputFilename = i != -1 ? inputFilename.substring(0, i) : inputFilename;
			path = inputFile.getParent() + File.separatorChar + outputFilename + ".png";
		}
		outputFile = new File(path);
		// Write image to file.
		try {
			ImageIO.write(image, "PNG", outputFile);
			System.out.println("   ... file " + outputFile + " has been generated.");
		} catch (IOException e1) {
			e1.printStackTrace();
			System.out.println("Enable to write to file: " + inputFile);
		}
	}
	
	// ---------- Look&Fell methods. ----------
	
	/** The Jgoodies L&F class name.   */
	public final static String JGOODIES_PLASTIC_LOOKANDFEEL = "com.jgoodies.looks.plastic.PlasticXPLookAndFeel";
	/** Set a specific L&F by class name.   */
	public void setLookAndFeel(String className) {
		if (className == null)
			return;
		//
		try {
			Class.forName(className);
			Class clazz = Thread.currentThread().getContextClassLoader().loadClass(className);
			LookAndFeel landf = (LookAndFeel)clazz.newInstance();
			UIManager.setLookAndFeel(landf);
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
			System.err.println("Look&Feel exception.");
		} catch (ClassNotFoundException e) {
			System.err.println("Look&Feel " + className + " not found.");
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	
	// ---------- Utils. ----------
	
	/** Read an input file into a String.   */
	private static String readFileAsString(File file) throws IOException {
		StringBuffer fileData = new StringBuffer();
		BufferedReader reader = new BufferedReader(
				new FileReader(file));
		char[] buf = new char[1024];
		int numRead=0;
		while((numRead=reader.read(buf)) != -1){
			String readData = String.valueOf(buf, 0, numRead);
			fileData.append(readData);
		}
		reader.close();
		return fileData.toString();
	}
}
