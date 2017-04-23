package car;

import java.util.ArrayList;

public class MainThread implements Runnable {
	
	public Simulator s = new Simulator();
	private int carId; //TODO use license plate and when getCar search through the array for license

	public MainThread(int carId) {
		this.carId = carId;
	}

	public int getCarId() {
	    return carId;
    }

    public Simulator getSimulator(){
	    return s;
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
