package operators;

import java.util.concurrent.ThreadLocalRandom;
import structs.City;
import structs.Tour;

/**
 * @author Ryan Ceresani & Tyler Sefcik
 *
 */
public class Mutator {

	//Mutates a tour
	public static Tour swapMutate(Tour tour, double rate){
		//Check against the designated Mutation Rate
		if(ThreadLocalRandom.current().nextDouble() <= rate){
			//Generate two random indexes
			int indexC1 = ThreadLocalRandom.current().nextInt(tour.getSize());
			int indexC2 = 0;
			do {
				indexC2 = ThreadLocalRandom.current().nextInt(tour.getSize());
			}while(indexC2 == indexC1);

			//Swap the locations
			City swap = tour.getCity(indexC1);
			tour.setCity(indexC1, tour.getCity(indexC2));
			tour.setCity(indexC2, swap);
			tour.recalcFitness();
		}
		return tour;
	}
}
