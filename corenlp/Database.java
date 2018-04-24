package corenlp;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
 
/** @author sqlitetutorial.net */
public class Database {
	
    public static void main(String[] args) {
    	createNewTable();
    }
 
    /** Connect to a sample database */
    public static void createNewDatabase(String fileName) {
 
        String url = "jdbc:sqlite:/home/leo/eclipse-workspace/corenlp/" + fileName;
 
        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new database has been created.");
            }
 
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    /** Create a new table in the test database */
    public static void createNewTable() {
        // SQLite connection string
    	String url = "jdbc:sqlite:/home/leo/eclipse-workspace/corenlp/corenlp";
        
        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS verb (\n"
        		+ " name text NOT NULL\n,"
        		+ " verb text NOT NULL\n"
                + ");";
          	
        try (Connection conn = DriverManager.getConnection(url);
                Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
