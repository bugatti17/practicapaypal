<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Carrito de Compra</title>
</head>
<body>
<h2>Carrito de la compra </h2>
<c:if test= "${Cantidad1 == 0 && Cantidad2 == 0 && Cantidad3 == 0}" >
<p>No hay nada para comprar</p>
<!-- Boton que sigue comprando -->
<form action="sigueComprando" method=post>
<input type=submit value=sigueComprando></form>
</c:if>


<c:if test= "${Cantidad1 > 0 || Cantidad2 > 0 || Cantidad3 > 0}" >
 <p>Sus artículos elegidos son: </p>
 <c:if test="${Cantidad1 >0}">
 <p> Articulo con código 0: </p>
 <p>Su cantidad elegida es: ${Cantidad1} </p>
 <p>El valor de cada artículo es: ${Precio1}</p>
 </c:if>
 <c:if test="${Cantidad2 >0}">
 <p> Articulo con código 1: </p>
 <p>Su cantidad elegida es: ${Cantidad2} </p>
 <p>El valor de cada artículo es: ${Precio2}</p>
 </c:if>
 <c:if test="${Cantidad3 >0}">
 <p> Articulo con código 2: </p>
 <p>Su cantidad elegida es: ${Cantidad3} </p>
 <p>El valor de cada artículo es: ${Precio3}</p>
 </c:if>
 
<p>Su cantidad a pagar en euros son:  ${Suma}</p>

<!-- Boton que cancela el pago total -->
<form action="cancelaSuma" method=post>
<input type=submit value=CancelarPago></form>

<!-- Boton que sigue comprando -->
<form action="sigueComprando" method=post>
<input type=submit value=sigueComprando></form>

<form action="https://www.sandbox.paypal.com/cgi-bin/webscr" method="post" target="_top">
<input type="hidden" name="cmd" value="_xclick">
<input type="hidden" name="business" value="aom00033-facilitator@red.ujaen.es">
<input type="hidden" name="lc" value="ES">

<input type="hidden" name="item_name" value="Comprar Ahora">
<input type="hidden" name="button_subtype" value="services">
<input type="hidden" name="no_note" value="0">
<input type="hidden" name="cn" value="Añadir instrucciones especiales para el vendedor:">
<input type="hidden" name="no_shipping" value="2">
<input type="hidden" name="rm" value="1">
<input type="hidden" name="currency_code" value="EUR">
<input type="hidden" name="amount" value="${Suma}">
<input type="hidden" name="return" value="http://localhost:8080/practicaPaypal/">
<input type="hidden" name="cancel_return" value="http://localhost:8080/practicaPaypal/">
<input type="hidden" name="bn" value="PP-BuyNowBF:btn_paynowCC_LG.gif:NonHosted">
<input type="image" src="https://www.sandbox.paypal.com/es_ES/ES/i/btn/btn_paynowCC_LG.gif" border="0" name="submit" alt="PayPal, la forma rápida y segura de pagar en Internet.">
<img alt="" border="0" src="https://www.sandbox.paypal.com/es_ES/i/scr/pixel.gif" width="1" height="1">
</form>
</c:if>


</body>
</html>