package socket;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Jdbc {
	public static Connection getConn() {
		String drive = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/login";
		String username = "root";
		String password = "0000";
		Connection conn = null;
		
		try {
			Class.forName(drive);
			conn = (Connection) DriverManager.getConnection(url,username,password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	public static void main(String[] args) {
		System.out.println(Jdbc.getConn());
	}
}
