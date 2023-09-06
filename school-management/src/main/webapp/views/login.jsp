<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login Page</title>

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9"
	crossorigin="anonymous">

</head>
<body>

	<%@ page session="false"%>
	
	
	<h2 style="margin: 30px 500px">Login Page</h2>
	<h5 style="margin-left: 500px; margin-right: 600px">${ logoutMessage }</h5>
	<h5 style="margin-left: 500px; margin-right: 600px">${ logoutErrorMessage }</h5>
	<h5 style="margin-left: 500px; margin-right: 600px">${ errorMessage }</h5>
	<h5 style="margin-left: 500px; margin-right: 600px">${ deleteSucessMsg }</h5>
	
	<form action="/login" method="POST">
		<div style="margin-left: 500px; margin-right: 600px">
			<div class="mb-3">
				<label for="userName" class="form-label">Username </label> <input
					type="text" name="userName" class="form-control" id="userName"
					aria-describedby="userName">

			</div>
			<div class="mb-3">
				<label for="exampleInputPassword1" class="form-label">Password</label>
				<input type="password" name="password" class="form-control"
					id="exampleInputPassword1">
			</div>
	<div class="container text-center">
        
			<button type="submit" class="btn btn-primary">Login</button>
		    <a href="/registration" class="btn btn-primary" role="button">Register</a>
</div>

		</div>
	</form>


</body>
</html>