package model;

import io.ably.lib.realtime.AblyRealtime;
import io.ably.lib.realtime.Channel;
import io.ably.lib.realtime.CompletionListener;
import io.ably.lib.types.AblyException;
import io.ably.lib.types.ErrorInfo;

import java.io.*;
import java.util.Scanner;

public class TxtFileHandler {

    /*public static void main(String[] args) {
        try {
            addRegisteredCar(1);
            addRegisteredCar(2);
            addRegisteredCar(3);
            addRegisteredCar(4);
            addRegisteredCar(5);
            addRegisteredCar(6);
            addRegisteredCar(7);
            addRegisteredCar(8);
            addRegisteredCar(9);
            removeRegisteredCar(1);
            removeRegisteredCar(4);
            removeRegisteredCar(8);
            removeRegisteredCar(6);
            addRegisteredCar(4); // should not write 4 two times
            System.out.println(isRegistered(1));
            System.out.println(isRegistered(5));
            System.out.println(isRegistered(9));
            System.out.println(isRegistered(15));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/
	
	public static void main(String[] args) {
		for (Car car : PoolOfCar.getCars()) {
			if (!isRegistered(car.getId())) {
				System.out.println("registering car " + car.getId());
				try {
					if (car.register()) {
						addRegisteredCar(car.getId());
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	/*public static void main(String[] args) {
		AblyRealtime realtime = Constants.getRealtime();
		try {
			Channel channel = realtime.channels.get("/trip/1");
	    	channel.publish("statusChange", "INBOUND", new CompletionListener() {

				@Override
				public void onError(ErrorInfo arg0) {
					// TODO Auto-generated method stub
					System.out.println("Error with reason " + arg0);
				}

				@Override
				public void onSuccess() {
					// TODO Auto-generated method stub
					System.out.println("Message sent");
				}
	    		
	    	});
		} catch (AblyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
	

    public static boolean removeRegisteredCar(int i) throws IOException { // TODO also throw NotRegisteredException when not registered ?
        File inputFile = new File("registered-cars.txt");
        File tempFile = new File("tmp-cars.txt");

        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

        String lineToRemove = Integer.toString(i);
        String currentLine;

        while((currentLine = reader.readLine()) != null) {
            // trim newline when comparing with lineToRemove
            String trimmedLine = currentLine.trim();
            if(trimmedLine.equals(lineToRemove)) {
                continue;
            }
            writer.write(currentLine + System.getProperty("line.separator"));
        }
        writer.close();
        reader.close();
        return tempFile.renameTo(inputFile);
    }
    public static boolean addRegisteredCar(int i) throws IOException {
        String oldFileName = "registered-cars.txt";
        String tmpFileName = "tmp.txt";

        boolean carAdded = false;

        Scanner scanner = new Scanner(new File(oldFileName));

        BufferedWriter bw = null;

//        try {
            bw = new BufferedWriter(new FileWriter(tmpFileName));


            int currentInt = 0;
            while (scanner.hasNextInt()) {
                currentInt = scanner.nextInt();
                if(currentInt == i) {
                    System.out.println("Car: " + Integer.toString(i) +  " is already registered!!"); //throw new IOException(); // TODO except throw AlreadyRegisteredException when not registered ?
                    carAdded = true;
                }

                if (currentInt > i && !carAdded) {
//                    System.out.println("Add: " + Integer.toString(i) + " " + Integer.toString(currentInt));
                    bw.write(Integer.toString(i)+"\n");
                    bw.write(Integer.toString(currentInt)+"\n");
                    carAdded = true;
                }
                else {
//                    System.out.println("Copy: " + Integer.toString(currentInt));
                    bw.write(Integer.toString(currentInt) + "\n");
                }
            }

            if (!carAdded) {
                bw.write(Integer.toString(i) + "\n");
            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            return carAdded;
//        }
//        finally {
            try {
                if(bw != null)
                    bw.close();
            } catch (IOException e) {
                //
            }
//        }
        // Once everything is complete, delete old file..
        File oldFile = new File(oldFileName);
        oldFile.delete();

        // And rename tmp file's name to old file name
        File newFile = new File(tmpFileName);
        newFile.renameTo(oldFile);

        return carAdded;
    }

    public static boolean isRegistered(int i) {
        boolean isRegistered = false;

        try {
            Scanner scanner = new Scanner(new File("registered-cars.txt"));
            while (scanner.hasNextInt()) {
                if (scanner.nextInt() == i) {
                    isRegistered = true;
                    break;
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return isRegistered;
    }
}