package jdbc_follow;

import java.io.IOException;
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
 * Servlet implementation class UnfollowEvent
 */
@WebServlet("/UnfollowEvent")
public class UnfollowEvent extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UnfollowEvent() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userID = request.getParameter("userID");
		String eventID = request.getParameter("eventID");
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		PreparedStatement psReturnData = null;
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			

			conn = DriverManager.getConnection("jdbc:mysql://cs201.cll9sbto0nla.us-west-1.rds.amazonaws.com/GoOutDB?user=master&password=masterpassword&useSSL=false"); //add port number if not on default
			st = conn.createStatement();
			System.out.println("connected");
				
			
			//check whether ID is actually an int
			int parsedUserID = -1;
			try {
				//see if the entry is a number
				parsedUserID = Integer.parseInt(userID);
			}catch(NumberFormatException nfe) {
				System.out.println("nfe: " + nfe.getMessage());
				System.out.println(userID);
				userID = "";
			}
			
			int parsedEventID = -1;
			try {
				//see if the entry is a number
				parsedEventID = Integer.parseInt(eventID);
			}catch(NumberFormatException nfe) {
				System.out.println("nfe: " + nfe.getMessage());
				System.out.println(eventID);
				eventID = "";
			}
			
			
			if (userID != null && userID.length() != 0 && eventID != null && eventID.length() != 0) {
				
				
				ps = conn.prepareStatement("DELETE FROM FollowingEvents WHERE eventID=? AND userID=?");
				ps.setInt(1, parsedEventID );
				ps.setInt(2, parsedUserID );
				System.out.println(parsedEventID + ", " + parsedUserID);
				
				
				if (ps.executeUpdate() == 0) {
					System.out.println("Didn't delete");
				}
				
				psReturnData = conn.prepareStatement("SELECT * "
						+ "FROM Users u, Events e"
						+ " WHERE u.userID = e.userID "
//						+ "AND e.eventID = e.eventID "
						+ "AND e.eventID=?");
				psReturnData.setInt(1, parsedEventID );
				rs = psReturnData.executeQuery();
				while(rs.next()) {
					
					int eventID_ = rs.getInt("e.eventID");
					String eventName = rs.getString("e.eventName");
					int eventUserID = rs.getInt("u.userID");
					String eventFullName = rs.getString("u.fullName");
					String eventUsername = rs.getString("u.username");
					
					String eventLocation = rs.getString("e.location");
//					if (eventLocation == null) {
//						eventLocation = ("N/A");
//					}
					
					int eventMonth = rs.getInt("e.month");
//					if (eventMonth == 0) {
//						eventMonth = ("N/A");
//					}
					int eventDay = rs.getInt("e.day");
					
					
					String eventStartTime = rs.getTime("e.startTime").toString();

					String eventEndTime = rs.getTime("e.endTime").toString();
					if (eventEndTime == null) {
						eventEndTime = "N/A";
					}

					
					request.setAttribute("eventID", eventID_);
					request.setAttribute("eventName", eventName);
					request.setAttribute("eventUserID", eventUserID);
					request.setAttribute("eventLocation", eventLocation);
					request.setAttribute("eventMonth", eventMonth);
					request.setAttribute("eventDay", eventDay);
					request.setAttribute("eventStartTime", eventStartTime);
					request.setAttribute("eventEndTime", eventEndTime);
					
					request.setAttribute("eventUsername", eventUsername);
					request.setAttribute("eventFullName", eventFullName);
					
				}
				
			}
				



			
			
			
//			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/FollowEventResults.jsp");
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