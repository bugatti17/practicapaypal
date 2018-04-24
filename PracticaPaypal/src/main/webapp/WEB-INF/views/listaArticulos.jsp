<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<c:forEach items= "${listaArticulos}" var="lista">
<th><c:out value="${lista.nombre}" /></th>
<th><c:out value="${lista.precio}" /></th>
<th><c:out value="${lista.codigo}" /></th>
</tr>
</c:forEach> 
</table>
</body>
</html>