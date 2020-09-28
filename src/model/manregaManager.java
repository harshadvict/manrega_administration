package model;

public class manregaManager {

	//local variable
	private String manager_id;
	private String manager_name;
	private	String manager_password;
	
	
	//constructor of the class
	
	public manregaManager(String manager_id, String manager_password,String manager_name) {
		//super();
		this.manager_id = manager_id;
		this.manager_password = manager_password;
		this.manager_name=manager_name;
	}
	
	//getters and setters
	
	public String getManager_id() {
		return manager_id;
	}
	
	public void setManager_id(String manager_id) {
		this.manager_id = manager_id;
	}
	public String getManager_password() {
		return manager_password;
	}
	public void setManager_password(String manager_password) {
		this.manager_password = manager_password;
	}

	public String getManager_name() {
		return manager_name;
	}

	public void setManager_name(String manager_name) {
		this.manager_name = manager_name;
	}
	
}
