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
			
			//to know the previous work id
			String previousSql="select work_id from worker where Id=?";
			PreparedStatement previousStmt=con.prepareStatement(previousSql);
			
			previousStmt.setLong(1, Long.parseLong(WorkerId));
			
			ResultSet previousRs=previousStmt.executeQuery();
			
			previousRs.next();
			Long PreviousWorkId=previousRs.getLong(1);
			
			//to do from here take worker id at first method call
			String sql="update worker set skill_id=?,work_id=? where id=?";
			
			PreparedStatement stmt =con.prepareStatement(sql);
			
			stmt.setLong(1,SkillId);
			stmt.setLong(2,	workId);
			stmt.setLong(3, Long.parseLong(WorkerId));
			
			int i=stmt.executeUpdate();
			
			//change to currently working worker part
			
			//sql statement to know no of worker working
			String sql1="select worker_working from work where id=?";
			
			PreparedStatement stmt2=con.prepareStatement(sql1);
			
			stmt2.setLong(1,PreviousWorkId);
			
			ResultSet rs1=stmt2.executeQuery();
			rs1.next();
			
			Long worker_working=rs1.getLong(1);
			
			worker_working=worker_working-1;
		
			//sql statement to update the worker_working for the work
			String Sql2="update work set worker_working=? where id=?";
			
			PreparedStatement stmt3=con.prepareStatement(Sql2);
			stmt3.setLong(1,worker_working);
			stmt3.setLong(2, PreviousWorkId);
			stmt3.executeUpdate();
			
			
			//to increase the worker working of the new work
			
			String sqlnew="select worker_working from work where id=?";
			
			PreparedStatement stmtnew=con.prepareStatement(sqlnew);
			
			stmtnew.setLong(1,workId);
			
			ResultSet newRs=stmtnew.executeQuery();
			newRs.next();
			
			Long worker_working_new=newRs.getLong(1);
			
			worker_working_new=worker_working_new+1;
		
			//sql statement to update the worker_working for the work
			String SqlnewUpdate="update work set worker_working=? where id=?";
			
			PreparedStatement stmtnewUpdate=con.prepareStatement(SqlnewUpdate);
			stmtnewUpdate.setLong(1,worker_working_new);
			stmtnewUpdate.setLong(2,workId);
			stmtnewUpdate.executeUpdate();
			
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
		//	System.out.println("for checking purpose");
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
			
			//System.out.println("for checking purpose"+WorkId);
			
			PreparedStatement stmt2=con.prepareStatement(sql1);
			stmt2.setLong(1, WorkId);
			
			ResultSet rs1=stmt2.executeQuery();
			
			rs1.next();
			//storing worker no to be decreased
			Long worker_working=rs1.getLong(1);
			
			//System.out.println(worker_working);
			worker_working=worker_working-1;
			
			//System.out.println("for checking purpose"+worker_working);
			
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
			
			
			//workerSkill skillObj=null;
			
			//manregaWorkLocation locationObj=null;
			
			String sql="select work_id from worker where id=?";
			
			PreparedStatement stmt=con.prepareStatement(sql);
			stmt.setLong(1, Long.parseLong(workerId));
			ResultSet rs=stmt.executeQuery();
			rs.next();
			
			Long workId=rs.getLong(1);
			
			String sql1="select work.id,work.work_name,skill.skill_name,work.work_duration,work.pay,manager.name,location.name from work inner join skill on work.skill_id=skill.skill_id inner join location on work.location_id=location.id inner join manager on work.manager_id=manager.id where work.id=?";
			
			PreparedStatement stmt1=con.prepareStatement(sql1);
			
			stmt1.setLong(1, workId);
			
			ResultSet rs1=stmt1.executeQuery();
			
			rs1.next();
			
			//workObj.setWork_id(rs.getLong(1));
			//workObj.setWork_name(rs.getString(2));
			//skillObj.setSkill_name(rs1.getString(3));
			workerSkill skillObj =new workerSkill(0,rs1.getString(3));
			//workObj.setWork_duartion(rs.getString(4));
			//workObj.setPay(rs.getLong(5));
			//workObj.setManager_name(rs.getString(6));
			//locationObj.setLocation_name(rs1.getString(7));
			manregaWorkLocation locationObj=new manregaWorkLocation(0, rs1.getString(7));
			manregaProjectWork workObj= new manregaProjectWork(rs1.getLong(1),skillObj, locationObj, 0,rs1.getString(4),rs1.getLong(5),rs1.getString(6), null,rs1.getString(2));

			
			return workObj;
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

}
