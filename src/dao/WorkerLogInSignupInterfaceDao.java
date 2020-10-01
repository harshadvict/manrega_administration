package dao;

import model.manregaWorkerDetails;

interface WorkerLogInSignupInterfaceDao {
	public void LoadingWorkerData(manregaWorkerDetails workerObj);
	
	public boolean WorkerSignIn(manregaWorkerDetails worker);
	
	public String KnowName(String Id);
	
}
