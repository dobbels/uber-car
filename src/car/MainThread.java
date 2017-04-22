package car;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class MainThread implements Runnable {
	
	public static Simulator s = new Simulator();

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("START TEST");
		
		s.events = new ArrayList<Object>();
		Movement m = new Movement();
		m.car = PoolOfCar.getCar(0);
		s.addEvent(m);
		s.doAllEvents();
	}

}
