package core;

import java.util.ArrayList;

import operators.Crossover;
import operators.Tournament;
import structs.*;


/**
 * @author Ryan Ceresani & Tyler Sefcik
 *
 */
public class GA {

	//Constants
	public static final double  CROSSOVER_RATE = .7;
	public static final double MUTATION_DECAY_RATE = .005;
	//public static final double TOURNAMENT_GROWTH_RATE = 0;
	private static final int TOURNAMENT_GROWTH_FACTOR = 5;

	//Adaptive Algorithm Parameters
	public static int POP_SIZE = 250;
	public static int TERMINIATION_GEN = 250;
	public static int TOURNAMENT_SIZE = 10;
	public static double  MUTATION_RATE = .05;

	public static Tour fittest;

	public static void solveWithGA(){		
		int generation = 0;
		Population pop = new Population(POP_SIZE, true);
		Tour mostFit = pop.fittest();

		while(generation < TERMINIATION_GEN){
			//Alter
			MUTATION_RATE *= (1-MUTATION_DECAY_RATE);
			if(generation % TOURNAMENT_GROWTH_FACTOR == 0){
				TOURNAMENT_SIZE++;
			}
			pop = evolve(pop);
			if(!pop.fittest().equals(mostFit)){
				if(pop.fittest().getFitness() < mostFit.getFitness()){
					mostFit = pop.fittest();
				}
			}
			generation++;
		}
		fittest = mostFit;
		Population.executor.shutdown();
	}
	/**
	 * Evolves a single population into the next generation
	 * @param pop - population to be evolved
	 * @return next generation of population
	 */
	private static Population evolve(Population pop) {
		Population nextGen = new Population(POP_SIZE);
		//Fittest always moves through
		nextGen.addTour(pop.fittest());
		//Start at 1 to make space for fittest
		for (int i = 1; i < nextGen.getSize(); i++) {
			Tour p1 = Tournament.tournament(pop);
			Tour p2 = Tournament.tournament(pop);
			Tour child = Crossover.crossoverNWOX(p1, p2);
			nextGen.addTour(child);
		}		
		return nextGen;
	}

	public static Tour getFittest(){
		if(fittest != null){
			return fittest;
		}
		return null;
	}

	public static void setParams(String[] args) {
		try{
			TERMINIATION_GEN = Integer.parseInt(args[1]);	
		}
		catch (NullPointerException e){
			TERMINIATION_GEN = 250;
		}
	}
}
