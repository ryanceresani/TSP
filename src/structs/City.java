package structs;

import java.util.HashMap;

/**
 * @author Ryan Ceresani & Tyler Sefcik
 *
 */
public class City {
	private int num;
	private double x;
	private double y;
	
	//Remember distances to different cities to save calculation
	HashMap<Integer, Integer> memoizedDist;

	public City(int num, double x, double y){
		this.num = num;
		this.x = x;
		this.y = y;
		memoizedDist = new HashMap<Integer, Integer>();
	}

	public int getNum() {
		return num;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	/**
	 * Calculates the Euclidian distance between two cities
	 * @param destination City
	 * @return Euclidian distance as an integer
	 */
	public int distanceTo(City dest){
		//Check if these cities have been compared before and use that distance
		if(memoizedDist.containsKey(dest.getNum())){
			return memoizedDist.get(dest.getNum()).intValue();
		}
		else{
			double x1 = this.getX();
			double y1 = this.getY();
			double x2 = dest.getX();
			double y2 = dest.getY();
			//Find Euclidian distance between two points
			int distance = (int)Math.round(Math.sqrt((x1-x2)*(x1-x2) + (y1-y2)*(y1-y2)));
			memoizedDist.put(dest.getNum(), distance);
			return distance;
		}
	}
	
	public String toString(){
		return "Num: " + num + "     X: " + x + "     Y: " + y;
	}
}
