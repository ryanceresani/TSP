package operators;
import structs.*;

public class FitnessThread implements Runnable {
	
	private Tour tour;
	
	public FitnessThread(Tour tour){
		this.tour = tour;
	}
	
	@Override
	public void run() {
		tour.recalcFitness();
	}
}
