package model;

import io.ably.lib.types.*;
import io.ably.lib.realtime.*;

public class Constants {
	
	//static private String api = "3lNRpA.JFT09Q:liUh0XqwTismnAcB";
	static private String api = "YmhWtQ.k72laQ:zG8pg-8b_eqUBA_S";
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
