<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html lang="es">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Formulario de Crear</title>

<!-- Bootstrap core CSS -->
<link href="css/bootstrap.min.css" rel="stylesheet">

</head>
<body>
	<div class="container">
		<div class="row justify-content-md-center">
			<div class="col-md-4 col-md-offset-4">
				<div class="login-panel panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title text-center">Registrarse</h3>
					</div>
					<div class="panel-body">
						<form action="RegUser.do" method="post" role="form">
							<fieldset>

								<div class="form-group">
									<label for="cedula">Cedula</label> 
									<input type="text" class="form-control" id="cedula" name="cedula" placeholder="cedula (12345678)" autofocus required>
								</div>
								<div class="form-group">
									<label for="nombre">Nombre</label> 
									<input type="text" class="form-control" id="nombre" name="nombre" placeholder="Nombre(Andres, José, Juan ..)" autofocus required>
								</div>
								
								<div class="form-group">
									<label for="apellido">Apellido</label> 
									<input type="text" class="form-control" id="apellido" name="apellido" placeholder="Apellido(Perez, Quero ..)" autofocus required>
								</div>
								<div class="form-group">
									<label for="telefono">Telefono</label> 
									<input type="text" class="form-control" id="telefono" name="telefono" placeholder="Telefon(04120000000)" autofocus required>
								</div>
								
								<div class="form-group">
									<label for="user">Pseudonimo</label> 
									<input type="text" class="form-control" id="user" name="user" placeholder="Pseudo(Leo, Pepito ..)" autofocus required>
								</div>
								
								<div class="form-group">
									<label for="email">Email</label> 
									<input type="email" class="form-control" id="email" name="email" placeholder="Email" required>
								</div>

								<div class="form-group">
									<label for="pass">Password</label> 
									<input type="password" class="form-control" id="pass" name="pass" placeholder="Password" required>
								</div>

								<div class="form-group">
									<label for="passConfirm"> Confirm Password</label> 
									<input type="password" class="form-control" id="passConfirm" name="passConfirm" placeholder="Confirme Password" required>
								</div>
								<c:if test="${not empty messageError}">
									<%@ include file="errorPage/errorUser_Password.jsp"%>
								</c:if>
								<button type="submit" class="btn btn-primary">Registrar</button>
							</fieldset>
						</form>
					</div>

				</div>
			</div>
		</div>
	</div>
</body>
</html>