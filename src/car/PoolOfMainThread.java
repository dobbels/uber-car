package car;

import java.util.ArrayList;
import java.util.Arrays;

public class PoolOfMainThread {

//    Function<Car,MainThread> carToThread = x -> (new MainThread(x.getId()));
    static ArrayList<MainThread> ts = convertCarsToMainThreads();

    public static ArrayList<MainThread> convertCarsToMainThreads() {
        ArrayList<MainThread> aot = new ArrayList<>();
        for (int i = 0; i < PoolOfCar.getCars().size(); i++)
            aot.add(i, new MainThread(PoolOfCar.getCar(i).getId()));
        return aot;
    }

    public static ArrayList<MainThread> getMainThreads() {
        return ts;
    }
}
