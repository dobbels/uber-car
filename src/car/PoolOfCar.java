package car;

import java.util.ArrayList;
import java.util.Arrays;

public class PoolOfCar {
	
	static ArrayList<Car> cars = new ArrayList<Car>( Arrays.asList(
			new Car("Renault", "Megane", "ABC-121", "blue", new Location(40.437056,-3.706269), 1),
			new Car("Renault", "Megane", "ABC-122", "blue", new Location(40.437056,-3.706269), 2),
			new Car("Renault", "Megane", "ABC-123", "blue", new Location(40.437056,-3.706269), 3),
			new Car("Renault", "Megane", "ABC-124", "blue", new Location(40.437056,-3.706269), 4),
			new Car("Renault", "Megane", "ABC-125", "blue", new Location(40.437056,-3.706269), 5),
			new Car("Renault", "Megane", "ABC-126", "blue", new Location(40.437056,-3.706269), 6),
			new Car("Renault", "Megane", "ABC-127", "blue", new Location(40.437056,-3.706269), 7),
			new Car("Renault", "Megane", "ABC-128", "blue", new Location(40.437056,-3.706269), 8)));

	

	
	static public Car getCar(int i) {
		return cars.get(i);
	}
	
	static public ArrayList<Car> getCars(){
		return cars;
	}
	
}
