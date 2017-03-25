import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;


public class Car {
	
	public enum State {
        REQUESTED, OCCUPIED, FREE
    };

	private State state = State.FREE;
	private Location location;
	private Trip trip = null;
	private int id;
	private String licensePlate, brand, type;
	private Direction d = new Direction();
	private int speed = 30; // km/h
    //TODO switch-statement in the dispatch depending on state
	
	private final String USER_AGENT = "Mozilla/5.0";
		
	public Car(String brandPar, String typePar, String licensePlatePar, Location locationPar, int idPar){
		brand = brandPar;
		type = typePar;
		licensePlate = licensePlatePar;
		location =locationPar;
		id = idPar;
	}

	public boolean requestCar(int tripId, Location from, Location to) { //TODO
	    boolean result = false;
	    switch (this.state) {
            case FREE:
                this.state = State.REQUESTED;
                result = true;
                this.trip = new Trip(tripId, from, to);
        }
        return result;
    }
	
	public void passengerGetIn() throws IOException {
		this.state = State.OCCUPIED;
		sendTripMessage(messageType.START);
	}
	
	public void passengerGetOut() throws IOException {
		this.state = State.FREE;
		sendTripMessage(messageType.END);
		this.location = this.trip.getTo();
	}
	
	public boolean atStartDestination(){
		return this.location == this.trip.getFrom();
	}
	
	public boolean atEndDestination(){
		return this.location == this.trip.getTo();
	}

    /**
     *
     * @return success : returns false if registration failed
     * @throws IOException
     */
	public boolean register() throws IOException {
	    // the JSON-load contains carId, location and address as a String in format ID - LAT - LONG - x.x.x.x:yyyy/somepath
        URL url = new URL("http://localhost/api/trip/car/register"); // TODO fill in server address
        URLConnection con = url.openConnection();
        HttpURLConnection http = (HttpURLConnection) con;

        boolean result = true;

        try {
            http.setRequestMethod("POST");
            http.setDoOutput(true); // Indicates that we are going to send data over the connection

            byte[] out = ("{\"id\":" + "\""
                    + Integer.toString(this.id) + " - "
                    + Integer.toString(this.location.getLatitude()) + " - "
                    + Integer.toString(this.location.getLongitude()) + " - "
                    + "x.x.x.x:yyyy/path" + "\"").getBytes(StandardCharsets.UTF_8);
            int length = out.length;

            http.setFixedLengthStreamingMode(length);
            http.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            http.connect();

            OutputStream os = http.getOutputStream();
            os.write(out);

            int statusCode = http.getResponseCode();

            if (statusCode != 200) { // In case of a bad request, nothing changes. Only the statuscode is printed.
                System.out.println(statusCode);
                result = false;
            }
            //TODO same InputStream handling as trip-message
            return result;
        }
//
//        catch (IOException io) {
//            http.getErrorStream().close();
//        }

        finally {
            http.disconnect();
        }
    }

    public enum messageType {
        START, END
    }

    public void sendTripMessage(messageType type) throws IOException, IllegalArgumentException {
        String mType;
        if (type == messageType.START) {
            mType = "start";
        } else if (type == messageType.END) {
            mType = "end";
        } else throw new IllegalArgumentException();


        URL url = new URL("http://localhost/api/trip/" + mType); // TODO fill in server address
        URLConnection con = url.openConnection();
        HttpURLConnection http = (HttpURLConnection) con;

        try {
            http.setRequestMethod("POST");
            http.setDoOutput(true); // Indicates that we are going to send data over the connection

            byte[] out = ("{\"id\":" + "\"" + Integer.toString(this.trip.getId()) + "\"").getBytes(StandardCharsets.UTF_8);
            int length = out.length;

            http.setFixedLengthStreamingMode(length);
            http.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            http.connect();

            OutputStream os = http.getOutputStream();
            os.write(out);

            int statusCode = http.getResponseCode();

            if (statusCode != 200) { // In case of a bad request, nothing changes. Only the statuscode is printed.
                System.out.println(statusCode);
            }
            //TODO : do anything with http.getInputStream() ? and .close() is afterwards ?
            // See answer 4 in http://stackoverflow.com/questions/4767553/safe-use-of-httpurlconnection
            // also javadoc: https://developer.android.com/reference/java/net/HttpURLConnection.html

        }
//
//        catch (IOException io) {
//            http.getErrorStream().close();
//        }

        finally {
            http.disconnect();
        }
	}

	// Some sources used

    // from http://stackoverflow.com/questions/3324717/sending-http-post-request-in-java
    //The post request (in vanilla Java?)
    /*
    URL url = new URL("https://www.example.com/login");
    private URLConnection con = url.openConnection();
    private HttpURLConnection http = (HttpURLConnection) con;
    http.setRequestMethod("POST"); // PUT is another valid option
    http.setDoOutput(true);
    */
    // To do a post with JSON
    /*
    byte[] out = "{\"username\":\"root\",\"password\":\"password\"}" .getBytes(StandardCharsets.UTF_8);
    int length = out.length;

    http.setFixedLengthStreamingMode(length);
    http.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
    http.connect();
    try(OutputStream os = http.getOutputStream()) {
        os.write(out);
    }
	// Do something with http.getInputStream()
     */
    // from http://stackoverflow.com/questions/3038207/java-getinputstream-400-errors
    /*
    // Get the response code
	int statusCode = connection.getResponseCode();

	InputStream is = null;

	if (statusCode >= 200 && statusCode < 400) {
	   // Create an InputStream in order to extract the response object
	   is = connection.getInputStream();
	}
	else {
	   is = connection.getErrorStream();
	}
     */
    
    
    
    public void move() throws InterruptedException {
    	//Movement has to be programmed
    	System.out.println("Car is moving");
    	if (d.getDistance() > 0) {
    		int dist = (speed * 1000)/60;
    		if (d.advance(dist)) {
    			//reached position
    			
    		}
    	}
    	else if(d.getDistance() < 0) {
    		//movement does nothing because car doesn't have destination
    		TimeUnit.SECONDS.sleep(1);
    	}
    	
    	
    }
    
    public boolean assignPassenger() {
    	System.out.println("Passenger is assigned");
    	return this.state == State.REQUESTED;
    }
    
    public void calculateRoute() throws Exception {
    	String origin;
    	String destination;
    	if (state == State.REQUESTED) {
    		origin = location.getLocation();
    		destination = trip.getFrom().getLocation();
    	}
    	else if (state == State.OCCUPIED) {
    		origin = trip.getFrom().getLocation();
    		destination = trip.getTo().getLocation();
    	}
    	String url = "https://maps.googleapis.com/maps/api/directions/json?origin="
    			+ "Toronto" //origin
    			+ "&destination="
    			+ "Montreal" //destination
    			+ "&key=AIzaSyB9NcE7_2N70hVgaG3O7FLlRU4Ahq3Ff7w";

		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");

		//add request header
		con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		
		
		
		Object a=JSONValue.parse(response.toString());
		JSONObject finalResult=(JSONObject) a;
		
		//print result
		System.out.println(finalResult.get("routes"));
		JSONArray j = (JSONArray) finalResult.get("routes");
		JSONArray i = (JSONArray) ((JSONObject) j.get(0)).get("legs");
		//System.out.println(i);
		JSONObject dis = (JSONObject) ((JSONObject) i.get(0)).get("distance");
		//System.out.println(dis);
		d.setDistance((int) dis.get("value"));
    	
    }
    
    
}
