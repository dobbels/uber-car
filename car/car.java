
public class car {
	
	// enum state {requested, occupied, free};
	// private state myState;
	
	private String brand;
	private int destFrom, destTo, location, Id;
	private boolean passenger;
		
	public car (String brandPar, int destFromPar, int destToPar, int locationPar,
			int IdPar, boolean passengerPar){		
		brand = brandPar;
		destFrom = destFromPar;
		destTo  = destToPar;
		location =locationPar;
		Id = IdPar;
		passenger = passengerPar;
	}
	
	public void passengerGetIn(){
		this.passenger = true;
	}
	
	public void passengerGetOut(){
		this.passenger = false;
	}
	
	public boolean atStartDestination(){
		if(this.location == this.destFrom)
			return true;
		else
			return false;
	}
	
	public boolean atEndDestination(){
		if(this.location == this.destTo)
			return true;
		else
			return false;
	}
	
	/*
	public void saying()
	{
		passengerGetIn();
		System.out.println(this.passenger);		
	}
	
	*/
}
