package car;

public class PoolOfCar {
	
	static Car[] cars =  {
		new Car("Renault", "Megane", "ABC-121", new Location(40.437056,-3.706269), 1),
		new Car("Renault", "Megane", "ABC-122", new Location(40.437056,-3.706269), 1),
		new Car("Renault", "Megane", "ABC-123", new Location(40.437056,-3.706269), 1),
		new Car("Renault", "Megane", "ABC-123", new Location(40.437056,-3.706269), 1),
		new Car("Renault", "Megane", "ABC-124", new Location(40.437056,-3.706269), 1),
		new Car("Renault", "Megane", "ABC-125", new Location(40.437056,-3.706269), 1),
		new Car("Renault", "Megane", "ABC-126", new Location(40.437056,-3.706269), 1),
		new Car("Renault", "Megane", "ABC-127", new Location(40.437056,-3.706269), 1)
	};
	

	
	static public Car getCar() {
		return cars[0];
	}
	
	static public Car[] getCars(){
		return cars;
	}
	
}
