<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import = "java.util.ArrayList" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Event+userinfo Search Results</title>
	</head>
	<body>
		<%
		
		
		ArrayList<Integer> eventIDs = (ArrayList<Integer>)request.getAttribute("eventIDs");
		ArrayList<String> eventNames = (ArrayList<String>)request.getAttribute("eventNames");
		ArrayList<Integer> eventUserIDs = (ArrayList<Integer>)request.getAttribute("eventUserIDs");
		ArrayList<String> eventLocations = (ArrayList<String>)request.getAttribute("eventLocations");
		ArrayList<Integer> eventMonths = (ArrayList<Integer>)request.getAttribute("eventMonths");
		ArrayList<Integer> eventDays = (ArrayList<Integer>)request.getAttribute("eventDays");
		ArrayList<String> eventStartTimes = (ArrayList<String>)request.getAttribute("eventStartTimes");
		ArrayList<String> eventEndTimes = (ArrayList<String>)request.getAttribute("eventEndTimes");
		
		ArrayList<Integer> userIDs = (ArrayList<Integer>)request.getAttribute("userIDs");
		ArrayList<String> fullNames = (ArrayList<String>)request.getAttribute("fullNames");
		ArrayList<String> usernames = (ArrayList<String>)request.getAttribute("usernames"); 
		
		
		for (int i = 0; i < eventIDs.size(); i++){
			System.out.println( eventIDs.get(i) + " " +eventNames.get(i) + " Created by: " + eventUserIDs.get(i) + " " + usernames.get(i) + "- " + fullNames.get(i) 
								+ " " + eventLocations.get(i)
								+ " " + eventMonths.get(i)
								+ " " + eventDays.get(i)
								+ " " + eventStartTimes.get(i)
								+ " " + eventEndTimes.get(i)
								);
			
			%>
			<%= eventIDs.get(i) %> - <%= eventNames.get(i) %> Created By: <%= eventUserIDs.get(i) %> <%= usernames.get(i) %>- <%= fullNames.get(i) %> 
			<br />
			<%= eventLocations.get(i) %>, <%= eventMonths.get(i) %>/<%= eventDays.get(i) %> 
			from <%= eventStartTimes.get(i) %> to <%= eventEndTimes.get(i) %> 
			<br /> ------------------- <br />
		<% } %>
		
		
	</body>
</html>