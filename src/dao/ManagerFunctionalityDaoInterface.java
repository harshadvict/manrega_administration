package dao;

import java.sql.Connection;
import java.util.ArrayList;

import model.manregaProjectWork;
import model.manregaWorkLocation;
import model.workerSkill;

interface ManagerFunctionalityDaoInterface {
	public void newWorkDao(manregaProjectWork workObj,Connection conn);
	public ArrayList<manregaProjectWork> allWorkView(String id,Connection conn);
	public void addSkill(workerSkill skillObj,Connection Conn);
	public void addLocation(manregaWorkLocation locationObj,Connection Conn);
	public void deleteWork(Connection conn,Long id );
	public void deleteLocation(Connection conn,Long id );
	public void deleteSkill(Connection conn,Long id );
	public ArrayList<workerSkill> showSkill(Connection conn);
	public ArrayList<workerSkill> showSkill();
	public void showWork(Long Skill_id);
	public ArrayList<manregaWorkLocation> showLocation();
}
