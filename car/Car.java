import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;

public class Car {
	
	public enum State {
        REQUESTED, OCCUPIED, FREE
    };

	private State state = State.FREE;
	private Location location;
	private Trip trip = null;
	private int id;
	private String licensePlate, brand, type;

	private String managingServer = "https://uber-server.herokuapp.com/";

    public void setTrip(Trip trip) {
        this.trip = trip;
    }
		
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
	
	public void passengerGetOut()throws IOException {
		this.state = State.FREE;
		sendTripMessage(messageType.END);
	}
	
	public boolean atStartDestination(){
		return this.location == this.trip.getFrom();
	}
	
	public boolean atEndDestination(){
		return this.location == this.trip.getTo();
	}

    /**
     *
     * @return succes : returns false if registration failed
     * @throws IOException
     */
	public boolean register() throws IOException {
	    // the JSON-load contains carId, location and address as a String in format ID - LAT - LONG - x.x.x.x:yyyy/somepath
        URL url = new URL(managingServer + "/api/trip/car/register");
        URLConnection con = url.openConnection();
        HttpURLConnection http = (HttpURLConnection) con;

        boolean result = true;

        try {
            http.setRequestMethod("POST");
            http.setDoOutput(true); // Indicates that we are going to send data over the connection

            byte[] out = ("{\"id\":" + "\""
                    + this.licensePlate + " - "
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

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String output;
            StringBuffer response = new StringBuffer();

            while ((output = in.readLine()) != null) {
                response.append(output);
            }
            in.close();

            //printing result from response
            System.out.println(response.toString());

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

//        URL url = new URL("http://httpbin.org/");
        URL url = new URL(managingServer + "api/trip/" + mType);
        URLConnection con = url.openConnection();
        HttpURLConnection http = (HttpURLConnection) con;

        try {
            http.setRequestMethod("POST");
            http.setDoOutput(true); // Indicates that we are going to send data over the connection
//
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

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String output;
            StringBuffer response = new StringBuffer();

            while ((output = in.readLine()) != null) {
                response.append(output);
            }
            in.close();

            //printing result from response
            System.out.println(response.toString());
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
}
