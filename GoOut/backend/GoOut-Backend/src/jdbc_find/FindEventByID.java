package jdbc_find;

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
 * Servlet implementation class FindEventByID
 */
@WebServlet("/FindEventByID")
public class FindEventByID extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String eventID = request.getParameter("eventID");
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
			
			ArrayList<Integer> eventIDs = new ArrayList<Integer>();
			ArrayList<String> eventNames = new ArrayList<String>();
			ArrayList<Integer> eventUserIDs = new ArrayList<Integer>();
			ArrayList<String> eventLocations = new ArrayList<String>();
			ArrayList<Integer> eventMonths = new ArrayList<Integer>();
			ArrayList<Integer> eventDays = new ArrayList<Integer>();

			ArrayList<String> eventStartTimes = new ArrayList<String>();
			ArrayList<String> eventEndTimes = new ArrayList<String>();
			
			//check event ID is valid
			int parsedEventID = -1;
			try {
				parsedEventID = Integer.parseInt(eventID);
			}catch(NumberFormatException nfe) {
				System.out.println("nfe: " + nfe.getMessage());
				System.out.println(eventID);
				eventID = "";
			}
			
			if (eventID != null && eventID.length() != 0) {
				ps = conn.prepareStatement("SELECT * FROM Events WHERE eventID=?"); 
				ps.setInt(1, parsedEventID );
				rs = ps.executeQuery();
				

				
				while(rs.next()) {
					int eventID_ = rs.getInt("eventID");
					String eventName = rs.getString("eventName");
					int eventUserID = rs.getInt("userID");
					System.out.println(eventID + "-" + eventName + " " + eventUserID);
					
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
						eventEndTimes.add(eventStartTime.toString());
					}
					else {
						eventEndTimes.add("N/A");
					}			

				
					
				}
				
			}
				
			else {
				
//				ps = conn.prepareStatement(" SELECT * FROM Events");
//				rs = ps.executeQuery();

				//save self from SQL injection
				ps = conn.prepareStatement(" SELECT * FROM Events");
				rs = ps.executeQuery();
				eventIDs.add(0000000000);
				eventNames.add("");
				eventUserIDs.add(000000000);	
				eventLocations.add("");
				eventMonths.add(0);
				eventDays.add(0);
				eventStartTimes.add("");
				eventEndTimes.add("");
			}
			
			request.setAttribute("eventIDs", eventIDs);
			request.setAttribute("eventNames", eventNames);
			request.setAttribute("eventUserIDs", eventUserIDs);
			request.setAttribute("eventLocations", eventLocations);
			request.setAttribute("eventMonths", eventMonths);
			request.setAttribute("eventDays", eventDays);
			request.setAttribute("eventStartTimes", eventStartTimes);
			request.setAttribute("eventEndTimes", eventEndTimes);
			
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/EventSearchResults.jsp");
			dispatcher.forward(request, response);
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
