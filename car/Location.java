public class Location {
    private int latitude;
    private int longitude;

    public Location(int lat, int lon) {
        setLatitude(lat);
        setLongitude(lon);
    }

    public int getLatitude() {
        return latitude;
    }

    public void setLatitude(int latitude) {
        this.latitude = latitude;
    }

    public int getLongitude() {
        return longitude;
    }

    public void setLongitude(int longitude) {
        this.longitude = longitude;
    }

    public boolean equals(Location loc) {
        return (loc.getLatitude() == this.latitude &&
        loc.getLongitude() == this.longitude);
    }
}
