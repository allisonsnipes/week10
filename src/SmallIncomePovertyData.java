/**
 * Allison Snipes
 * Course 605.201.81 Summer 2020
 * Assignment 10 Question 1
 * 
 *  Project Specs:
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
	private static int povertyData(String filename, int[] state, int[] total_population, int[] children_population, int[] child_poverty_population) throws FileNotFoundException {
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("SmallAreaIncomePovertyEstData.txt")));
		String line;
		String string_location;
		int fipsStateCode;
		int prevState = 0;

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
				br.close();
			} 
		}
		catch (IOException e) {
			System.out.println("There is an error. Your entry of " + e.getMessage() + " is out of the index bounds.");
			e.printStackTrace();
		}

		return prevState;
	}
	
	private static void dataForReport(String filename, int prevState, int[] state, int[] total_population, int[] children_population, int[] child_poverty_population) throws IOException {
		File pd = new File("SmallAreaIncomePovertyEstData.txt");
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(pd)));
		double percent;
		String line;
		
		for( int i = 1; i <= prevState; i++) {
			percent = 100 * (child_poverty_population[i] / children_population[i]);
			line = String.format("%-3s %17s %17s %17s %24f %13.5", state[i], total_population[i], children_population[i], child_poverty_population[i], percent);
			bw.write(line + "\n");
		}
		bw.close();
		System.out.println("You may find the report located at: " + pd.getAbsolutePath());
	}




	}
