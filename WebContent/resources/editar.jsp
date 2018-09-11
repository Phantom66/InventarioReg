<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html lang="es">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="../../../../favicon.ico">

<title>Editar</title>

<!-- Bootstrap core CSS -->
<link href="./css/bootstrap.min.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="./css/navbar-top.css" rel="stylesheet">
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
				<li class="nav-item"><a class="nav-link" href="#">Link</a></li>
				<li class="nav-item"><a class="nav-link disabled" href="#">Disabled</a>
				</li>
			</ul>
			<form class="form-inline mt-2 mt-md-0">
				<input class="form-control mr-sm-2" type="text" placeholder="Search"
					aria-label="Search">
				<button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
			</form>
		</div>
	</nav>
	<!--Contenet -->
	<div class="container">
	
		<form action="#" method="post">
			<div class="form-row">
				<div class="form-group col-md-6">
					<label for="cedula ">cedula:</label> 
						<input type="text" class="form-control" id="cedula" name="cedula">
				</div>
				<div class="form-group col-md-6">
					<label for="nombre ">Nombre:</label> 
						<input type="text" class="form-control" id="nombre" name="nombre">
				</div>
			</div>

			<div class="form-row">
				<div class="form-group col-md-6">
					<label for="apellido">Apellido:</label> 
						<input type="text" class="form-control" id="apellido" name="apellido" >
				</div>
				<div class="form-group col-md-6">
					<label for="telefono">Telefono:</label>
						<input type="tel" class="form-control" id="telefono" name="telefono">
				</div>				
			</div>
			
			
			<div class="form-row">
				<div class="form-group col-md-6">
					<label for="producto">Producto:</label> <input type="text"
						class="form-control" id="producto" name="producto" >
				</div>
				<div class="form-group col-md-6">
					<label for="status">Estatus:</label> <input type="text"
						class="form-control" id="status" name="status">
				</div>
			</div>
			<div class="form-group">
				<label for="descripcion">Descripcion:</label>
					<textarea class="form-control" id="descripcion" name="descripcion" rows="3" ></textarea>
			</div>
			 <button type="submit" class="btn btn-primary">Enviar</button>	
		</form>
	
	</div>
	<hr>
	<!-- Footer -->
	<footer>
		<div class="container">
		
		</div>

	</footer>


	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
		integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
		crossorigin="anonymous"></script>
	<script>
		window.jQuery
				|| document
						.write('<script src="../../assets/js/vendor/jquery-slim.min.js"><\/script>')
	</script>
	<script src="../../assets/js/vendor/popper.min.js"></script>
	<script src=".js/bootstrap.min.js"></script>
</body>