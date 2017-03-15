import java.io.OutputStream;
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
    //TODO switch-statement in the dispatch depending on state
		
	public Car(String brandPar, String typePar, String licensePlatePar, Location locationPar, int idPar){
		brand = brandPar;
		type = typePar;
		licensePlate = licensePlatePar;
//		destFrom = destFromPar;
//        destFrom = locationPar;
//		destTo  = destToPar;
//        destTo = locationPar;
		location =locationPar;
		id = idPar;
	}

	public void requestCar(int tripId) {
	    // TODO
	    this.state = State.REQUESTED;
    }
	
	public void passengerGetIn(){
		this.state = State.OCCUPIED;
	}
	
	public void passengerGetOut(){
		this.state = State.FREE;
	}
	
	public boolean atStartDestination(){
		return this.location == this.trip.getFrom();
	}
	
	public boolean atEndDestination(){
		return this.location == this.trip.getTo();
	}

	public void register() {
	    // with carId, address and location
    }

	public void sendStartTripMessage(int id) {
        // TODO put id in JSON type
        // http-post
        // handle response (if not 200, do we do anything?)
        URL url = new URL("http://localhost/api/trip/start"); // TODO fill in server address
        private URLConnection con = url.openConnection();
        private HttpURLConnection http = (HttpURLConnection) con;
        http.setRequestMethod("POST");
        http.setDoOutput(true); // Indicates that we are going to send data over the connection

        byte[] out = ("{\"id\":" + Integer.toString(this.tripId)).getBytes(StandardCharsets.UTF_8);
        int length = out.length;

        http.setFixedLengthStreamingMode(length);
        http.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        http.connect();
        try(OutputStream os = http.getOutputStream()) {
            os.write(out);
        }
        // Do something with http.getInputStream()
	}

    public void sendEndTripMessage(int id) {
	    // TODO put id in JSON type
        // http-post
        // handle response (if not 200, do we do anything?)
    }

    /*TODO now: add all the properties and methods from doc to here
            fill in http-requests (post) in sendStartTripMessage and sendEndTripMessage
    */

    // from http://stackoverflow.com/questions/3324717/sending-http-post-request-in-java
    //The post request (in vanilla Java?)
    /*
    URL url = new URL("https://www.example.com/login"); // TODO fill in server address
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
    //TODO throw

//    public Location moveCar() {} ; // TODO add methods to simulate

	/*
	public void saying()
	{
		passengerGetIn();
		System.out.println(this.passenger);		
	}
	
	*/
}
