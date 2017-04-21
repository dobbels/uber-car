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
		int myId = 0;
		double myLat = -1, myLon = -1, myLat2 = -1, myLon2 = -1;   
		try {
			 jsonObj = new JSONObject(str);     //JSONObject contains the received data
			 myId = jsonObj.getInt("trip_id");       //Gets the value of "id" key from JSONObject
			 myLat = jsonObj.getDouble("lat");
			 myLon = jsonObj.getDouble("lon");
			 myLat2 = jsonObj.getDouble("lat2");
			 myLon2 = jsonObj.getDouble("lon2");
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			System.out.println("Exception is detected!");
			e1.printStackTrace();
		} 
		
		Car c = PoolOfCar.getCar();
		System.out.println(c);
		
		if (c.isFree()) {
			System.out.println("Dispatch is recognized successfully!");
			System.out.println("myId is: " + myId);
			System.out.println("myLat is: " + myLat);
			System.out.println("myLon is: "+ myLon);
			System.out.println("myLat2 is: "+ myLat2);
			System.out.println("myLon2 is: " +myLon2);
			response.setStatus(200);
			
			Trip trip = new Trip(myId, new Location(myLat,myLon), new Location(myLat2,myLon2));
			c.setTrip(trip);
			CarToPassenger ctp = new CarToPassenger();
			ctp.car = c;
			MainThread.s.addEvent(ctp);
			response.getWriter().println("OK");
		}
		else{
			System.out.println("Error! Something went wrong!");
			//c.passengerGetOut();
			response.setStatus(400);
			response.getWriter().println("Car is not available");
		}	
	}			
}
