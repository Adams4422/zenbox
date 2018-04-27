package corenlp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;



public class InsertApp {
	
	public static void main(String[] args) {

		InsertApp app = new InsertApp();
		// insert three new rows
		app.insertAllumer("on","switch");
		app.insertAllumer("on","activate");
		app.insertAllumer("on","energize");
		app.insertAllumer("on","ignite");
		app.insertAllumer("on","kick-start");
		app.insertAllumer("on","start");
		
		app.insertEteindre("off","unplug");
		app.insertEteindre("off","shut");
		app.insertEteindre("off","close");
		app.insertEteindre("off","cut");
		app.insertEteindre("off","kill");
		app.insertEteindre("off","halt");
		app.insertEteindre("off","douse");
		app.insertEteindre("off","down");
		
	}

	/**
	 * Connect to the test.db database
	 *
	 * @return the Connection object
	 */
	private Connection connect() {
		// SQLite connection string
		String url = "jdbc:sqlite:/home/leo/eclipse-workspaceEE/CoreNLP/corenlp";
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return conn;
	}

	/**
	 * Insert a new row into the warehouses table
	 *
	 * @param name
	 * @param capacity
	 */
	public void insertAllumer(String name,String verb) {
		String sql = "INSERT INTO verb(name,verb) VALUES(?,?)";

		try (Connection conn = this.connect();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, name);
			pstmt.setString(2, verb);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void insertEteindre(String name,String verb) {
		String sql = "INSERT INTO verb(name,verb) VALUES(?,?)";

		try (Connection conn = this.connect();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, name);
			pstmt.setString(2, verb);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}



}