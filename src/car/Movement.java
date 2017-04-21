package car;

public class Movement extends Event {

	@Override
	void execute(AbstractSimulator simulator) {
		// TODO Auto-generated method stub
		try {
			car.move();
			if (car.atStartDestination()) {
				ReachPassenger rp = new ReachPassenger();
				rp.car = car;
				simulator.addEvent(rp);
			}
			else if (car.atEndDestination()) {
				ReachDestination rd = new ReachDestination();
				rd.car = car;
				simulator.addEvent(rd);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		simulator.addEvent(this);
	}

}
