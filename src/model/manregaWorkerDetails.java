package model;

public class manregaWorkerDetails {

	//local class variable
	private String worker_id;
	private String worker_name;
	private String worker_password;
	private workerSkill skill;
	private manregaProjectWork work;
	private Long manager_id;
	
	
	//constructor of the class
	
	public manregaWorkerDetails(String worker_id, String worker_name, String worker_password, workerSkill skill,
			manregaProjectWork work, Long manager_id) {
		//super();
		this.worker_id = worker_id;
		this.worker_name = worker_name;
		this.worker_password = worker_password;
		this.skill = skill;
		this.work = work;
		this.manager_id=manager_id;
	}
	
	
	
	//getters and setters
	
	public String getWorker_id() {
		return worker_id;
	}
	public void setWorker_id(String worker_id) {
		this.worker_id = worker_id;
	}
	public String getWorker_name() {
		return worker_name;
	}
	public void setWorker_name(String worker_name) {
		this.worker_name = worker_name;
	}
	public String getWorker_password() {
		return worker_password;
	}
	public void setWorker_password(String worker_password) {
		this.worker_password = worker_password;
	}
	public workerSkill getSkill() {
		return skill;
	}
	public void setSkill(workerSkill skill) {
		this.skill = skill;
	}
	public manregaProjectWork getWork() {
		return work;
	}
	public void setWork(manregaProjectWork work) {
		this.work = work;
	}
	public Long getManager_id() {
		return manager_id;
	}



	public void setManager_id(Long manager_id) {
		this.manager_id = manager_id;
	}
	
	
}
