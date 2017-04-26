package structs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import core.GA;
import core.TSP;
import operators.*;

public class Tour {

	ArrayList<City> tour;
	int fitness;
	FitnessThread ft;

	/**
	 * Create blank tour
	 */
	public Tour(){
		tour = new ArrayList<City>();
		fitness = 0;
		ft = new FitnessThread(this);

	}

	/**
	 * Create new Tour from an existing ArrayList of Cities
	 * @param tourList - 
	 */
	public Tour(ArrayList<City> tourList){
		tour = new ArrayList<City>(tourList);
		fitness = 0;
		ft = new FitnessThread(this);

	}

	/**
	 * Clones an existing tour
	 * @param cloneTour - Tour to be cloned
	 */
	public Tour(Tour cloneTour){
		this.tour = new ArrayList<City>(cloneTour.tour);
		fitness = cloneTour.getFitness();
		ft = new FitnessThread(this);
	}

	/**
	 * @param index - index of city to be assigned
	 * @param city - City to be assigned
	 */
	public void setCity(int index, City city){
		tour.set(index, city);
	}

	public City getCity(int index){
		return tour.get(index);
	}

	/** Adds a subTour to this tour
	 * @param subTour - sub-section of other tour to be added directly
	 */
	public void addSubTour(Tour subTour){
		tour.addAll(subTour.getTour());
	}

	/**
	 * Gets a portion of a tour
	 * @param fromIndex - starting location
	 * @param toIndex - ending location (exclusive)
	 * @return - a subList of Tours
	 */
	public Tour getSubTour(int fromIndex, int toIndex){
		ArrayList<City> subTourList = new ArrayList<City>(tour.subList(fromIndex, toIndex));
		return new Tour(subTourList);
	}

	private Collection<? extends City> getTour() {
		return this.tour;
	}

	/**
	 * Check if this tour contains a specific city
	 * Used for crossover
	 * @param city - to be compared against
	 * @return if the city exists in this tour
	 */
	public boolean containsCity(City city){
		if(tour.contains(city)){
			return true;
		}
		return false;
	}
	public int getSize(){
		return tour.size();
	}

	/**
	 * Returns tour's fitness
	 * If fitness has not yet been calculated, it calculates it first
	 * @return the fitness of this tour
	 */
	public int getFitness() {
		if(fitness == 0){
			Population.executor.execute(ft);
		}
		return this.fitness;
	}

	/**
	 * Sums the distances between cities
	 * Checks for last city and calculates distance to start
	 * @return 
	 */
	public void recalcFitness() {
		fitness = 0;
		for (int i = 0; i < tour.size(); i++) {
			if(i != tour.size()-1){
				fitness += tour.get(i).distanceTo(tour.get(i+1));
			}
			else {
				fitness += tour.get(i).distanceTo(tour.get(0));
			}
		}
	}

	/**
	 * Shuffles tours for generating original population
	 * @return a randomly sorted tour
	 */
	public static Tour genTour() {
		ArrayList<City> newTourList = CityHandler.getCities();
		Collections.shuffle(newTourList);
		Tour newTour = new Tour(newTourList);
		return newTour;
	}

	public String toString(){
		StringBuilder tourString = new StringBuilder();
		for(City c : tour){
			tourString.append(c.getNum());
			tourString.append(" ");
		}
		return tourString.toString();
	}

	public void addCity(City city) {
		tour.add(city);
	}
}
