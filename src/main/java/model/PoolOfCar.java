package model;

import java.util.ArrayList;
import java.util.Arrays;
import io.ably.lib.types.*;

public class PoolOfCar {
	
	static ArrayList<Car> cars = new ArrayList<Car>( Arrays.asList(
			new Car("Renault", "Megane", "blue", "ABC1211", new Location(40.437056,-3.706269), 0),
			new Car("Renault", "Megane", "blue", "ABC1222", new Location(40.437056,-3.706269), 1),
			new Car("Renault", "Megane", "blue", "ABC1233", new Location(40.437056,-3.706269), 2),
			new Car("Renault", "Megane", "blue", "ABC1244", new Location(40.437056,-3.706269), 3),
			new Car("Renault", "Megane", "blue", "ABC1255", new Location(40.437056,-3.706269), 4),
			new Car("Renault", "Megane", "blue", "ABC1266", new Location(40.437056,-3.706269), 5),
			new Car("Renault", "Megane", "blue", "ABC1277", new Location(40.437056,-3.706269), 6),
			new Car("Renault", "Megane", "blue", "ABC1288", new Location(40.437056,-3.706269), 7)));

	

	
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
