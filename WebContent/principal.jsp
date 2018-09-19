<%@ include file="layout/header.jsp"%>

<!-- 	Tomar en cuenta para mejora -->
<!-- 			<form class="form-inline mt-2 mt-md-0"> -->
<!-- 				<input class="form-control mr-sm-2" type="text" placeholder="Search" -->
<!-- 					aria-label="Search"> -->
<!-- 				<button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button> -->
<!-- 			</form> -->
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
					<td>${temporal.nombre }${temporal.apellido}</td>
					<td>${temporal.telefono}</td>
					<td><a class="btn btn-primary" href="#" role="button">PDF</a></td>
					<td><a class="btn btn-primary"
						href="editar.do?cedula=${temporal.cedula}" role="button">Editar</a></td>
					<td><a class="btn btn-primary"
						href="borrar.do?cedula=${temporal.cedula}" role="button">Eliminar</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<!--Pagination -->
	<nav aria-label="...">
		<ul class="pagination justify-content-center">
			<li class="page-item disabled"><a class="page-link" href="#"
				tabindex="-1">Previous</a></li>
			<li class="page-item"><a class="page-link" href="#">1</a></li>
			<li class="page-item active"><a class="page-link" href="#">2<span
					class="sr-only">(current)</span>
			</a></li>
			<li class="page-item"><a class="page-link" href="#">3</a></li>
			<li class="page-item"><a class="page-link" href="#">Next</a></li>
		</ul>
	</nav>
</div>
<hr>
<%@ include file="layout/footer.jsp"%>