<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import = "java.util.ArrayList" import = "java.sql.Time" import="Information.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Get Following Events List Result</title>
	</head>
	<body>
		<%
		ArrayList<Event> eventList = (ArrayList<Event>) request.getAttribute("eventList");
		for(int x=0; x<eventList.size(); x++){
		Event currentEvent = eventList.get(x);
		String eventName = currentEvent.getEventName(); 
%>
		<%= eventName %><br/>
<%} %>
		
	</body>
</html>