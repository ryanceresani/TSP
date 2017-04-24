package structs;

import java.util.ArrayList;

public class Tour {
	
	ArrayList<City> tour;
	int fitness;
	
	public Tour(){
		tour = new ArrayList<City>();	
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
	
	public int getFitness() {
		// TODO Auto-generated method stub
		return fitness;
	}
	
	
}
