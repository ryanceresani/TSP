package core;

import structs.*;

import java.util.concurrent.ThreadLocalRandom;

import operators.*;

/**
 * @author Ryan Ceresani & Tyler Sefcik
 *
 * Implementation of Simulated Annealing for Traveling Salesperson
 * 
 */
public class SA {
	//Constants
	public static final double COOLING_RATE = .0001;
	public static final int MAX_ITERATIONS = 100000;

	//Changing Parameters
	public static int temperature = 10000;

	public static void solveWithSA() {
		//Generate initial tour
		Tour tour = Tour.genTour(); 
		Tour bestTour = tour;
		int iters = 0;
		
		//Continue until we reach our termination amount of times
		while(iters < MAX_ITERATIONS){
			//Clone the current tour
			Tour newTour = new Tour(tour);
			//Swap two locations randomly
			Mutator.swapMutate(newTour, 1);
			//Verify if the new tour is accepted
			if(acceptSwap(tour, newTour, temperature)){
				tour = newTour;
			}
			//Remember the best tour we ever found
			if(tour.getFitness() < bestTour.getFitness()){
				bestTour = new Tour(newTour);
			}
			//Cool the problem
			temperature = (int) Math.max(1, (temperature * 1-COOLING_RATE));
			iters++;
		}
		
		System.out.println(bestTour.getFitness());
		System.out.println(bestTour.toString());
	}

	private static boolean acceptSwap(Tour tour, Tour newTour, int temperature) {
		//If the new tour is better accept it automatically
		if(tour.getFitness() > newTour.getFitness()){
			return true;
		}
		//Use Boltzmann Distribution to sometimes accept worse tours to escape local optima
		else if(Math.exp((tour.getFitness() - newTour.getFitness())/temperature) > ThreadLocalRandom.current().nextDouble()){
			return true;
		}
		return false;
	}
}
