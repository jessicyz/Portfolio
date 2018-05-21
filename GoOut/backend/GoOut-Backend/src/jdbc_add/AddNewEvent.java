/*
 * 
 * startTime and endTime strings must be in "hh:mm:ss" format in order to be successfully parsed 
 * 
 */
package jdbc_add;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AddNewEvent
 */
@WebServlet("/AddNewEvent")
public class AddNewEvent extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddNewEvent() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String eventName = request.getParameter("eventName");
		String userIDStr = request.getParameter("userID");
		String location = request.getParameter("location");
		int month = 0; 
		try{
			month = Integer.parseInt(request.getParameter("month")); 
		}catch(NumberFormatException nfe){
			System.out.println("nfe: "+nfe.getMessage());
			month = 0; 
		}
		int day = 0; 
		try{
			day = Integer.parseInt(request.getParameter("day")); 
		}catch(NumberFormatException nfe){
			System.out.println("nfe: "+nfe.getMessage());
			day = 0; 
		}
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime"); 
		boolean privateEvent = false;
		// check null 
		// if true, directs back to AddNewEventForm
		if(eventName==null || eventName=="" || userIDStr==null || userIDStr==""){
			 response.setContentType("text/html");  
			 PrintWriter out = response.getWriter(); 
			 out.println("Event name, user ID and event privacy cannot be null");
			response.sendRedirect("AddNewEventForm.html");
			return; 
		}
		int userID = Integer.parseInt(userIDStr); 
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		PreparedStatement psUpdate = null;
		PreparedStatement psDisplay = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			// sets up the connection 
			conn = DriverManager.getConnection("jdbc:mysql://cs201.cll9sbto0nla.us-west-1.rds.amazonaws.com/GoOutDB?user=master&password=masterpassword&useSSL=false");
			st = conn.createStatement();
			System.out.println("connected");
			psUpdate = conn.prepareStatement("INSERT INTO Events(eventName, userID, location, month, day, startTime, endTime, privateEvent)"
					+"VALUES(?,?,?,?,?,?,?,?)");
			psUpdate.setString(1, eventName);
			psUpdate.setInt(2, userID);
			psUpdate.setString(3, location);
			psUpdate.setInt(4, month);
			psUpdate.setInt(5, day);
			
			java.sql.Time start = getSQLTime(startTime);
			System.out.println("start: "+ start);
			psUpdate.setTime(6, start);
			System.out.println("end: "+endTime);
			java.sql.Time end = getSQLTime(endTime);
			psUpdate.setTime(7, end);
			psUpdate.setBoolean(8, privateEvent);
			// executes the script 
			psUpdate.executeUpdate();
			System.out.println("Script executed!");
			
			psDisplay = conn.prepareStatement("SELECT * FROM Events WHERE eventName = ?");
			psDisplay.setString(1, eventName);
			rs = psDisplay.executeQuery();
			while(rs.next()){
				String eventName_ = rs.getString("eventName");
				int userID_ = rs.getInt("userID");
				String location_ = rs.getString("location");
				request.setAttribute("userID", userID_);
				request.setAttribute("eventName", eventName_);
				request.setAttribute("location", location_);
			}
			
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/AddNewEventResults.jsp");
			dispatcher.forward(request, response);
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
	// gets the correct time object for SQL 
	private java.sql.Time getSQLTime(String timeStr){
		java.sql.Time ppstime = null; 
		try{
			SimpleDateFormat textFormat = new SimpleDateFormat("hh:mm:ss");
			java.util.Date d1 =(java.util.Date)textFormat.parse(timeStr);
			ppstime = new java.sql.Time(d1.getTime()); 
		}catch(ParseException pe){
			System.out.println("ParseException: "+pe.getMessage());
		}
		return ppstime;
	}
}
