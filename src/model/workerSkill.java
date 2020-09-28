package model;

public class workerSkill {

	// class local variable
	private long skill_id;
	private String skill_name;
	
	//constructor of the class
	
	public workerSkill(long skill_id, String skill_name) {
		//super();
		this.skill_id = skill_id;
		this.skill_name = skill_name;
	}
	
	
	//getters and setters
	public long getSkill_id() {
		return skill_id;
	}
	public void setSkill_id(long skill_id) {
		this.skill_id = skill_id;
	}
	public String getSkill_name() {
		return skill_name;
	}
	public void setSkill_name(String skill_name) {
		this.skill_name = skill_name;
	}
	
}
