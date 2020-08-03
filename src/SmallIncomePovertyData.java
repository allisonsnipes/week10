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
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class SmallIncomePovertyData {
	/**
	 * In this coding block the arrays of state, total population, children population, and the population of children in poverty is created.
	 * The BufferedReader object is imported and in charge of reading the input text and buffers characters to read character, arrays, and lines more efficiently. 
	 * The InputStreamReader object is imported and creates a stream to read default character sets. The FileInputStream object is imported to connect to the 
	 * actual datafile via a path. 
	 * 
	 * Lastly the arrays are created that will hold each specific data 
	 */
	private static int povertyData(String filename, int[] state, int[] total_population, int[] children_population, int[] child_poverty_population) throws FileNotFoundException {
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("/Users/allisonsnipes/605.201.81/Assignmet10_asnipes81/SmallAreaIncomePovertyEstData.txt")));
		String line;
		String string_location;
		int fipsStateCode;
		int prevState = 0;
		
		/**
		 * This coding block is in charge of creating the columns from based on the information provided. It will keep doing so until
		 * there is nothing to read in the row. There is a try/catch method in case the file cannot be read. I need to find the columns
		 * for states, total population, children population, population of children living in poverty, and a way to keep track on which
		 * state I am on.
		 * 
		 * @return prevState this is how the program will track which state and/or location it is at in the data
		 * @throws IOException signals that an I/O exception or error has occurred.
		 */
		try {
			while((line = br.readLine()) != null) {
				//using the column index number gather the needed information

				//fips statecode
				string_location = line.substring(0 , 2).trim();
				fipsStateCode = Integer.parseInt(string_location);
				state[fipsStateCode] = fipsStateCode;

				// total us population
				string_location = line.substring(83, 90).trim();
				total_population[fipsStateCode] = fipsStateCode;

				//children population
				string_location = line.substring(92, 99).trim();
				children_population[fipsStateCode] = fipsStateCode;

				//population of children living in poverty
				string_location = line.substring(101, 108).trim();
				child_poverty_population[fipsStateCode] = fipsStateCode;

				//keeping up with the order
				if (fipsStateCode > prevState) {
					prevState = fipsStateCode;
				}
			}
			br.close();
		}
		catch (IOException e) {
			System.out.println("There is an error:" + e.getMessage() + " .");
			e.printStackTrace();
		}

		return prevState;
	}
	
	/**
	 * In this coding block the percentage of children in poverty for each state is calculated via a for loop, the data is formatted into columns, and 
	 * the BufferedWritter is closed. The BufferedWritter object was imported to write text to the output stream efficiently by buffering the characters.
	 * The File object was also imported  as an abstraction of the file's location and file 
	 *
	 */
	private static void dataForReport(String filename, int prevState, int[] state, int[] total_population, int[] children_population, int[] child_poverty_population) throws IOException {
		File pd = new File("/Users/allisonsnipes/605.201.81/Assignmet10_asnipes81/SmallAreaIncomePovertyEstData.txt");
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(pd)));
		double percent;
		String line;

		for( int i = 1; i <= prevState; i++) {
			percent = 100 * (child_poverty_population[i] / children_population[i]);
			line = String.format("%03d %16d %16d %16d %12.5f", state[i], total_population[i], children_population[i], child_poverty_population[i], percent);
			bw.write(line + "\n");
		}
		bw.close();
		System.out.println("You may find the report located at: " + pd.getAbsolutePath());
	}

	/**
	 * This is where the program comes together and all the states are populated with their specific data.
	 * 
	 * @param args the arguments needed to run the program
	 * @param prevState the previous state in the row of data
	 * @param state identifies the state where the data was observed
	 * @param total_population represents the total population from the data provided
	 * @param children_population represents the children population based on the data provided
	 * @param child_poverty_population represents the population of children in poverty based on the data provided
	 * @throws IOException signals that an I/O exception or error has occurred.
	 */
	public static void main(String[] args) {
		if(args.length != 3) {
			System.out.println("You must have (3) runtime arguements for SmallIncomePovertyData: data source file path, the destination file path, and the number of records in the data file.");
			System.exit(1);
		}
		
		int[] state, total_population, children_population, child_poverty_population;
		int prevState = 0;
		
		state = new int[100];
		total_population = new int[100];
		children_population = new int[100];
		child_poverty_population = new int[100];
		
		try {
			prevState = povertyData(args[0], state, total_population, children_population, child_poverty_population);
			dataForReport(args[1], prevState, state, total_population, children_population, child_poverty_population);
		} catch (FileNotFoundException e) {
			System.out.println("There is an error:" + e.getMessage() + " .");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("There is an error:" + e.getMessage() + " .");
			e.printStackTrace();
		}	
	}
}
