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
	public static Tour crossover(Tour t1, Tour t2){
		Tour child = new Tour();
		if(ThreadLocalRandom.current().nextDouble() < GA.CROSSOVER_RATE){
			//Do Crossover
			int crossStart = ThreadLocalRandom.current().nextInt(t1.getSize());
			int crossEnd = ThreadLocalRandom.current().nextInt(crossStart, t1.getSize());
			ArrayDeque<City> holdCity = new ArrayDeque<City>();
			Tour t2Crossover = t2.getSubTour(crossStart, crossEnd);

			for (int i = 0; i < t1.getSize(); i++) {
				if(!t2Crossover.containsCity(t1.getCity(i))){
					holdCity.add(t1.getCity(i));
				}
			}
			for (int i = 0; i < t1.getSize(); i++) {
				if(i == crossStart){
					child.addSubTour(t2Crossover);
					i += t2Crossover.getSize();
				}
				else
					child.setCity(i, holdCity.poll());
			}
		}
		else{
			child = new Tour(t1);
		}
		return child;
	}
}
