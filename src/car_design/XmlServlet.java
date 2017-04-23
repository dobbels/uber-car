package car_design;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;                        //Using JSONObject class

import car.*;

public class XmlServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doPost(request, response);         //Automatically calls doPost() method
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
		String str ="";
		String line;
		while (true) {                            //Does the reading operation until the end
			line = reader.readLine();
			if (line == null) {
				break;
			}
			str += line;
		}
		reader.close();
		JSONObject jsonObj;
		int myTripId = 0;
		int myCarId = 0; String licensePlate = "dummy"; // boolean matchingCarFound = false;
		double myLat = -1, myLon = -1, myLat2 = -1, myLon2 = -1;
		System.out.println(str);
		try {
			 jsonObj = new JSONObject(str);     //JSONObject contains the received data
			 myTripId = jsonObj.getInt("trip_id");       //Gets the value of "id" key from JSONObject
			 licensePlate = jsonObj.getString("car_id");
			 myLat = jsonObj.getDouble("lat");
			 myLon = jsonObj.getDouble("lon");
			 myLat2 = jsonObj.getDouble("lat2");
			 myLon2 = jsonObj.getDouble("lon2");
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			System.out.println("Exception is detected!");
			e1.printStackTrace();
		}

		ArrayList<Car> cars = PoolOfCar.getCars();
		Car car = null;

		for (Car c : cars) {
			System.out.println(c.getLicensePlate());
			if (c.getLicensePlate().equals(licensePlate)) {
                car = c;
			    break;
            }
		}

        // If car doesn't exist, send error message (with specific code 410)
        if (car == null) {
            System.out.println("Trip id " + Integer.toString(myTripId) + ": no matching car found!");
            response.setStatus(410);
            response.getWriter().println("Car with this license plate does not exist");
        }
		else if (car.isFree() && car.isLoggedIn()) {
			System.out.println("Dispatch is recognized successfully!");
			System.out.println("myId is: " + myTripId);
			System.out.println("myCarId is: " + myCarId);
            System.out.println("my licensePlate is: " + licensePlate);
            System.out.println("myLat is: " + myLat);
			System.out.println("myLon is: "+ myLon);
			System.out.println("myLat2 is: "+ myLat2);
			System.out.println("myLon2 is: " +myLon2);
			response.setStatus(200);

			Trip trip = new Trip(myTripId, new Location(myLat,myLon), new Location(myLat2,myLon2));
			car.setTrip(trip);
			CarToPassenger ctp = new CarToPassenger();
			ctp.setCar(car);
			PoolOfMainThread.getMainThreads().get(PoolOfCar.getCars().indexOf(car)).getSimulator().addEvent(ctp);
			response.getWriter().println("OK");
		}
		else{
			System.out.println("Error! Something went wrong!");
			response.setStatus(400);
			response.getWriter().println("Car is not available or not logged in");
		}
	}
}
