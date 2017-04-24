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
	
	public static Tour tournament(Population pop){
		Population tourney = new Population(GA.TOURNAMENT_SIZE);
		for(int i = 0; i < tourney.getSize(); i++){
			int randomTour = ThreadLocalRandom.current().nextInt(pop.getSize());
			tourney.addTour(pop.getTour(randomTour));
		}	
		return tourney.fittest();
	}
}
