package structs;

import java.util.ArrayList;

/**
 * @author Ryan Ceresani & Tyler Sefcik
 *
 */
public class Population {
	int populationSize;
	ArrayList<Tour> tours;
	
	public Population(int popSize){
		this.populationSize = popSize;
		tours = new ArrayList<Tour>(populationSize);
		
	}
	
	public void addTour(Tour newTour){
		tours.add(newTour);
		}
	
	public Tour getTour(int index){
		return tours.get(index);
	}
	
	public Tour fittest(){
		Tour fittest = tours.get(0);
		for (int i = 1; i < tours.size(); i++) {
			if(tours.get(i).getFitness() > fittest.getFitness()){
				fittest = tours.get(i);
			}
		}
		return fittest;
	}
}
