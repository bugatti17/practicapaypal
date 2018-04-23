<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Articulos</title>
</head>
<body>
<table border = "1">
<tr>
<th> Nombre </th>
<th> Precio </th>
<th> Codigo </th>
</tr>

<tr>
<c:forEach items= "${lista}" var="list">
<th><c:out value="${list.nombre}" /></th>
<th><c:out value="${list.password}" /></th>
<th><c:out value="${list.email}" /></th>
<th><c:out value="${list.dni}" /></th>
</tr>
</c:forEach> 
</table>
</body>
</html>