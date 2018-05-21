package jdbc_follow;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CheckFollowingUser
 */
@WebServlet("/CheckFollowingUser")
public class CheckFollowingUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckFollowingUser() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String followedUserID = request.getParameter("followedUserID");
		String followedByUserID = request.getParameter("followedByUserID");
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
//		PreparedStatement psReturnData = null;
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			

			conn = DriverManager.getConnection("jdbc:mysql://cs201.cll9sbto0nla.us-west-1.rds.amazonaws.com/GoOutDB?user=master&password=masterpassword&useSSL=false"); //add port number if not on default
			st = conn.createStatement();
			System.out.println("connected");
			PrintWriter pw = response.getWriter();
			
			//check whether ID is actually an int
			int parsedFollowedUserID = -1;
			try {
				//see if the entry is a number
				parsedFollowedUserID = Integer.parseInt(followedUserID);
			}catch(NumberFormatException nfe) {
				System.out.println("nfe: " + nfe.getMessage());
				System.out.println(followedUserID);
				followedUserID = "";
			}
			
			int parsedFollowedByUserID = -1;
			try {
				//see if the entry is a number
				parsedFollowedByUserID = Integer.parseInt(followedByUserID);
			}catch(NumberFormatException nfe) {
				System.out.println("nfe: " + nfe.getMessage());
				System.out.println(followedByUserID);
				followedByUserID = "";
			}
			
			
			if (followedUserID != null && followedUserID.length() != 0 && followedByUserID != null && followedByUserID.length() != 0) {
				
				
				ps = conn.prepareStatement("SELECT * "
						+ "FROM FollowingUsers "
						+ "WHERE followedUserID=? " 
						+ "AND followedByUserID =?");
				ps.setInt(1, parsedFollowedUserID );
				ps.setInt(2, parsedFollowedByUserID );
				System.out.println(parsedFollowedUserID + ", " + parsedFollowedByUserID);
				rs = ps.executeQuery();
				
				
				
				if (!rs.next()) {
					System.out.println("No connection exists");
					pw.println("no");
				}
				else {
					pw.println("yes");
				}
	
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
