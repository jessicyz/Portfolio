package jdbc_follow;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse; 
/**
 * Servlet implementation class FollowUser
 */
@WebServlet("/GetFollowingEventList")
public class GetFollowingEventList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetFollowingEventList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
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
					+ "FollowingEvents fe, Events e, Users u "
					+ "WHERE fe.userID = ? "
					+ "AND e.eventID = fe.eventID "
					+ "AND e.userID = u.userID"
					);
			ps.setInt(1, userID);
			rs = ps.executeQuery();
			ArrayList<Integer> eventIDs = new ArrayList<Integer>();
			ArrayList<String> eventNames = new ArrayList<String>();
			ArrayList<Integer> eventUserIDs = new ArrayList<Integer>();
			ArrayList<String> eventLocations = new ArrayList<String>();
			ArrayList<Integer> eventMonths = new ArrayList<Integer>();
			ArrayList<Integer> eventDays = new ArrayList<Integer>();

			ArrayList<String> eventStartTimes = new ArrayList<String>();
			ArrayList<String> eventEndTimes = new ArrayList<String>();
			ArrayList<String> eventDescriptions = new ArrayList<String>();
			
			ArrayList<Integer> userIDs = new ArrayList<Integer>();
			ArrayList<String> usernames = new ArrayList<String>();
			ArrayList<String> fullNames = new ArrayList<String>();
			
			ArrayList<String> eventStrings = new ArrayList<String>();
			
//			if (!rs.next()) {
//				System.out.println("Returning nothing");
//			}
			
			while (rs.next()) {
				
				String toReturn = "";
				
					int eventID_ = rs.getInt("e.eventID");
				
					String eventName = rs.getString("e.eventName");
					
					int eventUserID = rs.getInt("u.userID");
				//toReturn += eventUserID + ",";
					
					eventIDs.add(eventID_);
					eventNames.add(eventName);
					eventUserIDs.add(eventUserID);

					//may be null
					String eventLocation = rs.getString("location");
					if (eventLocation != null) {
						eventLocations.add(eventLocation);
					}
					else {
						eventLocations.add("N/A");
					}

					
					int eventMonth = rs.getInt("month");
					if (eventMonth != 0) {
						eventMonths.add(eventMonth);
					}
					else {
						eventMonths.add(0);
					}

					
					int eventDay = rs.getInt("day");
					if (eventDay != 0) {
						eventDays.add(eventDay);
					}
					else {
						eventDays.add(0);
					}

					
					Time eventStartTime = rs.getTime("startTime");
					if (eventStartTime != null) {
//							eventStartTimes.add(eventStartTime);
						eventStartTimes.add(eventStartTime.toString());
					}
					else {
						eventStartTimes.add("N/A");
					}

					
					Time eventEndTime = rs.getTime("endTime");
					if (eventEndTime != null) {
//							eventEndTimes.add(eventEndTime);
						eventEndTimes.add(eventEndTime.toString());
					}
					else {
						eventEndTimes.add("N/A");
					}		

					
					String eventDescription = rs.getString("description");
					if (eventDescription != null) {
						eventDescriptions.add(eventDescription);
					}
					else {
						eventDescriptions.add("");
					}

					

					int userID_ = rs.getInt("u.userID");
					String username_ = rs.getString("u.username");
					String fullName = rs.getString("u.fullName");

					
					userIDs.add(userID_);
					usernames.add(username_);
					fullNames.add(fullName);	
					
					toReturn += username_ + ",";
					toReturn += eventID_ + ",";
					toReturn += eventName + ",";
					toReturn += eventDescription + ",";	
					toReturn += eventMonth + ",";
					toReturn += eventDay + ",";
					toReturn += eventStartTime.toString() + ",";
					toReturn += eventEndTime.toString() + ",";
					toReturn += eventLocation;
					
					eventStrings.add(toReturn);
					
				
			}
			PrintWriter pw = response.getWriter();
			for(int i = 0; i < eventStrings.size(); i++){
				pw.println(eventStrings.get(i));
			}
			pw.flush();
			pw.close();
			// request.setAttribute("eventIDs", eventIDs);
			// request.setAttribute("eventNames", eventNames);
			// request.setAttribute("eventUserIDs", eventUserIDs);
			// request.setAttribute("eventLocations", eventLocations);
			// request.setAttribute("eventMonths", eventMonths);
			// request.setAttribute("eventDays", eventDays);
			// request.setAttribute("eventStartTimes", eventStartTimes);
			// request.setAttribute("eventEndTimes", eventEndTimes);
			// request.setAttribute("eventDescriptions", eventDescriptions);
			
			// request.setAttribute("userIDs", userIDs);
			// request.setAttribute("usernames", usernames);
			// request.setAttribute("fullNames", fullNames);
			
			// request.setAttribute("eventStrings", eventStrings);
			
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