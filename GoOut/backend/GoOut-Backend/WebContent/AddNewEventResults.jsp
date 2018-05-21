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
		String eventName = (String)request.getAttribute("eventName");
		String location = (String)request.getAttribute("location");
		
			System.out.println( userID + " "  +eventName + " " + location);
			
			%>
			<%= userID %> - <%= eventName %> - s<%= location %>   <br />

		
		
	</body>
</html>