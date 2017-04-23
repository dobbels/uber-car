package car;

import io.ably.lib.types.*;
import io.ably.lib.realtime.*;

public class Constants {
	
	static private String api = "3lNRpA.JFT09Q:liUh0XqwTismnAcB";
	static AblyRealtime realtime = makeNew();
	
	
	public static AblyRealtime makeNew() {
		try {
			return new AblyRealtime(api);
		} catch (AblyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return null;
	}
	
	public static AblyRealtime getRealtime() {
		return realtime;
	}
	
	

}
