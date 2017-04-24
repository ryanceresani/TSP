package structs;

import java.util.HashMap;

/**
 * @author Ryan Ceresani & Tyler Sefcik
 *
 */
public class City {
	private int num;
	private long x;
	private long y;
	
	//Remember distances to different cities
	HashMap<Integer, Integer> memoizedDist;


	public City(int num, long x, long y){
		this.num = num;
		this.x = x;
		this.y = y;
	}

	public int getNum() {
		return num;
	}

	public long getX() {
		return x;
	}

	public long getY() {
		return y;
	}

	public long distanceTo(City dest){
		//Check if these cities have been compared before
		if(memoizedDist.containsKey(dest.getNum())){
			return memoizedDist.get(dest.getNum()).intValue();
		}
		else{
			long x1 = this.getX();
			long y1 = this.getY();
			long x2 = dest.getX();
			long y2 = dest.getY();
			
			//Find Euclidian distance between two points
			int distance = (int)Math.round( Math.sqrt((x1-x2)*(x1-x2) + (y1-y2)*(y1-y2)));
			memoizedDist.put(dest.getNum(), distance);
			return distance;
		}
	}
}
