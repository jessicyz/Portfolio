<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import = "java.util.ArrayList" import = "java.sql.Time" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Follow Event Results</title>
	</head>
	<body>
		<%
		
		int eventID = (int)request.getAttribute("eventID");
		String eventName = (String)request.getAttribute("eventName");
		int eventUserID = (int)request.getAttribute("eventUserID");
		String eventFullName = (String)request.getAttribute("eventFullName");
		String eventUsername = (String)request.getAttribute("eventUsername");
		String eventLocation = (String)request.getAttribute("eventLocation");
		int eventMonth = (int)request.getAttribute("eventMonth");
		int eventDay = (int)request.getAttribute("eventDay");
		Time eventStartTime = ((Time)request.getAttribute("eventStartTime") );
		Time eventEndTime = ((Time)request.getAttribute("eventEndTime") );
		
%>

		<%= eventID %> - <%= eventName %> Created By: <%= eventUserID %> <%= eventUsername %>- <%= eventFullName %> 
		<br />
			<%= eventLocation %>, <%= eventMonth %>/<%= eventDay %> 
			from <%= eventStartTime.toString() %> to <%= eventEndTime.toString() %> 
			<br /> ------------------- <br />

		
		
	</body>
</html>