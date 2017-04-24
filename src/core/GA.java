package core;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import structs.City;

import structs.CityHandler;


/**
 * @author Ryan Ceresani & Tyler Sefcik
 *
 */
public class GA {
  
	public static final double  CROSSOVER_RATE = .7;
	public static final double  MUTATION_RATE = .1;
	public static final int POP_SIZE = 100;
	public static final int TOURNAMENT_SIZE = 10;
  

	public static void main(String[] args) throws IOException {
		//String fileName = args[0];
		String fileName = "dj38.tsp.txt";
		//Read file
		ArrayList<City> cities = new ArrayList<City>();
		cities = readCities(fileName);
		
		printList(cities);		
	}

	//reads cities into the cities arraylist with their number, x coordinate, and y coordinate
	private static ArrayList<City> readCities(String fileName) throws IOException {
		ArrayList<City> cities = new ArrayList<City>();
		
		String[] tokens = new String[3];
		BufferedReader in = new BufferedReader(new FileReader(fileName));
		String line;
		City newCity = null;

		while((line = in.readLine()) != null) {
			if (line.contains("NAME") || line.contains("COMMENT") || line.contains("TYPE") || line.contains("DIMENSION")
					|| line.contains("EDGE_WEIGHT_TYPE") || line.contains("NODE_COORD_SECTION") || line.contains("EOF")) {
				continue;
			} else {
				tokens = line.split(" ");
				newCity = new City(Integer.parseInt(tokens[0]), Double.parseDouble(tokens[1]), Double.parseDouble(tokens[2]));
				if (!newCity.equals(null)) {
					cities.add(newCity);
				}
			}
		}
		in.close();
		return cities;
	}
	
	public static void printList(ArrayList<City> list){
	    for(City elem : list){
	        System.out.println(elem.toString());
	    }
	}
}
