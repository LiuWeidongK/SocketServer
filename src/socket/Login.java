package socket;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login {
	private String username;
	private String password;
	
	public Login(String username,String password) {
		this.username = username;
		this.password = password;
	}
	
	public Boolean login() {
		Connection conn = Jdbc.getConn();
		PreparedStatement pstm;
		String sql = "select password from user where username = '" + username + "'";
		
		try {
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			if(rs.last()){
				if(password.equals(rs.getString("password"))){
					return true;
				}else{
					return false;
				}
			}else{
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
}
