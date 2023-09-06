<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ page import="com.osttra.to.User" %>
	
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Update</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9"
	crossorigin="anonymous">

</head>

<body>

	<jsp:include page="header.jsp" />
	<%
	User deletingUser = (User) request.getAttribute("deletingUser");
	%>
	

	<h2 style="margin: 30px 500px">Welcome to Update Page</h2>

	<form action="/update" method="POST">
		<div style="margin-left: 500px; margin-right: 600px">

			<div class="mb-3">
				<label for="firstName" class="form-label">First Name</label> <input
					type="text" name="firstName" value="<%= deletingUser.getFirstName() %>" class="form-control" id="firstName"
					aria-describedby="firstName">

			</div>

			<div class="mb-3">
				<label for="lastName" class="form-label">Last Name</label> <input
					type="text" name="lastName" value="<%= deletingUser.getLastName() %>" class="form-control" id="lastName"
					aria-describedby="lastName">

			</div>


			<div class="mb-3">
				<label for="email" class="form-label">Email
					address</label> <input type="email" name="email" value="<%= deletingUser.getEmail() %>" class="form-control"
					id="email" aria-describedby="email">

			</div>

			<div class="mb-3">
				<label for="userName" class="form-label">Username</label> <input
					type="text" name="userName" readonly value= "<%=deletingUser.getUserName()%>" class="form-control" id="userName"
					aria-describedby="userName">

			</div>



			<!-- <select class="form-select" name=userType>
				<option selected>User Type</option>
				<option value="admin">Admin</option>
				<option value="student">Student</option>
				<option value="teacher">Teacher</option>
			</select> <br> 
			
			<select class="form-select" name=className>
				<option selected>Class</option>
				<option value="1">1st year</option>
				<option value="2">2nd year</option>
				<option value="3">3rd year</option>
				<option value="4">Final year</option>
				<option value="5">Admin</option>
			</select> <br> -->
			<button type="submit" class="btn btn-primary">Update</button>

		</div>
	</form>
</body>
</html>