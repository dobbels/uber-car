package model;
import java.util.ArrayList;


public class AbstractSimulator {
	
	public ArrayList<Object> events;
	
    public void addEvent(AbstractEvent e) {
        events.add(e);
    }
    
    void terminate()  {
        events.clear();
    }

}
