package model;

import java.util.ArrayList;
import java.util.Arrays;

public class PoolOfMainThread {

//    Function<Car,MainThread> carToThread = x -> (new MainThread(x.getId()));
    static ArrayList<MainThread> ts = convertCarsToMainThreads();

    public static ArrayList<MainThread> convertCarsToMainThreads() {
        ArrayList<MainThread> aot = new ArrayList<MainThread>();
        for (Car c : PoolOfCar.getCars()) {
            aot.add(new MainThread(c.getId()));
        }
        return aot;
    }

    public static ArrayList<MainThread> getMainThreads() {
        return ts;
    }
}
