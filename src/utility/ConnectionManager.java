package utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

		public Connection getConnection() throws ClassNotFoundException, SQLException {
			
			//loading the driver class
			Class.forName("oracle.jdbc.OracleDriver");
			
			//creating connection object establishing the connection
			
			Connection con=null;
			
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","SYSTEM","1129ku");
			
			
			//checking connection status
			if(con!=null) {
					System.out.println("connection established");
			}
			else {
				System.out.println("connection not eastablished");
			}
			
			//returning connection object
			return con;
			
		}
}
