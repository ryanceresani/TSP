package core;

import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;

import structs.CityHandler;

public class TSP {

	public static void main(String[] args) throws IOException {
		String fileName = "fi10639.tsp";
		CityHandler.setCities(fileName);
		ThreadMXBean bean = ManagementFactory.getThreadMXBean();
		float start = bean.getCurrentThreadCpuTime();
		SA.solveWithSA();
		System.out.println(bean.getCurrentThreadCpuTime() - start);
		start = bean.getCurrentThreadCpuTime();
		GA.solveWithGA();
		System.out.println(bean.getCurrentThreadCpuTime() - start);
	}

}
