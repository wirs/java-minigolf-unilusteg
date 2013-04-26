package minigolf;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MGsql {
    public void connect() {
        try {
            // The newInstance() call is a work around for some
            // broken Java implementations
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (Exception ex) {
            System.out.println("Driver load failed");
        }
        try {
           // Connection conn = DriverManager.getConnection("jdbc:mysql://admin.4pals.org/minigolf_db?" + "user=golf_user&password=g0lfp4$$");
          Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/test?" + "user=test&password=lol123");
        
            
            // Do something with the Connection
           System.out.println("Connected to DB");
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    
    }
}



