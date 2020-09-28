package model;

public class manregaWorkLocation {
	
	
    //local variable of the class
	private long location_id;
	private String location_name;

	
	//constructor of the class
	public manregaWorkLocation(long location_id, String location_name) {
		//super();
		this.location_id = location_id;
		this.location_name = location_name;
	}
	
	//getters and setters
	public long getLocation_id() {
		return location_id;
	}
	public void setLocation_id(long location_id) {
		this.location_id = location_id;
	}
	public String getLocation_name() {
		return location_name;
	}
	public void setLocation_name(String location_name) {
		this.location_name = location_name;
	}
	
}
