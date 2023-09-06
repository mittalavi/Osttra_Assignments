<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Registration</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9"
	crossorigin="anonymous">

</head>
<body>

	<h2 style="margin: 30px 500px">Welcome to Registration Page</h2>

	<form action="/registration" method="POST">
		<div style="margin-left: 500px; margin-right: 600px">

			<div class="mb-3">
				<label for="firstName" class="form-label">First Name</label> <input
					type="text" name="firstName" class="form-control" id="firstName"
					aria-describedby="firstName">

			</div>

			<div class="mb-3">
				<label for="lastName" class="form-label">Last Name</label> <input
					type="text" name="lastName" class="form-control" id="lastName"
					aria-describedby="lastName">

			</div>


			<div class="mb-3">
				<label for="exampleInputEmail1" class="form-label">Email
					address</label> <input type="email" name="email" class="form-control"
					id="exampleInputEmail1" aria-describedby="emailHelp">

			</div>

			<div class="mb-3">
				<label for="userName" class="form-label">Username</label> <input
					type="text" name="userName" class="form-control" id="userName"
					aria-describedby="userName">

			</div>

			<div class="mb-3">
				<label for="password" class="form-label">Password</label> <input
					type="password" name="password" class="form-control" id="password">
			</div>

			<div class="form-check">
				<input class="form-check-input" type="radio" name="userType"
					id="flexRadioDefault1" value="admin"> <label class="form-check-label"
					for="flexRadioDefault1"> Admin </label>
					</div>

			<div class="form-check">
				<input class="form-check-input" type="radio" name="userType"
					id="flexRadioDefault2" value="student" checked> <label
					class="form-check-label" for="flexRadioDefault2"> Student </label>
					</div>

			<div class="form-check">
				<input class="form-check-input" type="radio" name="userType"
					id="flexRadioDefault2" value="teacher"> <label
					class="form-check-label" for="flexRadioDefault2"> Teacher </label>
			</div>
			 <br> <select class="form-select" name=className>
				<option value="1">1st year</option>
				<option value="2">2nd year</option>
				<option value="3">3rd year</option>
				<option value="4">Final year</option>
			</select> <br>
			<button type="submit" class="btn btn-primary">Submit</button>
			<a href="/" class="btn btn-primary" role="button">Login</a>

		</div>
	</form>
</body>
</html>