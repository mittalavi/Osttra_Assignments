<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.osttra.to.User" %>


<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9"
	crossorigin="anonymous">
<title>Search User</title>
<style>
body {
	margin: 0;
	padding: 0;
	font-family: Arial, sans-serif;
	background-color: #f2f2f2;
	display: flex;
	justify-content: center;
	align-items: center;
	min-height: 100vh;
}

.welcome-container {
	background-color: white;
	padding: 20px;
	border-radius: 10px;
	box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
	width: 400px;
}

.student-details {
	text-align: center;
}

.student-details h2 {
	margin-bottom: 20px;
	color: #007bff;
}

.detail {
	display: flex;
	justify-content: space-between;
	align-items: center;
	margin: 10px 0;
}

.label {
	font-weight: bold;
	color: #555;
	margin-right: 10px;
}

.value {
	color: #333;
}
</style>
</head>
<body>

	<jsp:include page="header.jsp" />
	
	<%
	User user = (User) request.getAttribute("user");
	%>


	<div class="welcome-container">
		<div class="student-details">
			<h2><%= user.getFirstName()%> <%=user.getLastName()%>!</h2>
			<div class="detail">
				<span class="label">Username:</span> <span class="value"><%=user.getUserName()%></span>
			</div>
			<div class="detail">
				<span class="label">Email:</span> <span class="value"><%= user.getEmail()%></span>
			</div>
			<div class="detail">
				<span class="label">Password:</span> <span class="value"><%= user.getPassword()%></span>
			</div>
			<div class="detail">
				<span class="label">Class:</span> <span class="value"><%= user.getClassName()%></span>
			</div>

			<div class="detail">
				<span class="label">Role:</span> <span class="value"><%= user.getUserType()%></span>
			</div>
	
			<form style="display: inline" action="/update/<%=user.getUserName()%>" >
				<button type="submit" class="btn btn-success">Update</button>
			</form>
			



		</div>
	</div>
</body>
</html>
