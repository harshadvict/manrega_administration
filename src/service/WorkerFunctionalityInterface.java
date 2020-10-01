package service;

interface WorkerFunctionalityInterface {
	public Long calculateAmount(String Id);
	public void changeWork(String Id);
	public void LeaveWork(String Id,String pass);
	public void showWork(String workerId);
}
