package jdbc_add;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AddNewUser
 */
@WebServlet("/AddNewUser")
public class AddNewUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddNewUser() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String fullName = request.getParameter("fullName");
		boolean isPrivate = request.getParameter("private") != null;
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		PreparedStatement psUpdate = null;
		PreparedStatement psDisplay = null;
		try {

			Class.forName("com.mysql.jdbc.Driver");

			
			conn = DriverManager.getConnection("jdbc:mysql://cs201.cll9sbto0nla.us-west-1.rds.amazonaws.com/GoOutDB?user=master&password=masterpassword&useSSL=false");
			st = conn.createStatement();
			System.out.println("connected");
			
			psUpdate = conn.prepareStatement("INSERT INTO Users(fullName, username, password, privateUser)" + 
					"VALUES	(?, ?,?,?)");
			
			psUpdate.setString(1, fullName);
			psUpdate.setString(2, username);
			psUpdate.setString(3, password);
			psUpdate.setBoolean(4, isPrivate);
			
			
			psUpdate.executeUpdate();
			
			psDisplay = conn.prepareStatement("SELECT * FROM Users WHERE username =?");
			psDisplay.setString(1, username);
			rs = psDisplay.executeQuery();
			

			while (rs.next()) {
				int userID_ = rs.getInt("userID");
				String username_ = rs.getString("username");
				String password_ = rs.getString("password");
				String fullName_ = rs.getString("fullName");
				boolean isPrivate_ = rs.getBoolean("privateUser");
				
				response.getWriter().println(userID_);	
			}
			response.getWriter().flush();
			response.getWriter().close();
			
			

			
//			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/AddNewUserResults.jsp");
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
