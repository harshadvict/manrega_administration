package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import model.manregaWorkerDetails;
import utility.ConnectionManager;

public class WorkerLogInSignupDao implements WorkerLogInSignupInterfaceDao{

	@Override
	public void LoadingWorkerData(manregaWorkerDetails workerObj) {
		
		LocalDate current_date=LocalDate.now();
		
		// to load data in the database of the worker
		
		ConnectionManager con =new ConnectionManager();
		try {
			Connection conn=con.getConnection();
			
			//getting manager id from work id
			String sql1="select manager.id,work.id,work.worker_number,work.worker_working from manager inner join work on work.manager_id=manager.id";
			
			PreparedStatement stmt =conn.prepareStatement(sql1);
			
			ResultSet rs=stmt.executeQuery();
			
			int worker_required=0;
			int worker_working=0;
			while(rs.next()) {
				Long manager_id=rs.getLong(1);
				workerObj.setManager_id(manager_id);
				
				worker_required =rs.getInt(3);
				worker_working =rs.getInt(4);
			}
			
			if(worker_working<worker_required) {
			//for inserting data in the worker table in database
			String sql2="insert into worker(id,name,pass,skill_id,work_id,work_joining,manager_id)values(?,?,?,?,?,?,?)";
			
			PreparedStatement stmt1=conn.prepareStatement(sql2);
			
			stmt1.setLong(1,Long.parseLong(workerObj.getWorker_id()));
			stmt1.setString(2, workerObj.getWorker_name());
			stmt1.setString(3, workerObj.getWorker_password());
			stmt1.setLong(4, workerObj.getSkill().getSkill_id());
			stmt1.setLong(5, workerObj.getWork().getWork_id());
			Date date =Date.valueOf(current_date);
			stmt1.setDate(6,date);
			stmt1.setLong(7, workerObj.getManager_id());
			
			int i=stmt1.executeUpdate();
			
			String sql3="update work set worker_working=? where id=?";
			
			PreparedStatement stmt3 =conn.prepareStatement(sql1);
			
			stmt3.setLong(1,workerObj.getWork().getWork_id());
			worker_working=worker_working+1;
			stmt3.setLong(2,worker_working);
			
			if(i==1) {
				System.out.println("-----------------------------------------------------------------------------------------");
				System.out.println("registration successfull");
				System.out.println("-----------------------------------------------------------------------------------------");
			}
			}
			else {
				System.out.println("-----------------------------------------------------------------------------------------");
				System.out.println("Sorry no place vaccant for new worker");
				System.out.println("-----------------------------------------------------------------------------------------");
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

	@Override
	public boolean WorkerSignIn(manregaWorkerDetails worker) {
		//worker sign in part
		ConnectionManager con =new ConnectionManager();
		try {
			Connection conn=con.getConnection();
			
			String sql="select id,pass from worker where id=?";
			PreparedStatement stmt1=conn.prepareStatement(sql);
			stmt1.setInt(1,Integer.parseInt(worker.getWorker_id()));
			
			ResultSet rs=stmt1.executeQuery();
			
			
			if(rs!=null) {
				
				while(rs.next()) {
				if(rs.getString(2).equals(worker.getWorker_password())) {
					return true;
					
				}
				else {
					System.out.println("wrong password");
					return false;
					}
			}
		}
			else {
				System.out.println("wrong id");
				return false;
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return false;
	}

}
