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

import java.io.File;
import java.util.ArrayList;

/**
 * Command line interface to Salt9000.
 * @author mar9000
 */
public class SaltCLI {
	
	/** The list of the file names to process.   */
	private static ArrayList<String> inputFilenames = new ArrayList<String>();
	/** The output dir. received as parameter.   */
	private static File outputDirParameter = null;
	/** An optional Look&Feel to use.   */
	private static String lookAndFeelClass = null;
	
	/** Main method.   */
	public static void main(String[] args) {
		// Read parameters.
		loadArguments(args);
		// Error?
		if (inputFilenames.size() == 0) {
			System.out.println("Usage: java -jar salt9000.jar <options> <input files>");
			System.out.println("  options:");
			System.out.println("    " + JG_LAF_SWITCH + ": use JGoodies L&F, it works only with salt9000-complete.jar .");
			System.out.println("    " + LAF_SWITCH + " <class name>: use specified L&F class.");
			System.out.println("    " + NO_EXIST + ": do not exist after output creation.");
			System.exit(0);
		}
		// Process all specified filenames.
		for (int f = 0; f < inputFilenames.size(); f++) {
			File inputFile = new File(inputFilenames.get(f));
			if (inputFile.isDirectory()) {
				System.out.println("File " + inputFilenames.get(f) + " is a directory.");
				continue;
			}
			System.out.println("Processing file " + inputFilenames.get(f) + " ...");
			SaltProcessor salt = new SaltProcessor(inputFile);
			if (lookAndFeelClass != null)
				salt.setLookAndFeel(lookAndFeelClass);
			String outputFileName = getOutputFileName(inputFile);
			salt.dumpImage(outputFileName);
		}
		if (exitAfterRender)
			System.exit(0);
	}
	
	/** Calculate the output filename for a specific input file.   */
	private static String getOutputFileName(File input) {
		String outputDirName = null;
		String outputFileName = null;
		// Dir. name.
		if (outputDirParameter != null)
			outputDirName = outputDirParameter.getAbsolutePath();
		else
			outputDirName = input.getParent();
		// File name.
		int i = input.getName().lastIndexOf(".");
		if (i != -1)
			outputFileName = input.getName().substring(0, i);
		else
			outputFileName = input.getName();
		//
		return outputDirName + File.separator + outputFileName + ".png";
	}
	
	// ---------- Arguments methods. ----------
	
	private static final String LAF_SWITCH = "--laf";
	private static final String JG_LAF_SWITCH = "--jg-laf";
	private static final String NO_EXIST = "--no-exit";
	private static final String OUTPUT_DIR = "--output-dir";
	private static boolean exitAfterRender = true;
	/** Read arguments passed on the command line.   */
	private static void loadArguments(String[] args) {
		for (int a = 0; a < args.length; a++) {
			if (args[a].startsWith("-")) {
				if (args[a].equals(LAF_SWITCH)) {
					if (a < args.length-1) {
						a++;
						lookAndFeelClass = args[a];
					}
				} else if (args[a].equals(JG_LAF_SWITCH)) {
					lookAndFeelClass = SaltProcessor.JGOODIES_PLASTIC_LOOKANDFEEL;
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
}
