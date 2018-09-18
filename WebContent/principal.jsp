<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html lang="es">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<title>Principal</title>

<!-- Bootstrap core CSS -->
<link href="css/bootstrap.min.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="css/navbar-top.css" rel="stylesheet">
</head>

<body>
	<!-- Navbar -->
	<nav class="navbar navbar-expand-md navbar-dark bg-dark mb-4">
		<a class="navbar-brand" href="#">Top navbar</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarCollapse" aria-controls="navbarCollapse"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarCollapse">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item active"><a class="nav-link" href="#">Home
						<span class="sr-only">(current)</span>
				</a></li>
				<li class="nav-item"><a class="nav-link" href="crear.do">Crear</a></li>
				<li class="nav-item"><a class="nav-link disabled" href="#">${sessionUsuario}</a>
				</li>
			</ul>
			
			<form action="login.do" method="post">
				<div>
					<input type="hidden" name="cerrarSession">
					<button type="submit" class="btn btn-primary">cerrar</button>	
				</div>
			</form>
			<form class="form-inline mt-2 mt-md-0">
				<input class="form-control mr-sm-2" type="text" placeholder="Search"
					aria-label="Search">
				<button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
			</form>
		</div>
	</nav>
	<!--Contenet -->
	<div class="container">
		<!-- Content here -->
		<table class="table">
			<thead class="thead-dark">
				<tr>
					<th scope="col">#</th>
					<th scope="col">Cedula</th>
					<th scope="col">Nombres y Apellidos</th>
					<th scope="col">Telefono</th>
					<th scope="col"></th>
					<th scope="col"></th>
					<th scope="col"></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="temporal" items="${Lista_Productos}">
					<tr>
						<th scope="row">1</th>
						<td>${temporal.cedula}</td>
						<td>${temporal.nombre } ${temporal.apellido}</td>
						<td>${temporal.telefono}</td>
						<td><a class="btn btn-primary" href="#" role="button">PDF</a></td>
						<td><a class="btn btn-primary" href="editar.do?cedula=${temporal.cedula}" role="button">Editar</a></td>
						<td><a class="btn btn-primary" href="borrar.do?cedula=${temporal.cedula}" role="button">Eliminar</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

		<!--Pagination -->
		<nav aria-label="...">
			<ul class="pagination justify-content-center">
				<li class="page-item disabled"><a class="page-link" href="#" tabindex="-1">Previous</a></li>
				<li class="page-item"><a class="page-link" href="#">1</a></li>
				<li class="page-item active"><a class="page-link" href="#">2<span class="sr-only">(current)</span>
				</a></li>
				<li class="page-item"><a class="page-link" href="#">3</a></li>
				<li class="page-item"><a class="page-link" href="#">Next</a></li>
			</ul>
		</nav>
	</div>
	<hr>
	
	<!-- Footer -->
	<footer class="container">
		<p>&copy; Company 2017-2018</p>
	</footer>
</body>
</html>