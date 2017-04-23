package car;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletContextEvent;

public class CarListener implements javax.servlet.ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		MainThread t = new MainThread();
		new Thread(t).start();

//        ArrayList<MainThread> ts = new ArrayList<>();

//        for (int i=0;i<PoolOfCar.getCars().size();i++)
//            ts.add(i, new MainThread(PoolOfCar.getCar(i).getId()));

//        for (int i=0;i<PoolOfCar.getCars().size();i++)
//            new Thread(ts.get(i)).start();

    }

}
