package structs;

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
	 */
	private CityHandler(ArrayList<City> cities){
		cities = new ArrayList<City>(cities);
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
	 * @param newCities - the parsed City information from the .tsp file
	 */
	public static void setCities(ArrayList<City> newCities) {
		if(instance == null){
			startCityHandler();
		}
		cities = newCities;
		numCities = cities.size();
	}
	
	/**
	 * Generates the singleton instance
	 */
	private static void startCityHandler() {
		instance = new CityHandler();
	}
}
