package core;

import java.io.IOException;

import structs.CityHandler;

public class TSP {
	public static boolean USE_MULTITHREADING;
	
	public static void main(String[] args) throws IOException {
		String fileName = args[0];
		GA.setParams(args);
		SA.setParams(args);
		CityHandler.setCities(fileName);
		GA.solveWithGA();
		SA.solveWithSA(GA.getFittest());	
	}
}
