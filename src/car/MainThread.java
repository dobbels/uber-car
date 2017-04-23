package car;

import java.util.ArrayList;

public class MainThread implements Runnable {
	
	public Simulator s = new Simulator();
	private int carId;

	public MainThread(int carId) {
		this.carId = carId;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("START TEST");
		
		s.events = new ArrayList<>();
		Movement m = new Movement();
		m.car = PoolOfCar.getCar(carId);
		s.addEvent(m);
		s.doAllEvents();
	}

}
