package jdbc_find;

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
 * Servlet implementation class FindEventBySearchTerm
 */
@WebServlet("/FindEventBySearchTerm")
public class FindEventBySearchTerm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String searchTerm = request.getParameter("searchTerm");
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;

		PreparedStatement ps = null;
		PrintWriter pw = response.getWriter();
		try {
			//System.out.println("here 1");
			Class.forName("com.mysql.jdbc.Driver");
			//System.out.println("here 2");
			
			conn = DriverManager.getConnection("jdbc:mysql://cs201.cll9sbto0nla.us-west-1.rds.amazonaws.com/GoOutDB?user=master&password=masterpassword&useSSL=false"); //add port number if not on default
			st = conn.createStatement();
			System.out.println("connected");
			
			if (searchTerm != null && searchTerm.length() > 0) {
				//ps = conn.prepareStatement("SELECT * FROM Users WHERE username=?");
				ps = conn.prepareStatement("SELECT * "
											+ "FROM Users u, Events e "
											+ "WHERE u.userID = e.userID "
											+ "AND (u.username LIKE ? "
											+ "OR u.fullName LIKE ? "
											+ "OR e.eventName LIKE ?"
											+ "OR e.description LIKE ?)");

				
				ps.setString(1, "%" + searchTerm + "%");
				ps.setString(2, "%" + searchTerm + "%");
				ps.setString(3, "%" + searchTerm + "%");
				ps.setString(4, "%" + searchTerm + "%");
				rs = ps.executeQuery();

			}
			else {

				//save self from SQL injection
				ps = conn.prepareStatement("SELECT * FROM Events e, Users u WHERE u.userID = e.userID");
				rs = ps.executeQuery();
			}
			
			
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
			

			
			
			
			while(rs.next()) {
		

				
				int eventID = rs.getInt("e.eventID");
				String eventName_ = rs.getString("eventName");
				int eventUserID = rs.getInt("e.userID");
//				System.out.println(userID + "-" + firstName + " " + lastName);
				
				eventIDs.add(eventID);
				eventNames.add(eventName_);
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
//					eventStartTimes.add(eventStartTime);
					eventStartTimes.add(eventStartTime.toString());
				}
				else {
					eventStartTimes.add("N/A");
				}
				
				Time eventEndTime = rs.getTime("endTime");
				if (eventEndTime != null) {
//					eventEndTimes.add(eventEndTime);
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
				

				
				int userID = rs.getInt("u.userID");
				String username_ = rs.getString("u.username");
				String fullName = rs.getString("u.fullName");

				
				userIDs.add(userID);
				usernames.add(username_);
				fullNames.add(fullName);	
				
				
			
			}
			

			
			
			
			
			request.setAttribute("eventIDs", eventIDs);
			request.setAttribute("eventNames", eventNames);
			request.setAttribute("eventUserIDs", eventUserIDs);
			request.setAttribute("eventLocations", eventLocations);
			request.setAttribute("eventMonths", eventMonths);
			request.setAttribute("eventDays", eventDays);
			request.setAttribute("eventStartTimes", eventStartTimes);
			request.setAttribute("eventEndTimes", eventEndTimes);
			request.setAttribute("eventDescriptions", eventDescriptions);
			
			
			pw.println(eventIDs);
			pw.println(eventNames);
			pw.println(usernames);
			//pw.println(eventUserIDs);
			pw.println(eventLocations);
			pw.println(eventMonths);
			pw.println(eventDays);
			pw.println(eventStartTimes);
			pw.println(eventEndTimes);
			pw.println(eventDescriptions);
			
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
