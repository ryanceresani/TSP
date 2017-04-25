package operators;

import java.util.concurrent.ThreadLocalRandom;

import core.GA;
import structs.Population;
import structs.Tour;

/**
 * @authors Ryan Ceresani & Tyler Sefcik
 *
 */
public class Tournament {
	
	
	/**
	 * Tournament Selection Method
	 * @param pop, Population we are selecting members from
	 * @return Tour that is most fit
	 */
	public static Tour tournament(Population pop){
		//Create tournament population of Tournament Size
		Population tourney = new Population(GA.TOURNAMENT_SIZE);
		//Randomly add members of the population
		for(int i = 0; i < tourney.getSize(); i++){
			int randomTour = ThreadLocalRandom.current().nextInt(pop.getSize());
			tourney.addTour(pop.getTour(randomTour));
		}	
		//Return the most fit tour in this tourney
		return tourney.fittest();
	}
}
