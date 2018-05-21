package jdbc_find;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FindUserByID
 */
@WebServlet("/FindUserByID")
public class FindUserByID extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userID = request.getParameter("userID");
		//Integer.parseInt(request.getParameter("userID"));
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		try {

			Class.forName("com.mysql.jdbc.Driver");

			
			conn = DriverManager.getConnection("jdbc:mysql://cs201.cll9sbto0nla.us-west-1.rds.amazonaws.com/GoOutDB?user=master&password=masterpassword&useSSL=false"); 
			st = conn.createStatement();
			System.out.println("connected");
			
			ArrayList<Integer> userIDs = new ArrayList<Integer>();
			ArrayList<String> usernames = new ArrayList<String>();
			ArrayList<String> fullNames = new ArrayList<String>();
			
			//check if it's a number
			int parsedUserID = -1;
			try {
				//see if the entry is a number
				parsedUserID = Integer.parseInt(userID);
			}catch(NumberFormatException nfe) {
				System.out.println("nfe: " + nfe.getMessage());
				userID = "";
			}
			
			if (userID != null && userID.length() != 0) {
				ps = conn.prepareStatement("SELECT * FROM Users WHERE userID=?"); 

				ps.setInt(1, parsedUserID );
				rs = ps.executeQuery();
				

				
				while(rs.next()) {
					int userID_ = rs.getInt("userID");
					String username_ = rs.getString("username");
					String fullName = rs.getString("fullName");

					
					userIDs.add(userID_);
					usernames.add(username_);
					fullNames.add(fullName);				

				
				}
				
				
			}
//			else {
//
//				//save self from SQL injection
//				ps = conn.prepareStatement(" SELECT * FROM Users");
//				rs = ps.executeQuery();
//				userIDs.add(00000000);
//				usernames.add("no users with this ID");
//				fullNames.add("");	
//			}
			if(!usernames.isEmpty()) {
				response.getWriter().println(usernames);
				response.getWriter().flush();
				response.getWriter().close();
			}
//			request.setAttribute("userIDs", userIDs);
//			request.setAttribute("usernames", usernames);
//			request.setAttribute("fullNames", fullNames);
//			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/UserSearchResults.jsp");
			//dispatcher.forward(request, response);
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