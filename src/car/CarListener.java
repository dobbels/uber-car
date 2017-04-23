package car;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletContextEvent;

public class CarListener implements javax.servlet.ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {

		//TODO first close down threads and then logout
        //TODO use this to log out cars ??
        //TODO make sure that tomcat server is closed every time by opening the Servers view and rightclicking the server instance and choosing Stop, or by hitting the red button in the Servers view whose tooltip says Stop the server.
        // This method will not be called when you terminate the server altogether by hitting the red button in the Console view whose tooltip says Terminate. Terminating (killing) is not the same as stopping.
		System.out.println("stopped");
		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		MainThread t = new MainThread();
		new Thread(t).start();
        System.out.println("Thread started up");

//        ArrayList<MainThread> ts = new ArrayList<>();

//        for (int i=0;i<PoolOfCar.getCars().size();i++)
//            ts.add(i, new MainThread(PoolOfCar.getCar(i).getId()));

//        for (int i=0;i<PoolOfCar.getCars().size();i++)
//            new Thread(ts.get(i)).start();

    }

}
