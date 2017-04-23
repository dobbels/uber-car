package car;

import java.util.ArrayList;
import java.util.Arrays;

public class PoolOfCar {
	
	static ArrayList<Car> cars = new ArrayList<Car>( Arrays.asList(
			new Car("Renault", "Megane", "blue", "ABC-121", new Location(40.437056,-3.706269), 0),
			new Car("Renault", "Megane", "blue", "ABC-122", new Location(40.437056,-3.706269), 1),
			new Car("Renault", "Megane", "blue", "ABC-123", new Location(40.437056,-3.706269), 2),
			new Car("Renault", "Megane", "blue", "ABC-124", new Location(40.437056,-3.706269), 3),
			new Car("Renault", "Megane", "blue", "ABC-125", new Location(40.437056,-3.706269), 4),
			new Car("Renault", "Megane", "blue", "ABC-126", new Location(40.437056,-3.706269), 5),
			new Car("Renault", "Megane", "blue", "ABC-127", new Location(40.437056,-3.706269), 6),
			new Car("Renault", "Megane", "blue", "ABC-128", new Location(40.437056,-3.706269), 7)));

	

	
	static public Car getCar(int carId) {
		for (Car c : cars) {
			if (c.getId() == carId)
				return c;
		}
		return null;
	}
	
	static public ArrayList<Car> getCars(){
		return cars;
	}
	
}
