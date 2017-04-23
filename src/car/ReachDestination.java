package car;
import java.io.IOException;


public class ReachDestination extends Event {

	@Override
	void execute(AbstractSimulator simulator) {
		// TODO Auto-generated method stub
		System.out.println("Car " + car.getLicensePlate() + " has reached destination");
		try {
			car.passengerGetOut();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
