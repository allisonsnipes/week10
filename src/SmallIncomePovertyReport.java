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

	public static void main(String[] args) {
		if (args.length != 2) {
			System.out.println("You must have (2) runtime arguements for SmallIncomePovertyReport: data source file path and the number of records in the data file.");
			System.exit(1);
		}
		
		BufferedReader br;
		File f = new File(args[0]);
		System.out.println("File: " + f.getAbsolutePath() + "\n");
		
		int state, totalPopulation, childrenPopulation, childrenPovertyPopulation;
		String line;
		double percent;
		
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
			System.out.printf("%02s %12s %20s %25s %15s\n", "State", "Population", "Child Population", "Children in Povery Population", "Percentage of Child Poverty");
			System.out.printf("%02s %12s %20s %25s %15s\n" , "-----", "----------", "----------------", "-------------------------", "----------------");
			
			while((line = br.readLine()) != null) {
				String[] delim = line.split("(\\s)+");
				state = Integer.parseInt(delim[0]);
				totalPopulation = Integer.parseInt(delim[1]);
				childrenPopulation = Integer.parseInt(delim[2]);
				childrenPovertyPopulation = Integer.parseInt(delim[3]);
				percent = Double.parseDouble(delim[4]);
				line += String.format("%02d", state);
				line += String.format("%, 12d", totalPopulation);
				line += String.format("%, 20d", childrenPopulation);
				line += String.format("%, 25d", childrenPovertyPopulation);
				line += String.format("%15.2f", percent);
				System.out.println(line);
			}
		} catch (FileNotFoundException e) {
			System.out.println("There is an error:" + e.getMessage() + " .");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("There is an error:" + e.getMessage() + " .");
			e.printStackTrace();
		}	 
	}
}
