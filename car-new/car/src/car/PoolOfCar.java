package car;

public class PoolOfCar {
	
	static Car car = new Car("Renault", "Megane", "BCDE-123", new Location(40.437056,-3.706269), 1);
	
	static public Car getCar() {
		return car;
	}
	
	
	
}
