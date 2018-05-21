package jdbc_add;

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
 * Servlet implementation class CheckUsername
 */
@WebServlet("/ValidateInfo")
public class ValidateInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ValidateInfo() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String fullName = request.getParameter("fullName");
		
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		PrintWriter pw = response.getWriter();
		try {
			Class.forName("com.mysql.jdbc.Driver");

			conn = DriverManager.getConnection("jdbc:mysql://cs201.cll9sbto0nla.us-west-1.rds.amazonaws.com/GoOutDB?user=master&password=masterpassword&useSSL=false");
			st = conn.createStatement();
			
			
			if (username == "") {
				pw.println("Username cannot be empty <br>");
			}
			else {
				
//				rs = st.executeQuery("SELECT * FROM Student WHERE username = '" + username + "'");
				ps = conn.prepareStatement("SELECT * FROM Users WHERE username=?");
				ps.setString(1, username);
				rs = ps.executeQuery();
				
				if (rs.next()) {
					pw.println("Username already taken");
				}
			}
			
			
			if (password == "") {
    				pw.println("Password cannot be empty");
			}
			if (fullName == "") {
				pw.println("Full Name cannot be empty");
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
