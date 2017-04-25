package structs;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author Ryan Ceresani & Tyler Sefcik
 *
 * Singleton class used to manage the cities of a TSP separate from genetic algorithm
 * Holds the cities as they were read and can return them as needed
 */
public class CityHandler {
	private static int numCities;
	private static ArrayList<City> cities;
	public static CityHandler instance = null;

	/**
	 * Private constructor
	 */
	private CityHandler(){
		cities = new ArrayList<City>();
		instance = this;
		numCities = 0;
	}
	
	/**
	 * @param cities - the list of cities
	 * @throws IOException 
	 */
	private CityHandler(String fileName) throws IOException{
		cities = readCities(fileName);
		numCities = cities.size();
	}

	/**
	 * @return the CityHandler
	 */
	public CityHandler getInstance(){
		if(instance == null){
			instance = new CityHandler();
		}
		return instance;
	}

	/**
	 * @return the number of cities in the TSP problem
	 */
	public static int getNumCities() {
		return numCities;
	}

	/**
	 * @return the List of Cities
	 */
	public static ArrayList<City> getCities() {
		return cities;
	}

	/**
	 * @param fileName - the name of the TSP file to be read
	 * @throws IOException 
	 */
	public static void setCities(String fileName) throws IOException {
		if(instance == null){
			startCityHandler(fileName);
		}
		else{
			cities = readCities(fileName);
		}
		numCities = cities.size();
	}
	
	/**
	 * Generates the singleton instance
	 * @throws IOException 
	 */
	private static void startCityHandler(String fileName) throws IOException {
		instance = new CityHandler(fileName);
	}
	
	
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
}
