package operators;

import java.util.ArrayDeque;
import java.util.concurrent.ThreadLocalRandom;

import core.GA;
import structs.City;
import structs.Tour;

/**
 * @author Ryan Ceresani & Tyler Sefcik
 *
 */
public class Crossover {

	//Implementing Non-Wrapping Order Crossover
	public static Tour crossoverNWOX(Tour t1, Tour t2){
		Tour child = new Tour();
		//With probability of Crossover Rate, Do Crossover
		if(ThreadLocalRandom.current().nextDouble() < GA.CROSSOVER_RATE){
			
			//Determine random cross-section
			int crossStart = ThreadLocalRandom.current().nextInt(t1.getSize());
			int crossEnd = ThreadLocalRandom.current().nextInt(crossStart, t1.getSize());
			
			//Initialize Queue for reinsertions
			ArrayDeque<City> holdCity = new ArrayDeque<City>();
			//Take parent 2 cross-section as a whole
			Tour t2Crossover = t2.getSubTour(crossStart, crossEnd);

			//If city is not in parent 2 crossover-section, add it to holding queue
			for (int i = 0; i < t1.getSize(); i++) {
				if(!t2Crossover.containsCity(t1.getCity(i))){
					holdCity.add(t1.getCity(i));
				}
			}
			
			//Iterate through child adding from holding queue until cross section
			for (int i = 0; i < t1.getSize(); i++) {
				//If we reach cross-section, insert entire subTour from parent 2
				if(i == crossStart){
					child.addSubTour(t2Crossover);
					i += t2Crossover.getSize();
				}
				else
					child.addCity(holdCity.poll());
			}
			//Add any remaining cities left to the end
			while(!holdCity.isEmpty()){
				child.addCity((holdCity.poll()));
			}
		}
		//If we do not perform cross-over, parent 1 passes through.
		else{
			child = new Tour(t1);
		}	
		//Pass through the mutator and return
		return Mutator.mutate(child);
	}
}
