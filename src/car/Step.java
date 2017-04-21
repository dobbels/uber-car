package car;
import java.util.concurrent.TimeUnit;


public class Step {
	
	long distance = -1;
	Location end;

	public void setDistance(long distance) {
		this.distance = distance;
	}
	
	public long getDistance() {
		return this.distance;
	}
	
	public void setLocation(Location end) {
		this.end = end;
	}
	
	public Location getLocation() {
		return this.end;
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
