<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Modificar</title>
</head>
<body>
<h2>Modificación de usuarios</h2>
<form action="ServletModificar" method=post>
Nombre: <input type=text name=username>
<br>
Password: <input type=password name= pass>
<br>
Email: <input type=text name= email>
<br>
DNI: <input type=text name= dni maxlength="8">
<br>
<input type=submit value=Modificar>
</form>
</body>
</html>