package car_design;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

import org.json.JSONException;                         
import org.json.JSONObject;                              //Using JSONObject class

public class XmlServlet extends HttpServlet {	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
				
		BufferedReader reader = new BufferedReader(new InputStreamReader(req.getInputStream()));
		StringBuffer buff = new StringBuffer();
		String line;
		while (true) {
			line = reader.readLine();
			if (line == null) {
				break;
			}
			buff.append(line);
		}
		String reqStr = buff.toString();
		reader.close();
		JSONObject jsonObj;
		int myId = 0;
		double myLat = -1, myLon = -1, myLat2 = -1, myLon2 = -1;
		try {
			 jsonObj = new JSONObject(reqStr);
			 myId = jsonObj.getInt("id");
			 myLat = jsonObj.getDouble("lat");
			 myLon = jsonObj.getDouble("lon");
			 myLat2 = jsonObj.getDouble("lat2");
			 myLon2 = jsonObj.getDouble("lon2");
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			System.out.println("Exception is detected!");
			e1.printStackTrace();
		} 
		
		if (myLat >= 0 && myLat <= 18 && myLon >= 0 && myLon <=18 && myLat2 >= 0 && myLat2 <= 18
			&& myLon2 >=0 && myLon2<=18){
			System.out.println("Dispatch is recognized successfully!");
			System.out.println("myId is: " + myId);
			System.out.println("myLat is: " + myLat);
			System.out.println("myLon is: "+ myLon);
			System.out.println("myLat2 is: "+ myLat2);
			System.out.println("myLon2 is: " +myLon2);
			resp.setStatus(200);
		}
		else{
			System.out.println("Error! Something went wrong!");
			resp.setStatus(400);
		}
		 						
	}	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);         //Automatically calls doGet() method
	}
}
