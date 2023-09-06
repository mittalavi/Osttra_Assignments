<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ page import="com.osttra.to.User, java.util.List"%>

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
<title>Welcome to School Management System</title>
<style>
a {
	text-decoration: none;
}
</style>
</head>
<body>

	<jsp:include page="header.jsp" />

	<%
	User user = (User) session.getAttribute("user");
	List<User> users = (List<User>) request.getAttribute("users");
	List<User> students = (List<User>) request.getAttribute("students");
	%>

	<h1 style="margin-left: 550px; margin-top: 90px">Book Management</h1>

	<h5>${ updateSuccessMsg }</h5>

	<h2 style="margin-left: 630px; margin-top: 15px; color: Black">Your
		Profile</h2>

	<table class="table">
		<thead>
			<tr>
				<th scope="col">No.</th>
				<th scope="col">First Name</th>
				<th scope="col">Last Name</th>
				<th scope="col">User Name</th>
				<th scope="col">Email</th>
				<th scope="col">User Type</th>
				<th scope="col">Class Name</th>
				<th scope="col">Delete</th>
				<th scope="col">Update</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>1</td>
				<td><%=user.getFirstName()%></td>
				<td><%=user.getLastName()%></td>
				<td><%=user.getUserName()%></td>
				<td><%=user.getEmail()%></td>
				<td><%=user.getUserType()%></td>
				<td><%=user.getClassName()%></td>
				<td><a href="/delete/<%=user.getUserName()%>">Delete</a></td>
				<td><a href="/update/<%=user.getUserName()%>">Update</a></td>
			</tr>
		</tbody>
	</table>

	<hr>
	<!-- For Admin -->

	<%
	if (users != null && user.getUserType().equalsIgnoreCase("admin") && users.size() > 1) {
	%>
	<h2
		style="margin-left: 630px; margin-top: 15px; margin-bottom: 7px; color: Black">User
		Profile is listed below</h2>
	<table class="table table-striped table-hover">
		<thead>
			<tr>
				<th scope="col">No.</th>
				<th scope="col">First Name</th>
				<th scope="col">Last Name</th>
				<th scope="col">User Name</th>
				<th scope="col">Email</th>
				<th scope="col">User Type</th>
				<th scope="col">Class Name</th>
				<th scope="col">Delete</th>
				<th scope="col">Update</th>
				<th scope="col">Status</th>
				<th scope="col">Allow</th>
				<th scope="col">Block</th>
			</tr>
		</thead>
		<tbody>
			<%
			int count = 0;
				for (int i = 0; i < users.size(); i++) {

					if (!(user.getUserName().equals(users.get(i).getUserName()))) {
						count++;
			%>

			<tr>
				<td><%=count%></td>
				<td><%=users.get(i).getFirstName()%></td>
				<td><%=users.get(i).getLastName()%></td>
				<td><%=users.get(i).getUserName()%></td>
				<td><%=users.get(i).getEmail()%></td>
				<td><%=users.get(i).getUserType()%></td>
				<td><%=users.get(i).getClassName()%></td>
				<td><a href="/delete/<%=users.get(i).getUserName()%>">Delete</a></td>
				<td><a href="/update/<%=users.get(i).getUserName()%>">Update</a></td>
				<td>
					<div class="form-check form-switch">
						<form id="myForm" action="submit.php" method="post">
							<label> <input class="form-check-input" type="checkbox"
								name="mySwitch" id="toggleSwitch"
								<% if (users.get(i).getStatus().equals("Active")) out.print("checked"); %> />
							</label>
						</form>
					</div>
					<script>
        var switchElement = document.getElementById('toggleSwitch');
		console.log("in");
        switchElement.addEventListener('change', function () {
        	console.log("hello0oo");
            if (switchElement.checked) {
            	console.log("hello");
            	let str = "/allow"+users.get(i).getUserName();
                window.location.href = str;
            } else{
            	let str = "/block"+users.get(i).getUserName();
                window.location.href = str;
            }
        });
    </script></td>
				<td><a href="/allow/<%=users.get(i).getUserName()%>">Allow</a></td>
				<td><a href="/block/<%=users.get(i).getUserName()%>">Block</a></td>
			</tr>
			<%
			}
				}
				}
			%>
		</tbody>
	</table>


	<!-- For Teacher -->

	<%
		if (students != null && user.getUserType().equalsIgnoreCase("teacher") && students.size() > 1) {
		%>
	<h2
		style="margin-left: 630px; margin-top: 15px; margin-bottom: 7px; color: Black">Students
		Profile is listed below</h2>
	<table class="table table-striped table-hover">
		<thead>
			<tr>
				<th scope="col">No.</th>
				<th scope="col">First Name</th>
				<th scope="col">Last Name</th>
				<th scope="col">User Name</th>
				<th scope="col">Email</th>
				<th scope="col">Class Name</th>

			</tr>
		</thead>
		<tbody>
			<%
			int count = 0;
				for (int i = 0; i < students.size(); i++) {

					if (!(user.getUserName().equals(students.get(i).getUserName()))) {
						count++;
			%>

			<tr>
				<td><%=count%></td>
				<td><%=students.get(i).getFirstName()%></td>
				<td><%=students.get(i).getLastName()%></td>
				<td><%=students.get(i).getUserName()%></td>
				<td><%=students.get(i).getEmail()%></td>
				<td><%=students.get(i).getClassName()%></td>

			</tr>
			<%
			}
			}
			}
			%>
		</tbody>
	</table>



</body>
</html>
