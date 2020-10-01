package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import utility.ConnectionManager;

public class PublicPortalServiceDao implements PublicPortalServiceInterfaceDao{

	@Override
	public void ongoingProjectDao() {
		// method to show all work at all the position 
		ConnectionManager con=new ConnectionManager();
		try {
			Connection conn=con.getConnection();
			String sql="select work.work_name,location.name,work.work_duration,work.pay,work.worker_number,work.worker_working from work inner join location on work.location_id=location.id";
		
			PreparedStatement stmt=conn.prepareStatement(sql);
			
			ResultSet rs=stmt.executeQuery();
			
			System.out.println("Work Name\t\tWork Place\t\tWork Duration\t\tPay\t\tTotal Worker Required");
			while(rs.next()) {
				System.out.print(rs.getString(1));
				System.out.print("\t\t");
				System.out.print(rs.getString(2));
				System.out.print("\t\t");
				System.out.print(rs.getString(3));
				System.out.print("\t\t");
				System.out.print(rs.getInt(4));
				System.out.print("\t\t");
				int workerNeeded=rs.getInt(5);
				int workerWorking=rs.getInt(6);
				int required=workerNeeded-workerWorking;
				System.out.println(required);
			}
		
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
