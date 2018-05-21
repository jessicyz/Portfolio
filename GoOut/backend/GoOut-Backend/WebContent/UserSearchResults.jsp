<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import = "java.util.ArrayList" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>User Search Results</title>
	</head>
	<body>
		<%
		ArrayList<Integer> userIDs = (ArrayList<Integer>)request.getAttribute("userIDs");
		ArrayList<String> fullNames = (ArrayList<String>)request.getAttribute("fullNames");
		ArrayList<String> usernames = (ArrayList<String>)request.getAttribute("usernames");
		for (int i = 0; i < userIDs.size(); i++){
			System.out.println( userIDs.get(i) + " " + usernames.get(i) + " " +fullNames.get(i));
			
			%>
			<%= userIDs.get(i) %> <%= usernames.get(i) %> - <%= fullNames.get(i) %>  <br />
		<% } %>
		
		
	</body>
</html>