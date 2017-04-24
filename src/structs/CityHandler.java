package structs;

import java.util.ArrayList;

public class CityHandler {
	private static int numCities;
	private static ArrayList<City> cities;
	public static CityHandler instance;
	
	private CityHandler(ArrayList<City> cities){
		setCities(new ArrayList<City>(cities));
		numCities = cities.size();
	}
	
	public void startCityHandler(ArrayList<City> cities){
		instance = new CityHandler(cities);
	}
	
	public CityHandler getInstance(){
		return instance;
	}

	public static int getNumCities() {
		return numCities;
	}

	public static ArrayList<City> getCities() {
		return cities;
	}

	public static void setCities(ArrayList<City> newCities) {
		cities = newCities;
	}
}
