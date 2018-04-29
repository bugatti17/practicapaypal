<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Carrito de Compra</title>
</head>
<body>
<h2>Carrito de la compra </h2>
 
<p>Su cantidad a pagar en euros son:  ${Suma}</p>

<!-- Boton que cancela el pago total -->
<form action="cancelaSuma" method=post>
<input type=submit value=CancelarPago></form>

<!-- Boton que sigue comprando el pago total -->
<form action="sigueComprando" method=post>
<input type=submit value=sigueComprando></form>



</body>
</html>