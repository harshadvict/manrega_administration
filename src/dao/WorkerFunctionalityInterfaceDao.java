package dao;

import java.util.ArrayList;

import model.manregaProjectWork;
import model.manregaWorkerDetails;

interface WorkerFunctionalityInterfaceDao {
	public Long calculateAmountDao(String Id);
	public void changeWork(Long workId,Long SkillId,String WorkerId);
	public void LeaveWorkDao(String WorkerId);
	public manregaProjectWork showWork(String workerId);
}
