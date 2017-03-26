package car;
public class Location {
    private double latitude;
    private double longitude;

    public Location(double myLat, double myLon) {
        setLatitude(myLat);
        setLongitude(myLon);
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public boolean equals(Location loc) {
        return (loc.getLatitude() == this.latitude &&
        loc.getLongitude() == this.longitude);
    }
    
    public String getLocation() {
    	String result = "";
    	result = result + latitude + "," + longitude;
    	return result;
    }
}
