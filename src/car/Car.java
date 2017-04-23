package car;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
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
	private boolean loggedIn = false;
	private String licensePlate, brand, type, color;
	private Step d = new Step();
	private int speed = 400; // km/h
	private int step_position = 0;
	private JSONArray steps;
    //TODO switch-statement in the dispatch depending on state
	
	private final String USER_AGENT = "Mozilla/5.0";

	private String managingServer = "https://uber-server.herokuapp.com";

    public void setTrip(Trip trip) {
        this.trip = trip;
        this.state = State.REQUESTED;
    }
		
	public Car(String brandPar, String typePar, String colorPar, String licensePlatePar, Location locationPar, int idPar){
		brand = brandPar;
		type = typePar;
		color = colorPar;
		licensePlate = licensePlatePar;
		location = locationPar;
		id = idPar;
	}
	
	public int getId() {
		return this.id;
	}
	
	public boolean isLoggedIn() {
		return this.loggedIn;
	}

	public String getLicensePlate() { return this.licensePlate; }

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
		try {
			calculateRoute();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void passengerGetOut() throws IOException {
		this.state = State.FREE;
		sendTripMessage(messageType.END);
		this.location = this.trip.getTo();
	}
	
	public boolean atStartDestination(){
		if (this.trip == null) {
			return false;
		}
		boolean result = this.location == this.trip.getFrom() && this.state == State.REQUESTED;
		if (result) {
			this.state = State.OCCUPIED;
		}
		return result;
	}
	
	public boolean atEndDestination(){
		if (this.trip == null) {
			return false;
		}
		boolean result = this.location == this.trip.getTo() && this.state == State.OCCUPIED;
		if (result) {
			this.state = State.FREE;
		}
		return result;
	}
	
	public boolean atDestination() {
		if (state == State.REQUESTED) {
			return this.location == this.trip.getFrom();
		}
		else if (state == State.OCCUPIED) {
			return this.location == this.trip.getTo();
		}
		else {
			return false;
		}
	}
	
	public boolean login() throws IOException {
	    // the JSON-load contains carId, location and address as a String in format ID - LAT - LONG - x.x.x.x:yyyy/somepath
        URL url = new URL(managingServer + "/api/car/signin");
        URLConnection con = url.openConnection();
        HttpURLConnection http = (HttpURLConnection) con;

        boolean result = true;

        try {
            http.setRequestMethod("POST");
            http.setDoOutput(true); // Indicates that we are going to send data over the connection

            byte[] out = ("{\"id\" : " + "\"" + this.licensePlate + "\","
                    + "\"address\" : " + "\"" + "http://172.20.10.10:8080/car\","
                    + "\"lat\" : " + "\"" + Double.toString(this.location.getLatitude()) + "\","
                    + "\"lon\" : " + "\"" + Double.toString(this.location.getLongitude()) + "\"}").getBytes(StandardCharsets.UTF_8); //TODO put actual ip-address of car
            int length = out.length;

            http.setFixedLengthStreamingMode(length);
            http.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            http.connect();

            OutputStream os = http.getOutputStream();
            os.write(out);

            int statusCode = http.getResponseCode();
            System.out.println("Status " + statusCode);
            

            InputStream is = null;

            if (statusCode >= 200 && statusCode < 400) {
                // Create an InputStream in order to extract the response object
                is = http.getInputStream();
                this.loggedIn = true;
            }
            else {
                result = false;
                is = http.getErrorStream();
            }


            BufferedReader in = new BufferedReader(
                    new InputStreamReader(is));

            String output;
            StringBuffer response = new StringBuffer();

            while ((output = in.readLine()) != null) {
                response.append(output);
            }
            in.close();
            System.out.println("Response to Login: " + response.toString());
        }

//        catch (IOException io) {
//            http.getErrorStream().close();
//        }

        finally {
            http.disconnect();
        }

        return result;
    }

    /**
     *
     * @return success : returns false if registration failed
     * @throws IOException
     */
	public boolean register() throws IOException {
	    // the JSON-load contains carId, location and address as a String in format ID - LAT - LONG - x.x.x.x:yyyy/somepath
        URL url = new URL(managingServer + "/api/car");
        URLConnection con = url.openConnection();
        HttpURLConnection http = (HttpURLConnection) con;

        boolean result = true;

        try {
            http.setRequestMethod("POST");
            http.setDoOutput(true); // Indicates that we are going to send data over the connection

            byte[] out = ("{\"id\" : " + "\"" + this.licensePlate + "\","
                    + "\"make\" : " + "\"" + this.brand + "\","
                    + "\"model\" : " + "\"" + this.type + "\","
                    + "\"color\" : " + "\"" + this.color + "\"}").getBytes(StandardCharsets.UTF_8); //TODO put actual ip-address of car
            int length = out.length;

            http.setFixedLengthStreamingMode(length);
            http.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            http.connect();

            OutputStream os = http.getOutputStream();
            os.write(out);

            int statusCode = http.getResponseCode();
            System.out.println("Status " + statusCode);
            

            InputStream is = null;

            if (statusCode >= 200 && statusCode < 400) {
                // Create an InputStream in order to extract the response object
                is = http.getInputStream();
            }
            else {
                result = false;
                is = http.getErrorStream();
            }


            BufferedReader in = new BufferedReader(
                    new InputStreamReader(is));

            String output;
            StringBuffer response = new StringBuffer();

            while ((output = in.readLine()) != null) {
                response.append(output);
            }
            in.close();
            System.out.println("Response to Registration: " + response.toString());
        }

//        catch (IOException io) {
//            http.getErrorStream().close();
//        }

        finally {
            http.disconnect();
        }

        return result;
    }
	
	public boolean logout() throws IOException {
	    // the JSON-load contains carId, location and address as a String in format ID - LAT - LONG - x.x.x.x:yyyy/somepath
        URL url = new URL(managingServer + "/api/car/signout");
        URLConnection con = url.openConnection();
        HttpURLConnection http = (HttpURLConnection) con;

        boolean result = true;

        try {
            http.setRequestMethod("POST");
            http.setDoOutput(true); // Indicates that we are going to send data over the connection

            byte[] out = ("{\"id\" : " + "\"" + this.licensePlate
                     + "\"}").getBytes(StandardCharsets.UTF_8); //TODO put actual ip-address of car
            int length = out.length;

            http.setFixedLengthStreamingMode(length);
            http.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            http.connect();

            OutputStream os = http.getOutputStream();
            os.write(out);

            int statusCode = http.getResponseCode();
            System.out.println("Status " + statusCode);
            

            InputStream is = null;

            if (statusCode >= 200 && statusCode < 400) {
                // Create an InputStream in order to extract the response object
                is = http.getInputStream();
                this.loggedIn = false;
            }
            else {
                result = false;
                is = http.getErrorStream();
            }


            BufferedReader in = new BufferedReader(
                    new InputStreamReader(is));

            String output;
            StringBuffer response = new StringBuffer();

            while ((output = in.readLine()) != null) {
                response.append(output);
            }
            in.close();
            System.out.println("Response to Logout: " + response.toString());
        }

//        catch (IOException io) {
//            http.getErrorStream().close();
//        }

        finally {
            http.disconnect();
        }

        return result;
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

//        URL url = new URL("http://httpbin.org/");
        URL url = new URL(managingServer + "/api/trip/" + mType);
        URLConnection con = url.openConnection();
        HttpURLConnection http = (HttpURLConnection) con;

        try {
            http.setRequestMethod("POST");
            http.setDoOutput(true); // Indicates that we are going to send data over the connection
//
            byte[] out = ("{\"id\":\"" + Integer.toString(this.trip.getId()) + "\"}").getBytes(StandardCharsets.UTF_8);
            int length = out.length;

            http.setFixedLengthStreamingMode(length);
            http.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            http.connect();

            OutputStream os = http.getOutputStream();
            os.write(out);

            int statusCode = http.getResponseCode();
            System.out.println(this.trip.getId() + " " + statusCode);

            InputStream is = null;

            if (statusCode >= 200 && statusCode < 400) {
                // Create an InputStream in order to extract the response object
                is = http.getInputStream();
            }
            else {
                is = http.getErrorStream();
            }


            BufferedReader in = new BufferedReader(
                    new InputStreamReader(is));
            String output;
            StringBuffer response = new StringBuffer();

            while ((output = in.readLine()) != null) {
                response.append(output);
            }
            in.close();
            System.out.println("Response to " + mType + "-trip message: " + response.toString());
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
    	System.out.println("Car " + this.licensePlate +" is moving");
    	if (d.getDistance() > 0) {
    		System.out.println("Car " + this.licensePlate + " is moving to a destination");
    		int dist = (speed * 1000)/3600;
    		if (d.advance(dist)) {
    			//reached end of step
    			this.location = d.getLocation();
    			next_step();
    		}
    	}
    	else if(d.getDistance() <= 0) {
    		//movement does nothing because car doesn't have destination
    		System.out.println("Car " + this.licensePlate +" has no destination");
    		TimeUnit.SECONDS.sleep(1);
    	}
    	
    	
    }
    
    public boolean assignPassenger() {
    	System.out.println("Car " + this.licensePlate +" has a passenger assigned");
    	if (this.state == State.REQUESTED) {
    		System.out.println("Car " + this.licensePlate + " is calculating passenger destination");
    		try {
				calculateRoute();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		return true;
    	}
    	else {
    		return false;
    	}
    }
    
    public boolean isFree() {
    	return this.state == State.FREE;
    }
    
    public void calculateRoute() throws Exception {
    	String origin = "";
    	String destination = "";
    	if (state == State.REQUESTED) {
    		origin = location.getLocation();
    		destination = trip.getFrom().getLocation();
    	}
    	else if (state == State.OCCUPIED) {
    		origin = trip.getFrom().getLocation();
    		destination = trip.getTo().getLocation();
    	}
    	String url = "https://maps.googleapis.com/maps/api/directions/json?origin="
    			+ origin
    			+ "&destination="
    			+ destination
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
		
		Object a = JSONValue.parse(response.toString());
		JSONObject finalResult = (JSONObject) a;
		
		//print result
		System.out.println(finalResult.get("routes"));
		JSONArray j = (JSONArray) finalResult.get("routes");
		JSONArray i = (JSONArray) ((JSONObject) j.get(0)).get("legs");
		
		steps = (JSONArray) ((JSONObject) i.get(0)).get("steps");
		
		JSONObject step = (JSONObject) steps.get(step_position);
		step_position++;
		JSONObject dis = (JSONObject) step.get("distance");
		Long dist = (Long) dis.get("value");
		d.setDistance(dist.intValue());
		JSONObject loc = (JSONObject) step.get("end_location");
		double lat = (double) loc.get("lat");
		double lng = (double) loc.get("lng");
		
		//System.out.println("steps: ");
		//System.out.println(steps.size());
		
		//System.out.println(i);
		//JSONObject dis = (JSONObject) ((JSONObject) i.get(0)).get("distance");
		//System.out.println(dis);
		//Long dist = (Long) dis.get("value");
		//d.setDistance(dist.intValue());
    	
    }
    
    public void next_step() {
    	System.out.println("Car " + this.licensePlate + "  is in step: " + step_position);
    	if (step_position >= steps.size()) {
    		if (this.state == State.REQUESTED) {
				this.location = this.trip.getFrom();
			}
			else if (this.state == State.OCCUPIED) {
				this.location = this.trip.getTo();
			}
    		step_position = 0;
    	}
    	else {
    		JSONObject step = (JSONObject) steps.get(step_position);
    		step_position++;
    		JSONObject dis = (JSONObject) step.get("distance");
    		Long dist = (Long) dis.get("value");
    		d.setDistance(dist.intValue());
    		JSONObject loc = (JSONObject) step.get("end_location");
    		double lat = (double) loc.get("lat");
    		double lng = (double) loc.get("lng");
    		Location l = new Location(lat, lng);
    		d.setLocation(l);
    	}
    }
    
    
}
