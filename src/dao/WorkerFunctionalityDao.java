package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;

import model.manregaProjectWork;
import model.manregaWorkLocation;
import model.manregaWorkerDetails;
import model.workerSkill;
import utility.ConnectionManager;

public class WorkerFunctionalityDao implements WorkerFunctionalityInterfaceDao {

	@Override
	public Long calculateAmountDao(String Id) {
		// TODO Auto-generated method stub
		
		LocalDate dateToday=LocalDate.now();
		
		
		ConnectionManager conn=new ConnectionManager();
		
		try {
			Connection con=conn.getConnection();
			
			String sql="select worker.id,worker.work_joining,work.pay from work inner join worker on worker.work_id=work.id and worker.id=?";
			PreparedStatement stmt =con.prepareStatement(sql);
			stmt.setLong(1, Long.parseLong(Id));
			
			ResultSet rs=stmt.executeQuery();
			
			
			//while(rs.next()) {
				rs.next();
				LocalDate Date;
				Date=rs.getDate(2).toLocalDate();
				Long pay=rs.getLong(3);
			//}
			
			
			//calculate price by counting no of day*pay per day
			
			Period noOfDays=Period.between(Date, dateToday);
			int days=noOfDays.getDays();
			
			pay=pay*days;
			return pay;
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public void changeWork(Long workId,Long SkillId,String WorkerId) {
		//method to change the work of the worker
		ConnectionManager conn=new ConnectionManager();
		
		try {
			Connection con=conn.getConnection();
			
			//to do from here take worker id at first method call
			String sql="update worker set skill_id=?,work_id=? where id=?";
			
			PreparedStatement stmt =con.prepareStatement(sql);
			
			stmt.setLong(1,SkillId);
			stmt.setLong(2,	workId);
			stmt.setLong(3, Long.parseLong(WorkerId));
			
			int i=stmt.executeUpdate();
			
			if(i==1) {
				System.out.println("--------------------------------------------------------------------------------");
				System.out.println("Work Change Successfull");
				System.out.println("--------------------------------------------------------------------------------");
			}
			
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void LeaveWorkDao(String WorkerId) {
		// worker work leaving Dao part
		
		ConnectionManager conn=new ConnectionManager();
		
		try {
			Connection con=conn.getConnection();
			
			//sql to know the work id of the worker
			String sql="select work_id from worker where id=?";
			
			//sql statement to know no of worker working
			String sql1="select worker_working from work where id=?";
			
			//sql statement to update the worker_working for the work
			String Sql3="update work set worker_working=? where id=?";
			
			//dalete the worker
			String sql4="delete from worker where id=?";
			
			PreparedStatement stmt1=con.prepareStatement(sql);
			stmt1.setLong(1,Long.parseLong(WorkerId));
			
			ResultSet rs=stmt1.executeQuery();
			rs.next();
			//storing work id need to decrease the worker count
			Long WorkId=rs.getLong(1);
			
			PreparedStatement stmt2=con.prepareStatement(sql1);
			stmt2.setLong(1, WorkId);
			
			ResultSet rs1=stmt2.executeQuery();
			
			rs1.next();
			//storing worker no to be decreased
			Long worker_working=rs1.getLong(1);
			worker_working=worker_working-1;
			
			PreparedStatement stmt3=con.prepareStatement(Sql3);
			stmt3.setLong(1, worker_working);
			stmt3.setLong(2, WorkId);
			int i=stmt3.executeUpdate();
			
			if(i==1) {
				//worker deletion part
				PreparedStatement stmt4=con.prepareStatement(sql4);
				
				stmt4.setLong(1, Long.parseLong(WorkerId));
				
				stmt4.executeUpdate();
				return;
			}
			else {
				System.out.println("check line 137 of WorkerFunctionalityDao class update failed");
				return;
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return;
	}

	@Override
	public manregaProjectWork showWork(String workerId) {
		// worker work detail part
		
		ConnectionManager conn=new ConnectionManager();

		try {
			Connection con=conn.getConnection();
			
			
			workerSkill skillObj=null;
			
			manregaWorkLocation locationObj=null;
			
			String sql="select work_id from worker where id=?";
			
			PreparedStatement stmt=con.prepareStatement(sql);
			stmt.setLong(1, Long.parseLong(workerId));
			ResultSet rs=stmt.executeQuery();
			rs.next();
			
			Long workId=rs.getLong(1);
			
			String sql1="select work.id,work.work_name,skill.skill_name,work.work_duration,work.pay,manager.name,location.name from work inner join skill on work.skill_id=skill.skill_id inner join location on work.location_id=location.id inner join manager on work.manager_id=manager.id where id=?";
			
			PreparedStatement stmt1=con.prepareStatement(sql1);
			
			stmt1.setLong(1, workId);
			
			ResultSet rs1=stmt1.executeQuery();
			
			rs1.next();
			
			//workObj.setWork_id(rs.getLong(1));
			//workObj.setWork_name(rs.getString(2));
			skillObj.setSkill_name(rs.getString(3));
			//workObj.setWork_duartion(rs.getString(4));
			//workObj.setPay(rs.getLong(5));
			//workObj.setManager_name(rs.getString(6));
			locationObj.setLocation_name(rs.getString(7));
			
			manregaProjectWork workObj= new manregaProjectWork(rs.getLong(1), skillObj, locationObj, 0,rs.getString(4),rs.getLong(5),rs.getString(6), null, null);

			
			return workObj;
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

}
