package car;

public class CarToPassenger extends Event {

	@Override
	void execute(AbstractSimulator simulator) {
		// TODO Auto-generated method stub
		System.out.println("Car " + car.getLicensePlate() + " goes to passenger");
		if (car.assignPassenger()) {
			//all good so add move event
			Movement m = new Movement();
			m.car = car;
			simulator.addEvent(m);
		}
	}

}
