package core;

import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;

import structs.CityHandler;

public class TSP {
	public static boolean USE_MULTITHREADING;
	
	public static void main(String[] args) throws IOException {
		String fileName = args[0];
		GA.setParams(args);
		CityHandler.setCities(fileName);
		if(CityHandler.getNumCities() > 1000){
			USE_MULTITHREADING = false;
		}
		ThreadMXBean bean = ManagementFactory.getThreadMXBean();
		float start = bean.getCurrentThreadCpuTime();
		//SA.solveWithSA();
		//System.out.println(((bean.getCurrentThreadCpuTime() - start))/1000000000.0);
		//start = bean.getCurrentThreadCpuTime();
		GA.solveWithGA();
		System.out.println(((bean.getCurrentThreadCpuTime() - start))/1000000000.0);
	}

}
