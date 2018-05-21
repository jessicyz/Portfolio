package objects;


import java.sql.*;

import constants.StringConstants;

public class JDBCDriver {
	private static Connection conn = null;
	private static ResultSet rs = null;
	private static PreparedStatement ps = null;
	
	public static void connect() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://"+StringConstants.DB_URL+
							"/"+ StringConstants.MAIN_DB_NAME + "?user="+ StringConstants.DB_USER + "&password="
							   + StringConstants.DB_PASSWORD + "&useSSL=false");
		}catch(ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	public static void close() {
		try {
			if(rs!= null) {
				rs.close();
				rs = null;
			}
			if(conn != null) {
				conn.close();
				conn = null;
			}
			if(ps != null) {
				ps.close();
				ps = null;
			}
		}catch(SQLException sqle) {
			System.out.println("Error closing the connection");
			sqle.printStackTrace();
		}
	}
}
