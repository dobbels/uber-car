package car;
import java.io.IOException;
import java.util.concurrent.TimeUnit;


public class Main {

	public static void main(String[] args) throws IOException {
		System.out.println("START TEST");

		Car car = PoolOfCar.getCar();
		car.register();

//		System.out.println("REGISTER AT SERVER");
//		boolean registerResult = car.register();
		// TODO abort everything when response is 400?
//		System.out.println("Result of Register: " + Boolean.toString(registerResult));

        /*System.out.println("SEND TRIP MESSAGE");
        //car.setTrip(trip);
        //car.sendTripMessage(Car.messageType.START);

		System.out.println("END TEST");
		
		while (true) {
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(car.isFree());
		}*/

		//TODO at the start of everything run car.register() and if it returns false, repeat the registration or print mistake or something


	}
}
