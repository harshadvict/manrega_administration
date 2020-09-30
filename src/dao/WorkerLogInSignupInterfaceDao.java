package dao;

import model.manregaWorkerDetails;

interface WorkerLogInSignupInterfaceDao {
	public void LoadingWorkerData(manregaWorkerDetails workerObj);
	
	public boolean WorkerSignIn(manregaWorkerDetails worker);
	
}
