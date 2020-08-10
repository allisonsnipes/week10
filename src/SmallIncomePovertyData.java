/**
 * Allison Snipes
 * Course 605.201.81 Summer 2020
 * Assignment 10 Question 1
 * 
 *  Project Specs:
 *  Supplied is a data file from the US Census which contains data from US school districts and reports 
 *  statistics related to child poverty (http://www.census.gov/did/www/saipe/data/highlights/2013.html). 
 *  It is desired to have a summary report which calculate basic statistics at the state level.
 *  
 *  @author Allison Snipes
 *  @version 1.0 
 */

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class SmallIncomePovertyData {
	
	/**
	 * First error handling is conducted to see if the file exists by using the File object. The if/else
	 * statement is utilize to show a message if the file will be read or not. The FileReader is used to read
	 * the actual file. The Scanner object is created to be able to read from our original .txt file. The 
	 * Scanner class is required to be imported to use this object. In this coding block the  arrays of state, 
	 * total population, children population, and the population of children in poverty is created to hold the
	 * data. This is where the program comes together and all the states are populated with their specific data.
	 *
	 * @param args the necessary arguments needed to run the program.
	 * @throws IOException signals that an I/O exception or error has occurred.
	 * @throws FileNotFoundException signals that the file cannot be found/read.
	 */
	public static void main(String[] args) throws FileNotFoundException {
		if(args.length != 3) {
			System.out.println("You must have (3) runtime arguements for SmallIncomePovertyData: data source file path, the destination file path, and the number of records in the data file.");
			System.exit(1);
		}
		
		try {
			
			File f = new File("/Users/allisonsnipes/605.201.81/Assignmet10_asnipes81/SmallAreaIncomePovertyEstData.txt");
			
			if(!f.exists()) {
			System.out.println("The file does not exisit, program exiting.");
			System.exit(1);
			} else {
			System.out.println("Data file will be read");
			}
			
			FileReader readData = new FileReader(f);
			
			Scanner input = new Scanner(readData);
			java.text.DecimalFormat comma = new java.text.DecimalFormat("#,###");
			
			int code = 0;
			String state;
			String[] district = new String[13486];
			String[] districtName = new String[13486];
			int[] population = new int[57];
			int[] totalPopulation = new int[57];
			int[] childrenPopulation = new int[57];
			int[] childrenPovPopulation = new int[57];
			String[] tag = new String[100];
			
			for (int i = 0; i < 13486; i++) {
				districtName[i] = "";
				district[i] = "";
			}
			
			for(int i = 0; i < 57; i++) {
				totalPopulation[i] = 0;
				population[i] = 0;
				childrenPopulation[i] = 0;
				childrenPovPopulation[i] = 0;
			}
			
			while (input.hasNext()) {
				state = input.next();
				int index = Integer.parseInt(state);
				district[index] = input.next();
				
				while (!input.hasNextInt()) {
					districtName[code] += "" + input.next();
				}
				
				totalPopulation[index] += input.nextInt();
				childrenPopulation[index] += input.nextInt();
				childrenPovPopulation[index] += input.nextInt();
				code++;
			}
			/**
			 * Here the intermediate file with the data points are created. The BufferedWriter object is imported
			 * to be able to write text to the output stream efficiently. The OutputStreamWriter is imported 
			 * and offers default character encoding. The FileOutputStream as an output data stream to write to 
			 * the file that is highlighted by the object.
			 */
			File newFile = new File("/Users/allisonsnipes/605.201.81/Assignmet10_asnipes81/newFile.txt");
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter( new FileOutputStream(f)));
			bw.write("State\tTotal Population\tChildren Population\tChildren in Poverty Population\tChild Poverty");
			bw.write("-----------\t--------------\t----------------------\t-------------------\n");
			
			for (int i = 0; i < 57; i++) {
				if(childrenPovPopulation[i] > 0) {
					double percent = (double) (childrenPovPopulation[i] * 100/childrenPopulation[i]);
					String line = String.format("%02d\t%10s\t%16s\t%24s\t\t %.2f\n", i, comma.format(totalPopulation[i]), comma.format(childrenPopulation[i]), comma.format(childrenPovPopulation[i]), percent);
					bw.write(line);
				}
			}
			bw.close();
		
		} catch (FileNotFoundException e) {
			System.out.println("There is an error:" + e.getMessage() + " .");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("There is an error:" + e.getMessage() + " .");
			e.printStackTrace();
		}	 
	}
}