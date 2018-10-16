<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- Identifica que es una página de error -->
<%@ page isErrorPage="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Opss...</h1>
	<p>Sorry, an error occurred.</p>
	<p>Here is he exception stack trace:</p>
	<p><%=exception.getCause()%> </p>

	<%-- 		Ha ocurrido un error en la aplicacion :<%=exception.getMessage()%> --%>
	<%-- 	<br> Error Interno:<%=exception.getCause()%> --%>

</body>
</html>