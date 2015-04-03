package dbtest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBConnect {
	public static void main(String args[]) {
		try {
			Statement stmt;

			// Register the JDBC driver for MySQL.
			Class.forName("com.mysql.jdbc.Driver");

			// Define URL of database server for
			// database named mysql on the localhost
			// with the default port number 3306.
			String url = "jdbc:mysql://localhost:3306/test";

			// Get a connection to the database for a
			// user named root with a blank password.
			// This user is the default administrator
			// having full privileges to do anything.
			Connection con = DriverManager.getConnection(url, "root", "");

			// Display URL and connection information
			System.out.println("URL: " + url);
			System.out.println("Connection: " + con);

			// Get a Statement object
			stmt = con.createStatement();
			String query = "SELECT * FROM loginmaster";
			
			ResultSet rs= stmt.executeQuery(query);
			
			while(rs.next()){
				System.out.println(rs.getString(2));
			}

		} catch (Exception e) {

		}
	}
}
