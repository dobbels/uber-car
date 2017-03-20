package car_package;

import java.io.IOException;
//import java.io.PrintWriter;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class XmlServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String myTripId = request.getParameter("tripId");            //Storing the received inputs from the server
		String myFromLocation =  request.getParameter("from");
		String myToAddress = request.getParameter("to");
		out.println("Dispatch is recognized, your trip id is:" + myTripId +" your location is: " + myFromLocation
				+ " your destination is: " + myToAddress);            //Prints a message to server
				 
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

	}
}
