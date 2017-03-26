package car;
import java.util.concurrent.TimeUnit;


public class Direction {
	
	long distance = -1;

	public void setDistance(long distance) {
		this.distance = distance;
	}
	
	public long getDistance() {
		return this.distance;
	}
	
	public boolean advance(int move) throws InterruptedException {
		TimeUnit.SECONDS.sleep(1);
		System.out.println(distance);
		if (distance <= move) {
			distance = 0;
			return true;
		}
		else {
			distance = distance - move;
			return false;
		}
	}
}
