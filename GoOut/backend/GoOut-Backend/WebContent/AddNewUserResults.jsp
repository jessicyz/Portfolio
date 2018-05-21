<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import = "java.util.ArrayList" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Add New User Results</title>
	</head>
	<body>
		<%
		Integer userID = (Integer)request.getAttribute("userID");
		String fullName = (String)request.getAttribute("fullName");
		String username = (String)request.getAttribute("username");
		String password = (String)request.getAttribute("password");
		boolean isPrivate = (boolean)request.getAttribute("isPrivate");
		ArrayList<String> usernames = (ArrayList<String>)request.getAttribute("usernames");
		
			System.out.println( userID + " "  +fullName + " " + username + " " + password +  " " + isPrivate);
			
			%>
			<%= userID %> <%= fullName %> <%= username %> - <%= password %> Private User: <%= isPrivate %>   <br />

		
		
	</body>
</html>