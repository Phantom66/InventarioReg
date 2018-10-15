<%@ include file="/layout/header.jsp"%>
<!--Contenet -->
<div class="container">

	<form action="Registrar.do" method="post">
		<div class="form-row">
			<div class="form-group col-md-6">
				<label for="cedula ">cedula:</label> <input type="text"
					class="form-control" id="cedula" name="cedula" placeholder="Cedula">
			</div>
			<div class="form-group col-md-6">
				<label for="nombre ">Nombre:</label> <input type="text"
					class="form-control" id="nombre" name="nombre" placeholder="Nombre">
			</div>
		</div>

		<div class="form-row">
			<div class="form-group col-md-6">
				<label for="apellido">Apellido:</label> <input type="text"
					class="form-control" id="apellido" name="apellido"
					placeholder="Apellido">
			</div>
			<div class="form-group col-md-6">
				<label for="telefono">Telefono:</label> <input type="tel"
					class="form-control" id="telefono" name="telefono"
					placeholder="Telefono">
			</div>
		</div>


		<div class="form-row">
			<div class="form-group col-md-6">
				<label for="producto">Producto:</label> <input type="text"
					class="form-control" id="producto" name="producto"
					placeholder="Producto">
			</div>
			<div class="form-group col-md-6">
				<label for="status">Estatus:</label> <input type="text"
					class="form-control" id="status" name="status"
					placeholder="Estatus">
			</div>
		</div>
		<div class="form-group">
			<label for="descripcion">Descripcion:</label>
			<textarea class="form-control" id="descripcion" name="descripcion"
				rows="3"></textarea>
		</div>
		<button type="submit" class="btn btn-primary">Enviar</button>
	</form>

</div>
<hr>
<%@ include file="/layout/footer.jsp"%>