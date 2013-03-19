package minigolf;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.mysql.jdbc.Connection;


public class ReplicationDriverDemo {

	
	 java.sql.Connection conn = null;
	    Properties connectionProps = new Properties();{
	    //connectionProps.put("user"= "golf_user");
	   // connectionProps.put("password"= "g0lfp4$$");
	   
	 
	        try {
				conn = DriverManager.getConnection(
				           "jdbc:" + "mysql" + "://" +
				           "admin.4pals.org" +
				           ":" + "3306" + "/",
				           connectionProps);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	   
	   
	    System.out.println("Connected to database");}

public static void main(String[] args) {}}
	   
