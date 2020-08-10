/**
 * Allison Snipes
 * Course 605.201.81 Summer 2020
 * Assignment 10 Question 1
 * 
 *  Project Specs:
 *  Supplied is a data file from the US Census which contains data from US school districts and reports statistics related to child poverty 
 *  (http://www.census.gov/did/www/saipe/data/highlights/2013.html). It is desired to have a summary report which calculate basic statistics at 
 *  the state level.
 *  
 *  @author Allison Snipes
 *  @version 1.0
 *  
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class SmallIncomePovertyReport {

	/**
	 * This is the main program that is in charge of displaying the full report in its entirety. 
	 * @param args runtime arguments needed for the program to execute.
	 */
	public static void main(String[] args) {
		if (args.length != 2) {
			System.out.println("You must have (2) runtime arguements for SmallIncomePovertyReport: data source file path and the number of records in the data file.");
			System.exit(1);
		}
		
		/**
		 * BufferedReader was imported to be able to read from the character input stream by buffering the characters for 
		 * efficient reading. The File object was imported to be able to abstract the file and file path. The InputStreamReader
		 * was imported to be able to serve as the pipeline to receive the data. The FileInputStream is the actual part 
		 * that connect the pipeline to the actual data.
		 */
		
		BufferedReader br;
		File f = new File("/Users/allisonsnipes/605.201.81/Assignmet10_asnipes81/newFile.txt");
		System.out.println("File: " + f.getAbsolutePath() + "\n");
		
		double percent;
		String line;
		
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
			String firstLine = br.readLine();
			String secondLine = br.readLine();
			System.out.println(firstLine);
			System.out.println(secondLine);
			
			while((line = br.readLine()) != null) {
				System.out.println(line);
			}
			br.close();
			
		} catch (FileNotFoundException e) {
			System.out.println("There is an error:" + e.getMessage() + " .");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("There is an error:" + e.getMessage() + " .");
			e.printStackTrace();
		}	 
	}
}
