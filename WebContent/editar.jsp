<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html lang="es">
<head>
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Editar</title>

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
				<li class="nav-item active"><a class="nav-link" href="principal.do">Home
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
	
		<form action="actualizar.do" method="post">
			<div class="form-row">
				<div class="form-group col-md-6">
					<label for="cedula ">cedula:</label>
					<input type="text" class="form-control" id="cedula" name="cedula" value="${encontrada.cedula}">
				</div>
				<div class="form-group col-md-6">
					<label for="nombre ">Nombre:</label> 
						<input type="text" class="form-control" id="nombre" name="nombre" value="${encontrada.nombre}">
				</div>
			</div>

			<div class="form-row">
				<div class="form-group col-md-6">
					<label for="apellido">Apellido:</label> 
						<input type="text" class="form-control" id="apellido" name="apellido" value="${encontrada.apellido}" >
				</div>
				<div class="form-group col-md-6">
					<label for="telefono">Telefono:</label>
						<input type="tel" class="form-control" id="telefono" name="telefono" value="${encontrada.telefono}">
				</div>				
			</div>
			
			
			<div class="form-row">
				<div class="form-group col-md-6">
					<label for="producto">Producto:</label> 
					<input type="text" class="form-control" id="producto" name="producto" value="${encontrado.nombre }">
				</div>
				<div class="form-group col-md-6">
					<label for="status">Estatus:</label> 
						<input type="text" class="form-control" id="status" name="status" value="${encontrado.estatus }">
				</div>
			</div>
			<div class="form-group">
				<label for="descripcion">Descripcion:</label>
					<textarea class="form-control" id="descripcion" name="descripcion" rows="3"  >${encontrado.descripcion}</textarea>
			</div>
			 <button type="submit" class="btn btn-primary">Enviar</button>	
		</form>
	
	</div>
	<hr>
	<!-- Footer -->
	<footer class="container">
		<p>&copy; Company 2017-2018</p>
	</footer>
</body>
</html>