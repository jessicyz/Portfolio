package jdbc_follow;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class GetFollowingUserList
 */
@WebServlet("/GetFollowingUserList")
public class GetFollowingUserList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetFollowingUserList() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int userID = Integer.parseInt(request.getParameter("userID"));
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			// sets up the connection 
			conn = DriverManager.getConnection("jdbc:mysql://cs201.cll9sbto0nla.us-west-1.rds.amazonaws.com/GoOutDB?user=master&password=masterpassword&useSSL=false");
			st = conn.createStatement();
			System.out.println("Connnected!");
			ps = conn.prepareStatement("SELECT * FROM "
					+ "FollowingUsers "
					+ "WHERE followedByUserID = ? "
					);
			ps.setInt(1, userID);
			rs = ps.executeQuery();

			ArrayList<Integer> userIDs = new ArrayList<Integer>();

			
			while (rs.next()) {


					int userID_ = rs.getInt("followedUserID");

					
					userIDs.add(userID_);

					
				
			}
			
			response.getWriter().println(userIDs);
			response.getWriter().flush();
			response.getWriter().close();
			// request.setAttribute("userIDs", userIDs);

			
			// RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/FollowingEventListResult.jsp");
			// dispatcher.forward(request, response);
		}catch(SQLException sqle) {
			System.out.println("sqle: " + sqle.getMessage());
		}catch (ClassNotFoundException cnfe) {
			System.out.println("cnfe: " + cnfe.getMessage());
		}finally {
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
