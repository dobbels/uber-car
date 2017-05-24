package model;

abstract class Event extends AbstractEvent {
	
    //double time;
    public Car car;

    public Car setCar(Car car) {
        return this.car = car;
    }

}
