package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.manregaManager;
import utility.ConnectionManager;

public class managerLoginDao implements managerLoginDaoInterface {

	@Override
	public boolean managerLogin(manregaManager managerObj) {
		
		
		String sql="select * from manager where id=?";
		ConnectionManager con=new ConnectionManager();
		
		Connection connObj;
		try {
			connObj = con.getConnection();
			
			PreparedStatement stmt=connObj.prepareStatement(sql);
			
			stmt.setString(1, managerObj.getManager_id());
			
			ResultSet rs=stmt.executeQuery();
			
			if(rs.next()==true) {
				System.out.println("Id:"+rs.getString(1)+"\t\tName:"+rs.getString(2));
				return true;
			}
			else
				return false;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
				
		return false;
	}

}
