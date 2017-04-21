package car;
import java.io.IOException;
import java.util.concurrent.TimeUnit;


public class Main {

	public static void main(String[] args) throws IOException {
		System.out.println("START TEST");

		Car car = PoolOfCar.getCar();
		Trip trip = new Trip(3, new Location(2,3), new Location(5,6));

//		System.out.println("REGISTER AT SERVER");
		boolean registerResult = car.register();
		// TODO abort everything when response is 400?
		System.out.println("Result of Register: " + Boolean.toString(registerResult));

        System.out.println("SEND TRIP MESSAGE");
        car.setTrip(trip);
        car.sendTripMessage(Car.messageType.START);

		System.out.println("END TEST");
		
//		while (true) {
//			try {
//				TimeUnit.SECONDS.sleep(1);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			System.out.println(car.isFree());
//		}
	}
}
