import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {
		System.out.println("START TEST");

		Car car = new Car("Renault", "Megane", "ABC-123", new Location(0,0), 1);
		Trip trip = new Trip(5, new Location(2,3), new Location(5,6));

//		car.setTrip(trip);

//		car.sendTripMessage(Car.messageType.START);

		System.out.println("REGISTER AT SERVER");
		boolean registerResult = car.register();
		// TODO test if response is 400 on wrong register and if handled good in Car.

		System.out.println("Result of Register: " + Boolean.toString(registerResult));

		System.out.println("END TEST");

		//TODO at the start of everything run car.register() and if it returns false, repeat the registration or print mistake or something


	}
}
