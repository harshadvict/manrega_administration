package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.manregaManager;
import model.manregaProjectWork;
import model.manregaWorkLocation;
import model.workerSkill;
import utility.ConnectionManager;

public class ManagerFunctionalityDao implements ManagerFunctionalityDaoInterface{
	
	@Override
	public void newWorkDao(manregaProjectWork workObj,Connection conn) {
		//loading new work data in the database 'work' table
		
		String sql="insert into work(id,location_id,skill_id,worker_number,work_duration,pay,manager_id)values(?,?,?,?,?,?,?)";
		
		try {
			PreparedStatement stmt=conn.prepareStatement(sql);
			
			stmt.setLong(1, workObj.getWork_id());
			stmt.setLong(2,workObj.getLocation().getLocation_id());
			stmt.setLong(3, workObj.getSkill().getSkill_id());
			stmt.setLong(4, workObj.getWorker_no());
			stmt.setString(5, workObj.getWork_duartion());
			stmt.setLong(6,workObj.getPay());
			stmt.setString(7,workObj.getManager_id());
			
			int value=stmt.executeUpdate();
			if(value==1) {
				System.out.println("----------------------------------------------------------------------------------");
				System.out.println("work added successfully");
				System.out.println("----------------------------------------------------------------------------------");

			}
			else
				System.out.println("insertion fail");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public ArrayList<manregaProjectWork> allWorkView(String id,Connection conn) {
		//to see all work under manager
		String sql="select work.id,location.name,skill.skill_name,work.worker_number,work.work_duration,work.pay,manager.name from work inner join location on work.location_id=location.id inner join skill on work.skill_id=skill.skill_id inner join manager on work.manager_id=manager.id where work.manager_id=?";
		System.out.println("");
		
		ArrayList<manregaProjectWork> list=new ArrayList<>();
		try {
			PreparedStatement stmt=conn.prepareStatement(sql);
			stmt.setString(1,id);
			ResultSet rs=stmt.executeQuery();
			
			//object for manregaProjectWork class
			manregaProjectWork projectWorkObj=new manregaProjectWork(0, null, null, 0, null, 0, null, null);
			//object for location class
			manregaWorkLocation locationObj=new manregaWorkLocation(0, null);
			//object for skill class
			workerSkill skillObj=new workerSkill(0, null);
			//object for manager 
			//manregaManager managerObj=new manregaManager(null, null,null);
			
			while(rs.next()){
				projectWorkObj.setWork_id(rs.getLong(1));
				locationObj.setLocation_name(rs.getString(2));
				projectWorkObj.setLocation(locationObj);
				skillObj.setSkill_name(rs.getString(3));
				projectWorkObj.setSkill(skillObj);
				projectWorkObj.setWorker_no(rs.getInt(4));
				projectWorkObj.setWork_duartion(rs.getString(5));
				projectWorkObj.setPay(rs.getLong(6));
				//managerObj.setManager_name(rs.getString(7));
				projectWorkObj.setManager_name(rs.getString(7));
				
				//adding object to list
				list.add(projectWorkObj);
			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void addSkill(workerSkill skillObj,Connection Conn) {
		//for adding new skill to the database
		String sql="insert into skill(skill_id,skill_name)values(?,?)";
		try {
			PreparedStatement stmt=Conn.prepareStatement(sql);
			stmt.setLong(1, skillObj.getSkill_id());
			stmt.setString(2, skillObj.getSkill_name());
			
			int value=stmt.executeUpdate();
			if(value==1) {
				System.out.println("----------------------------------------------------------------------------------");
				System.out.println("New SKILL added successfully");
				System.out.println("----------------------------------------------------------------------------------");

			}
			else
				System.out.println("insertion fail");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	@Override
	public void addLocation(manregaWorkLocation locationObj, Connection Conn) {
		//for adding new skill to the database
		String sql="insert into location(id,name)values(?,?)";
		try {
			PreparedStatement stmt=Conn.prepareStatement(sql);
			stmt.setLong(1, locationObj.getLocation_id());
			stmt.setString(2, locationObj.getLocation_name());
			
			int value=stmt.executeUpdate();
			if(value==1) {
				System.out.println("----------------------------------------------------------------------------------");
				System.out.println("New location added successfully");
				System.out.println("----------------------------------------------------------------------------------");

			}
			else
				System.out.println("insertion fail");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void deleteWork(Connection conn, Long id) {
		//function to delete the work
		String sql ="DELETE from work where id=?";
		
		try {
			PreparedStatement stmt=conn.prepareStatement(sql);
			stmt.setLong(1, id);
			
			int value=stmt.executeUpdate();
			if(value==1) {
				System.out.println("----------------------------------------------------------------------------------");
				System.out.println("deleted successfully");
				System.out.println("----------------------------------------------------------------------------------");

			}
			else {
				System.out.println("----------------------------------------------------------------------------------");
				System.out.println("deletion fail");
				System.out.println("----------------------------------------------------------------------------------");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void deleteLocation(Connection conn, Long id) {
		//function to delete the location
				String sql ="DELETE from location where id=?";
				
				try {
					PreparedStatement stmt=conn.prepareStatement(sql);
					stmt.setLong(1, id);
					
					int value=stmt.executeUpdate();
					if(value==1) {
						System.out.println("----------------------------------------------------------------------------------");
						System.out.println("deleted successfully");
						System.out.println("----------------------------------------------------------------------------------");

					}
					else {
						System.out.println("----------------------------------------------------------------------------------");
						System.out.println("Warning:-work is going on this location");
						System.out.println("----------------------------------------------------------------------------------");
						}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
	}

	@Override
	public void deleteSkill(Connection conn, Long id) {
		//function to delete the skill
				String sql ="DELETE from skill where id=?";
				
				try {
					PreparedStatement stmt=conn.prepareStatement(sql);
					stmt.setLong(1, id);
					
					int value=stmt.executeUpdate();
					if(value==1) {
						System.out.println("----------------------------------------------------------------------------------");
						System.out.println("deleted successfully");
						System.out.println("----------------------------------------------------------------------------------");

					}
					else {
						System.out.println("----------------------------------------------------------------------------------");
						System.out.println("Warning:-work is going using this skill");
						System.out.println("----------------------------------------------------------------------------------");
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
		}

	@Override
	public ArrayList<workerSkill> showSkill(Connection conn) {
		return null;
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<workerSkill> showSkill() {
		//method to show all the skill
		ConnectionManager con=new ConnectionManager();
		
		//creating object for workerSkill class
		workerSkill skillObj=new workerSkill(0, null);
		
		//creating arrayList for workerSkill type
		ArrayList<workerSkill> list=new ArrayList<>();
		
		try {
			Connection conn=con.getConnection();
			
			String sql="select * from skill";
			
			PreparedStatement stmt=conn.prepareStatement(sql);
			
			ResultSet rs=stmt.executeQuery();
			
			while(rs.next()) {
				skillObj.setSkill_id(rs.getLong(1));
				skillObj.setSkill_name(rs.getString(2));
				 
				//adding object to the list
				list.add(skillObj);
				
			}
			return list;
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	
	
}