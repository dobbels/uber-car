package model;
/**
 * Created by steven on 15/03/17.
 */
public class Trip {
    private int id;
    private Location from;
    private Location to;

    public Trip(int id, Location from, Location to) {
        setId(id);
        setFrom(from);
        setTo(to);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Location getFrom() {
        return from;
    }

    public void setFrom(Location from) {
        this.from = from;
    }

    public Location getTo() {
        return to;
    }

    public void setTo(Location to) {
        this.to = to;
    }
}
