<%@ include file="/layout/header.jsp"%>
<!--Contenet -->
<div class="container">

	<form action="Actualizar.do" method="post">
		<div class="form-row">
			<div class="form-group col-md-6">
				<label for="cedula ">cedula:</label>
				<!-- Lo hago de esta manera, debido que al tener el campo disabled no env�a el valor.  -->
				<!-- Mejorar  -->
				<input type="hidden" name="cedula" value="${encontrada.cedula}">
				<input type="text" class="form-control" id="cedula" value="${encontrada.cedula}" disabled>
			</div>
			<div class="form-group col-md-6">
				<label for="nombre ">Nombre:</label> 
				<input type="text" class="form-control" id="nombre" name="nombre" value="${encontrada.nombre}">
			</div>
		</div>

		<div class="form-row">
			<div class="form-group col-md-6">
				<label for="apellido">Apellido:</label> <input type="text"
					class="form-control" id="apellido" name="apellido"
					value="${encontrada.apellido}">
			</div>
			<div class="form-group col-md-6">
				<label for="telefono">Telefono:</label> <input type="tel"
					class="form-control" id="telefono" name="telefono"
					value="${encontrada.telefono}">
			</div>
		</div>

		<c:forEach var="productos" items="${encontrada.productos }" >
			<div class="form-row">
				<div class="form-group col-md-2">
					<label for="producto">#</label> <input type="text"
						class="form-control" id="id_producto" name="id_producto"
						value="${productos.id }">
				</div>
				<div class="form-group col-md-5">
					<label for="producto">Producto:</label> <input type="text"
						class="form-control" id="producto" name="producto"
						value="${productos.nombre }">
				</div>
				<div class="form-group col-md-5">
					<label for="status">Estatus:</label> <input type="text"
						class="form-control" id="status" name="status"
						value="${productos.estatus}">
				</div>
			</div>
			<div class="form-group">
				<label for="descripcion">Descripcion:</label>
				<textarea class="form-control" id="descripcion" name="descripcion"
					rows="3">${productos.descripcion}</textarea>
			</div>

		</c:forEach>

		<button type="submit" class="btn btn-primary">Enviar</button>
	</form>

</div>
<hr>
<%@ include file="/layout/footer.jsp"%>