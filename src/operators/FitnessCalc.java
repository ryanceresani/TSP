package operators;
import structs.*;

public class FitnessCalc implements Runnable {
	
	private Tour tour;
	
	public FitnessCalc(Tour tour){
		this.tour = tour;
	}
	
	@Override
	public void run() {
		tour.recalcFitness();
	}
}
