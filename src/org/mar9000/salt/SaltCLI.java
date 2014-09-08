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
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.LookAndFeel;
import javax.swing.SwingUtilities;
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

public class SaltCLI {

	private static ParseTree tree = null;
	private static ArrayList<String> inputFilenames = new ArrayList<String>();
	private static File outputDirParameter = null;
	private static String JGOODIES_PLASTIC_LOOKANDFEEL = "com.jgoodies.looks.plastic.PlasticXPLookAndFeel"; 
	private static String lookAndFeelClass = null;
	
	public static void main(String[] args) {
		loadArguments(args);
		if (inputFilenames.size() == 0) {
			System.out.println("Usage: java -jar salt9000.jar <options> <input files>");
			System.out.println("  options:");
			System.out.println("    " + JG_LAF_SWITCH + ": use JGoodies L&F, it works only with salt9000-complete.jar .");
			System.out.println("    " + LAF_SWITCH + " <class name>: use specified L&F class.");
			System.out.println("    " + NO_EXIST + ": do not exist after output creation.");
			System.exit(0);
		}
		// Process all specified filenames.
		toBeCompleted = inputFilenames.size();
		for (int f = 0; f < inputFilenames.size(); f++) {
			File inputFile = new File(inputFilenames.get(f));
			if (inputFile.isDirectory()) {
				System.out.println("File " + inputFilenames.get(f) + " is a directory.");
				continue;
			}
			System.out.println("Processing file " + inputFilenames.get(f) + " ...");
			SaltCLI salt = new SaltCLI(inputFile, outputDirParameter);
			salt.processFile();
		}
	}
	
	private File inputFile = null;
	private File outputDir = null;
	private SaltCLI(File inputFile, File outputDir) {
		this.inputFile = inputFile;
		this.outputDir = outputDir;
	}
	
	private static int toBeCompleted = 0;
	private void processFile() {
		// Parse input file.
		String inputFileContent = null;
		try {
			inputFileContent = readFileAsString(inputFile);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Enable to open file: " + inputFile);
			return;
		}
		ANTLRInputStream input = new ANTLRInputStream(inputFileContent);
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
		// Calculate output dir.
		if (outputDir == null) {
			outputDir = new File(inputFile.getParent());
		}
		//
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				createAndDumpSwingFrame(outputDir);
			}
		});
	}
	
	private static final String LAF_SWITCH = "--laf";
	private static final String JG_LAF_SWITCH = "--jg-laf";
	private static final String NO_EXIST = "--no-exit";
	private static final String OUTPUT_DIR = "--output-dir";
	private static boolean exitAfterRender = true;
	private static void loadArguments(String[] args) {
		for (int a = 0; a < args.length; a++) {
			if (args[a].startsWith("-")) {
				if (args[a].equals(LAF_SWITCH)) {
					if (a < args.length-1) {
						a++;
						lookAndFeelClass = args[a];
					}
				} else if (args[a].equals(JG_LAF_SWITCH)) {
					lookAndFeelClass = JGOODIES_PLASTIC_LOOKANDFEEL;
				} else if (args[a].equals(NO_EXIST)) {
					exitAfterRender = false;
				} else if (args[a].equals(OUTPUT_DIR)) {
					if (a < args.length-1) {
						a++;
						outputDirParameter = new File(args[a]);
						if (!outputDirParameter.isDirectory()) {
							System.err.println("The path specified with " + OUTPUT_DIR + " should be a directory.");
							System.exit(1);
						}
					}
				}
			} else {
				inputFilenames.add(args[a]);
			}
		}
	}
	
	private void createAndDumpSwingFrame(File file) {

		// Set JGoodies L&F.
		try {
			if (lookAndFeelClass != null) {
				Class.forName(lookAndFeelClass);
				Class clazz = Thread.currentThread().getContextClassLoader().loadClass(lookAndFeelClass);
				LookAndFeel landf = (LookAndFeel)clazz.newInstance();
				UIManager.setLookAndFeel(landf);
			}
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
			System.err.println("Look&Feel exception.");
			System.exit(1);
		} catch (ClassNotFoundException e) {
			System.err.println("Look&Feel " + lookAndFeelClass + " not found.");
			System.exit(1);
		} catch (InstantiationException e) {
			e.printStackTrace();
			System.exit(1);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			System.exit(1);
		}
		
		SaltSwingVisitor visitor = new SaltSwingVisitor();
		JFrame frame = (JFrame)visitor.visit(tree);
		frame.pack();
		frame.setVisible(true);
		// Render the component to image.
		Container componentToRender = frame.getRootPane();
		BufferedImage image = new BufferedImage(componentToRender.getWidth(), componentToRender.getHeight()
				, BufferedImage.TYPE_INT_RGB);
		componentToRender.paint(image.getGraphics());
		// Write image to file.
		String inputFilename = inputFile.getName();
		String outputFilename = inputFilename;
		int i = inputFilename.lastIndexOf('.');
		if (i != -1) {
			outputFilename = inputFilename.substring(0, i);
		}
		File outputFile = new File(outputDir.getPath() + File.separatorChar + outputFilename + ".png");
		try {
			ImageIO.write(image, "PNG", outputFile);
			System.out.println("   ... file " + outputFile + " has been generated.");
		} catch (IOException e1) {
			e1.printStackTrace();
			System.out.println("Enable to write to file: " + inputFile);
			System.exit(1);
		}
		toBeCompleted--;
		if (exitAfterRender && toBeCompleted == 0)
			System.exit(0);
	}
	
	// Utils.
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
