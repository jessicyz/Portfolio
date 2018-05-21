package login;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ValidateLogin
 */
@WebServlet("/ValidateLogin")
public class ValidateLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ValidateLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 		String username = request.getParameter("username");
 		String password = request.getParameter("password");
 		
 		Connection conn = null;
 		Statement st = null;
 		ResultSet rs = null;
 		PreparedStatement ps = null;
 		PrintWriter pw = response.getWriter();
 		try {
 			Class.forName("com.mysql.jdbc.Driver");

 			conn = DriverManager.getConnection("jdbc:mysql://cs201.cll9sbto0nla.us-west-1.rds.amazonaws.com/GoOutDB?user=master&password=masterpassword&useSSL=false");
 			st = conn.createStatement();
 			
 			
 			ps = conn.prepareStatement("SELECT * FROM Users WHERE username=? AND BINARY password=?");
			ps.setString(1, username);
			ps.setString(2, password);
 			rs = ps.executeQuery();
 			int userID = -1;
 			String username_ = "";
 			String fullName = "";
 			
			if(!rs.next()) {
				pw.println("Invalid username or password");
			}
			else {
				userID = rs.getInt("userID");
				username_ = rs.getString("username");
				fullName = rs.getString("fullName");
				pw.println(userID);
			}
 			

 			pw.flush();
 			pw.close();
 		}catch(SQLException sqle) {
 			System.out.println("sqle: " + sqle.getMessage());
 		}catch (ClassNotFoundException cnfe) {
 			System.out.println("cnfe: " + cnfe.getMessage());
 		} finally {
 			try {
 				//has to be in reverse order
 				if (rs != null) {
 					rs.close();
 				}
 				if (st != null) {
 					st.close();
 				}
 				if (conn != null) {
 					conn.close();
 				}
 			}catch(SQLException sqle) {
 				System.out.println("sqle closing stuff: " + sqle.getMessage());
 			}
 		}
 		
 		
 	}

}