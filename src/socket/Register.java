package socket;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Register {
	private String username;
	private String password;
	
	public Register(String username,String password) {
		this.username = username;
		this.password = password;
	}
	
	public Boolean check() { 				//÷ÿ∏¥		
		int n = 0;
		Connection conn = Jdbc.getConn();
		PreparedStatement pstm;
		String sql = "select * from user where username = '" + username + "'";
		
		try {
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			while(rs.next()){
				n++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
		if(n!=0){
			return false;
		}else {
			return true;
		}
	}
	
	public Boolean regist() {
		int n = 0;
		Connection conn = Jdbc.getConn();
		PreparedStatement pstm;
		String sql = "insert into user (username,password) values (?,?)";

		try {
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			pstm.setString(1, username);
			pstm.setString(2, password);
			n = pstm.executeUpdate();
			pstm.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(n!=0){
			return true;
		}else{
			return false;
		}
	}
}
