package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;

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

}
