package jdbc_find;

import java.io.IOException;
import java.io.PrintWriter;
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
 * Servlet implementation class FindUserBySearchTerm
 */
@WebServlet("/FindUserBySearchTerm")
public class FindUserBySearchTerm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindUserBySearchTerm() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String searchTerm = request.getParameter("searchTerm");
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		try {
			System.out.println("here 1");
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("here 2");

			
			conn = DriverManager.getConnection("jdbc:mysql://cs201.cll9sbto0nla.us-west-1.rds.amazonaws.com/GoOutDB?user=master&password=masterpassword&useSSL=false");
			st = conn.createStatement();
			System.out.println("connected");
			
			if (searchTerm != null && searchTerm.length() > 0) {
				//ps = conn.prepareStatement("SELECT * FROM Users WHERE username=?");
				ps = conn.prepareStatement("SELECT * FROM Users WHERE username LIKE ? OR fullName LIKE ?");
				ps.setString(1, "%" + searchTerm + "%");
				ps.setString(2, "%" + searchTerm + "%");
				rs = ps.executeQuery();
			}
			else {

				//save self from SQL injection
				ps = conn.prepareStatement(" SELECT * FROM Users");
				rs = ps.executeQuery();
			}
			ArrayList<Integer> userIDs = new ArrayList<Integer>();
			ArrayList<String> usernames = new ArrayList<String>();
			ArrayList<String> fullNames = new ArrayList<String>();
			
			while(rs.next()) {
				int userID = rs.getInt("userID");
				String username_ = rs.getString("username");
				String fullName = rs.getString("fullName");

				
				userIDs.add(userID);
				usernames.add(username_);
				fullNames.add(fullName);				

			
			}
			PrintWriter pw = response.getWriter();
			pw.println(userIDs);
			pw.println(usernames);
			pw.println(fullNames);
			pw.flush();
			pw.close();
//			request.setAttribute("userIDs", userIDs);
//			request.setAttribute("usernames", usernames);
//			request.setAttribute("fullNames", fullNames);
//			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/UserSearchResults.jsp");
//			dispatcher.forward(request, response);
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
