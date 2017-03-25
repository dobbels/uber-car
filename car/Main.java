import java.util.ArrayList;


public class Main {
	public static void main(String[] args){
		Car car = new Car("Renault", "Megane", "ABC-123", new Location(0,0), 1);
		
		try {
			//car.calculateRoute();
			Simulator s = new Simulator(); 
			s.events = new ArrayList<Object>();
			Movement m = new Movement();
			m.car = car;
			s.addEvent(m);
			s.doAllEvents();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		//TODO at the start of everything run car.register() and if it returns false, repeat the registration or print mistake or something
	}
}
