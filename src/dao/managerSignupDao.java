package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.manregaManager;
import utility.ConnectionManager;

public class managerSignupDao implements managerSignupDaoInterface{

	@Override
	public boolean managerSignUp(manregaManager manager) {
		
		//creating object for comnnectionManager class to establish the connection with the database
		
		ConnectionManager con=new ConnectionManager();
		try {
			Connection connObj=con.getConnection();
			
			//sql statement for inserting data inside the table
			String sql="insert into manager(id,name,password)values(?,?,?)";
			
			PreparedStatement stmt=connObj.prepareStatement(sql);
			stmt.setString(1, manager.getManager_id());
			stmt.setString(2, manager.getManager_name());
			stmt.setString(3, manager.getManager_password());
			
			int i=stmt.executeUpdate();
			if(i==1) {
				return true;
			}
			else return false;
			
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
