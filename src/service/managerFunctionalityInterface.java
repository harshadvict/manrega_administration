package service;

import java.sql.Connection;

interface managerFunctionalityInterface {
	public void newWork(Connection conn);
	public void seeAllWork(Connection conn);
	public void addSkill(Connection conn);
	public void addLocation(Connection conn);
	public void deleteWork(Connection conn);
	public void deleteLocation(Connection conn);
	public void deleteSkill(Connection conn);
	public void allWorker(Connection conn,String managerId);
	public void WorkerUnderParticularWork(Connection conn);
}
