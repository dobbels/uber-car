package car;
import java.io.IOException;


public class ReachPassenger extends Event {

	@Override
	void execute(AbstractSimulator simulator) {
		// TODO Auto-generated method stub
		System.out.println("Reach passenger");
		try {
			car.passengerGetIn();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
