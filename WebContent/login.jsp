<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<title>Login</title>

<!-- Bootstrap core CSS -->
<link href="css/bootstrap.min.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="css/signin.css" rel="stylesheet">
</head>
<body class="text-center">
	<form class="form-signin" action="${pageContext.request.contextPath}/j_security_check" method="POST">
		<img class="mb-4"
			src="https://getbootstrap.com/docs/4.0/assets/brand/bootstrap-solid.svg"
			alt="" width="72" height="72">
		<h1 class="h3 mb-3 font-weight-normal">Please sign in</h1>
		<label for="inputEmail" class="sr-only">Email address</label> 
			<input type="text" id="username" class="form-control" name="j_username" placeholder="admin" required autofocus> 
		<label for="inputPassword" class="sr-only">Password</label> 
			<input type="password" id="inputPassword" class="form-control" name="j_password" placeholder="password" required>
		<div class="checkbox mb-3">
			<label> 
				<input type="checkbox" value="remember-me"> Remember me
			</label> 
			<label> 
				<a href="registro_user.jsp">Registrarse</a>
			</label>
			<c:if test="${not empty messageError}">
				<%@ include file="errorPage/errorUser_Password.jsp" %>
			</c:if>
			<c:if test="${not empty messageSuccess}">
				<%@ include file="errorPage/regSuccess.jsp"%>
			</c:if>
		</div>
		<button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
		<p class="mt-5 mb-3 text-muted">&copy; 2017-2018</p>
	</form>
</body>
</html>
