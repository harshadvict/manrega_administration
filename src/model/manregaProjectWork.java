package model;



public class manregaProjectWork {
	
	
	//local variable of the class
	private long work_id;
	private workerSkill skill;
	private manregaWorkLocation location;
	private int worker_no;
	private String work_duartion;
	private long pay;
	private String manager_name;
	private String manager_id;
	private String work_name;
	private int currently_working_worker;
	
	//constructor of the class
	public manregaProjectWork(long work_id, workerSkill skill, manregaWorkLocation location, int worker_no,
			String work_duartion, long pay, String manager_name,String manager_id ,String work_name) {
		//super();
		this.work_id = work_id;
		this.skill = skill;
		this.location = location;
		this.worker_no = worker_no;
		this.work_duartion = work_duartion;
		this.pay = pay;
		this.manager_name = manager_name;
		this.manager_id=manager_id;
		this.work_name=work_name;
	}
	
	
	//getters and setters
	public long getWork_id() {
		return work_id;
	}

	public void setWork_id(long work_id) {
		this.work_id = work_id;
	}
	public workerSkill getSkill() {
		return skill;
	}
	public void setSkill(workerSkill skill) {
		this.skill = skill;
	}
	public manregaWorkLocation getLocation() {
		return location;
	}
	public void setLocation(manregaWorkLocation location) {
		this.location = location;
	}
	public int getWorker_no() {
		return worker_no;
	}
	public void setWorker_no(int worker_no) {
		this.worker_no = worker_no;
	}
	public String getWork_duartion() {
		return work_duartion;
	}
	public void setWork_duartion(String work_duartion) {
		this.work_duartion = work_duartion;
	}
	public long getPay() {
		return pay;
	}
	public void setPay(long pay) {
		this.pay = pay;
	}
	public String getManager_name() {
		return manager_name;
	}
	public void setManager_name(String manager_name) {
		this.manager_name = manager_name;
	}
    public String getManager_id() {
		return manager_id;
	}
    public void setManager_id(String manager_id) {
		this.manager_id = manager_id;
	}


	public String getWork_name() {
		return work_name;
	}


	public void setWork_name(String work_name) {
		this.work_name = work_name;
	}


	public int getCurrently_working_worker() {
		return currently_working_worker;
	}


	public void setCurrently_working_worker(int currently_working_worker) {
		this.currently_working_worker = currently_working_worker;
	}
	
	
	
}
