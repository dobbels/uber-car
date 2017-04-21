package car;

public class Simulator extends AbstractSimulator {
	
    double time;
    
    double now() {
        return time;
    }
    
    void doAllEvents() {
        Event e;
        while ( (e = (Event) events.remove(0)) != null) {
        	//time = e.time;
            e.execute(this);
        }
    }
}