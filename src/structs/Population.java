package structs;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Ryan Ceresani & Tyler Sefcik
 *
 */
public class Population {
	int populationSize;
	ArrayList<Tour> tours;
	Tour fittest = null;
	public static ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
	/**
	 * Construct blank population
	 * @param popSize
	 */
	public Population(int popSize){
		this(popSize, false);
	}

	/**
	 * Construct Population
	 * @param popSize - the size of the populations (a parameter of the GA)
	 * @param init - determines if a random first population must be created
	 */
	public Population(int popSize, boolean init){
		this.populationSize = popSize;
		tours = new ArrayList<Tour>(populationSize);
		fittest = null;
		if(init){
			generateFirstPopulation();
		}
	}

	/**
	 * Adds a tour to the end of the population
	 * @param newTour - the Tour to be added
	 */
	public void addTour(Tour newTour){
		if(tours.size() < populationSize)
			tours.add(newTour);
	}

	/**
	 * Retrieve Tour from population
	 * @param index - index of the Tour to be retrieved
	 * @return Tour
	 */
	public Tour getTour(int index){
		return tours.get(index);
	}

	/**
	 * Iterate through tours and determine their fitness
	 * @return the most fit Tour in this population
	 */
	public Tour fittest(){
		if(fittest == null){
			fittest = tours.get(0);
			for (int i = 1; i < tours.size(); i++) {
				if(tours.get(i).getFitness() < fittest.getFitness()){
					fittest = tours.get(i);
				}
			}
		}
		return fittest;
	}

	/**
	 * @return the size of this population
	 */
	public int getSize(){
		return this.populationSize;
	}


	/**
	 * Fills population with random tours
	 */
	private void generateFirstPopulation() {
		for (int i = 0; i < populationSize; i++) {
			tours.add(i, Tour.genTour());
		}
	}
}
