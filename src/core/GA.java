package core;


import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
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
	public static final int POP_SIZE = 500;
	public static final int TERMINIATION_GEN = 500;
	public static final int DECAY_GEN = 50;
	
	//Adaptive Algorithm Parameters
	public static int TOURNAMENT_SIZE = 25;
	public static double  MUTATION_RATE = .1;
  

	public static void main(String[] args) throws IOException {
		int generation = 0;
		//String fileName = args[0];
		String fileName = "dj38.tsp.txt";
		
		
		CityHandler.setCities(readCities(fileName));
		Population pop = new Population(POP_SIZE, true);
		Tour mostFit = pop.fittest();
		
		while(generation < TERMINIATION_GEN){
			if(0 == generation % DECAY_GEN){
				MUTATION_RATE -= .01;
				TOURNAMENT_SIZE += 2;
			}
			pop = evolve(pop);
			if(pop.fittest().getFitness() < mostFit.getFitness()){
				mostFit = pop.fittest();
			}
			generation++;
		}
		
		/*
		 * For parameter use the following - 
		 */
//		PrintStream out = new PrintStream(new FileOutputStream("output.txt", true));
//		System.setOut(out);
//		System.out.println("Fitness: " + mostFit.getFitness() + " Pop Size: " + POP_SIZE + " END T_SIZE: " + TOURNAMENT_SIZE + " END MUT_RATE: " + MUTATION_RATE + " DECAY_GEN: " + DECAY_GEN );
		System.out.println(mostFit.getFitness());
		System.out.println(mostFit.toString());
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


	//reads cities into the cities arraylist with their number, x coordinate, and y coordinate
	private static ArrayList<City> readCities(String fileName) throws IOException {
		ArrayList<City> cities = new ArrayList<City>();
		
		String[] tokens = new String[3];
		BufferedReader in = new BufferedReader(new FileReader(fileName));
		String line;
		City newCity = null;

		while((line = in.readLine()) != null) {
			if (line.contains("NAME") || line.contains("COMMENT") || line.contains("TYPE") || line.contains("DIMENSION")
					|| line.contains("EDGE_WEIGHT_TYPE") || line.contains("NODE_COORD_SECTION") || line.contains("EOF")) {
				continue;
			} else {
				tokens = line.split(" ");
				newCity = new City(Integer.parseInt(tokens[0]), Double.parseDouble(tokens[1]), Double.parseDouble(tokens[2]));
				if (!newCity.equals(null)) {
					cities.add(newCity);
				}
			}
		}
		in.close();
		return cities;
	}
	
	public static void printList(ArrayList<City> list){
	    for(City elem : list){
	        System.out.println(elem.toString());
	    }
	}
}
