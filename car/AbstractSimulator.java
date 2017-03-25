import java.util.ArrayList;


public class AbstractSimulator {
	
	ArrayList<Object> events;
	
    void addEvent(AbstractEvent e) {
        events.add(e);
    }
    
    AbstractEvent cancel(AbstractEvent e)  {
        throw new java.lang.RuntimeException("Method not implemented");
    }

}
