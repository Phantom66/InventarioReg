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
						<form action="reg_user.do" method="post" role="form">
							<fieldset>
								<div class="form-group">
									<label for="user">Nombre</label> 
									<input type="text" class="form-control" id="user" name="user" placeholder="Name(Andres, José, Juan ..)" autofocus>
								</div>
								
								<div class="form-group">
									<label for="email">Email</label> 
									<input type="email" class="form-control" id="email" name="email" placeholder="Email">
								</div>

								<div class="form-group">
									<label for="pass">Password</label> 
									<input type="password" class="form-control" id="pass" name="pass" placeholder="Password">
								</div>

								<div class="form-group">
									<label for="passConfirm"> Confirm Password</label> 
									<input type="password" class="form-control" id="passConfirm" name="passConfirm" placeholder="Confirme Password">
								</div>
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