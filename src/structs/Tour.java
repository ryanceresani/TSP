package structs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Tour {
	
	ArrayList<City> tour;
	int fitness;
	
	/**
	 * Tour Constuctors
	 */
	
	/**
	 * Create blank tour
	 */
	public Tour(){
		tour = new ArrayList<City>();
		fitness = 0;
	}
	
	public Tour(ArrayList<City> tourList){
		tour = new ArrayList<City>(tourList);
		fitness = 0;
	}
	
	public Tour(Tour cloneTour){
		this.tour = new ArrayList<City>(cloneTour.tour);
		fitness = cloneTour.getFitness();
	}
	
	public void setCity(int index, City city){
		tour.set(index, city);
	}
	
	public City getCity(int index){
		return tour.get(index);
	}
	
	public void addSubTour(Tour subTour){
		tour.addAll(subTour.getTour());
	}
	
	public Tour getSubTour(int fromIndex, int toIndex){
		ArrayList<City> subTourList = new ArrayList<City>(tour.subList(fromIndex, toIndex));
		return new Tour(subTourList);
	}
	
	private Collection<? extends City> getTour() {
		return this.tour;
	}

	public boolean containsCity(City city){
		if(tour.contains(city)){
			return true;
		}
		return false;
	}
	public int getSize(){
		return tour.size();
	}
	
	public int getFitness() {
		if(fitness == 0){
			calcFitness();
		}
		return this.fitness;
	}

	private void calcFitness() {
		if(fitness == 0){
			for (int i = 0; i < tour.size(); i++) {
				if(i != tour.size()-1){
					fitness += tour.get(i).distanceTo(tour.get(i+1));
				}
				else {
					fitness += tour.get(i).distanceTo(tour.get(0));
				}
			}
		}
	}
	
	public static Tour genTour() {
		ArrayList<City> newTourList = CityHandler.getCities();
		Collections.shuffle(newTourList);
		Tour newTour = new Tour(newTourList);
		return newTour;
	}
	
}
