package car;

import java.util.ArrayList;

public class MainThread implements Runnable {
	
	public static Simulator s = new Simulator();
//	private int carId;

//	public MainThread(int carId) {
//		this.carId = carId;
//	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("START TEST");
		
		s.events = new ArrayList<>();
		Movement m = new Movement();
		m.car = PoolOfCar.getCar(0); //TODO change
		s.addEvent(m);
		s.doAllEvents();
	}

}
