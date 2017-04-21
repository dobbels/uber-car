package car;
import java.util.ArrayList;


public class AbstractSimulator {
	
	public ArrayList<Object> events;
	
    public void addEvent(AbstractEvent e) {
        events.add(e);
    }
    
    AbstractEvent cancel(AbstractEvent e)  {
        throw new java.lang.RuntimeException("Method not implemented");
    }

}
