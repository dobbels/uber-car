import java.util.concurrent.TimeUnit;


public class Direction {
	
	int distance = -1;

	public void setDistance(int distance) {
		this.distance = distance;
	}
	
	public int getDistance() {
		return this.distance;
	}
	
	public boolean advance(int move) throws InterruptedException {
		TimeUnit.SECONDS.sleep(1);
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
