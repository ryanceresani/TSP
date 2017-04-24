package structs;

/**
 * @author Ryan Ceresani & Tyler Sefcik
 *
 */
public class City {
	private int num;
	private long x;
	private long y;
	
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
}
