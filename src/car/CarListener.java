package car;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletContextEvent;

public class CarListener implements javax.servlet.ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {

		//TODO make sure that tomcat server is closed every time by opening the Servers view and rightclicking the server instance and choosing Stop, or by hitting the red button in the Servers view whose tooltip says Stop the server.
        // This method will not be called when you terminate the server altogether by hitting the red button in the Console view whose tooltip says Terminate. Terminating (killing) is not the same as stopping.
		System.out.println("Closing down");
		for (Car car : PoolOfCar.getCars()) {
			if (car.isLoggedIn()) {
				try {
					car.logout();
					System.out.println("Car " + car.getId() + " with license plate " + car.getLicensePlate() + " is logged out.");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		for (Car car : PoolOfCar.getCars()) {
			if (!TxtFileHandler.isRegistered(car.getId())) {
				try {
					TxtFileHandler.addRegisteredCar(car.getId());
					car.register();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (!car.isLoggedIn()) {
				try {
					car.login();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
        for (MainThread mt : PoolOfMainThread.getMainThreads())
            new Thread(mt).start();
        System.out.println("Threads started up");
    }

}
