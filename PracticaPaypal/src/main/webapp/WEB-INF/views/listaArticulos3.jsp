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


<c:forEach items= "${listaArticulos}" var="lista">
<tr>
<th><c:out value="${lista.nombre}" /></th>
<th><c:out value="${lista.precio}" /></th>
<th><c:out value="${lista.codigo}" /></th>
<img src="/practicaPaypal/resources/pantalon.jpg">
</tr>
</c:forEach>

</table>
 <!-- Botones para el carrito  -->
 <form action="Add" method=post>
 <input type="hidden" name="accion_servlet" value="accion1">
<input type=submit value=Añadir></form>


<form action="Add" method=post>
 <input type="hidden" name="accion_servlet" value="accion2">
<input type=submit value=Añadir></form>

<form action="Add" method=post>
<input type="hidden" name="accion_servlet" value="accion3">
<input type=submit value=Añadir></form>


<!-- Boton para modificar usuario -->
<form action="Modificar" method=post>
<input type=submit value=Modificar>
</form>

<!-- Boton pago total para llevarnos al carrito -->
<form action="Carrito" method=post>
<input type=submit value=Pago></form>


</body>
</html>