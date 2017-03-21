package car_design;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class XmlServlet extends HttpServlet {	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");                        //This should be changed to JSON format!!!
		PrintWriter out = resp.getWriter();                      // I will work on it to change the data format
		
		String myTripId = req.getParameter("tripId");            //Storing the received inputs from the server
		String myFromLocationX =  req.getParameter("fromX");
		String myFromLocationY = req.getParameter("fromY");
		String myToAddressX = req.getParameter("toX");
		String myToAddressY = req.getParameter("toY");
		out.println("Dispatch is recognized!  your trip id is: " + myTripId);
		out.println("Your location is: " + "("+ myFromLocationX + "," + myFromLocationY+ ")"); 
		out.println("Your destination is: " + "("+ myToAddressX+ "," + myToAddressY + ")");   //Prints a message to server
		
		
		//Is there any border for the map?
		
	}
}
