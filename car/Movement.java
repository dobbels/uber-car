
public class Movement extends Event {

	@Override
	void execute(AbstractSimulator simulator) {
		// TODO Auto-generated method stub
		try {
			car.move();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		simulator.addEvent(this);
	}

}
