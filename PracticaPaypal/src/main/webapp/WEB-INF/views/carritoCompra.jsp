<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
  <title>Tu Tienda Online</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>






  <style>
    /* Remove the navbar's default rounded borders and increase the bottom margin */ 
    .navbar {
      margin-bottom: 150px;
      border-radius: 0;
    }
    
    /* Remove the jumbotron's default bottom margin */ 
     .jumbotron {
      margin-bottom: 0;
    }
   
    /* Add a gray background color and some padding to the footer */
    
   
 
   h3 { text-align: rigth}


 footer {
      background-color: #d1d1d1;
      padding: 20px;
    }
  </style>
</head>
<body>




<div class="jumbotron">
  <div class="container text-center">
    <h1>Tu tienda online de bicicletas</h1>      
  </div>
</div>

<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>                        
      </button>
      
    </div>
    <div class="collapse navbar-collapse" id="myNavbar">
      <ul class="nav navbar-nav">
        <li class="active"><a href="sigueComprando">Inicio</a></li>
        <li><a href="Modificar">Modificar Usuario</a></li>
        <li><a href="cerrarSesion">Cerrar sesión</a></li>
        <li><a href="Carrito">Carrito de Productos</a></li>
      </ul>
    </div>
  </div>
</nav>

<text align="center">
<div class="container"> 
<h4>
<c:if test= "${Cantidad1 == 0 && Cantidad2 == 0 && Cantidad3 == 0}" >
<p>Su Carrito está vacío</p>
<!-- Boton que sigue comprando -->
<form action="sigueComprando" method=post>
<input type=submit value="Volver a la tienda"></form>
</c:if>


<c:if test= "${Cantidad1 > 0 || Cantidad2 > 0 || Cantidad3 > 0}" >
 <p>Sus artículos elegidos son: </p>
 <c:if test="${Cantidad1 >0}">
 <p> Articulo con código 0: </p>
 <p>Su cantidad elegida es: ${Cantidad1} </p>
 <p><b>El precio total del primer articulo es: ${Precio1}</b></p>
 </c:if>
 <c:if test="${Cantidad2 >0}">
 <p> Articulo con código 1: </p>
 <p>Su cantidad elegida es: ${Cantidad2} </p>
 <p><b>El precio total del segundo articulo es: ${Precio2}</b></p>
 </c:if>
 <c:if test="${Cantidad3 >0}">
 <p> Articulo con código 2: </p>
 <p>Su cantidad elegida es: ${Cantidad3} </p>
 <p><b>El precio total del tercer articulo es: ${Precio3}</b></p>
 </c:if>
 
 <p></p>
 <p></p>
 <p></p>
 <p></p>
<p><FONT size=5><strong>Su cantidad total a pagar en euros es de:  ${Suma}</strong></FONT></p>

<!-- Boton que cancela el pago total -->
<form action="cancelaSuma" method=post>
<input type=submit value=Cancelar></form>

<!-- Boton que sigue comprando -->
<form action="sigueComprando" method=post>
<input type=submit value="Volver a la tienda"></form>

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
<input type="hidden" name="return" value="http://localhost:8081/practicaPaypal/">
<input type="hidden" name="cancel_return" value="http://localhost:8080/practicaPaypal/">
<input type="hidden" name="bn" value="PP-BuyNowBF:btn_paynowCC_LG.gif:NonHosted">
<input type="image" src="https://www.sandbox.paypal.com/es_ES/ES/i/btn/btn_paynowCC_LG.gif" border="0" name="submit" alt="PayPal, la forma rápida y segura de pagar en Internet.">
<img alt="" border="0" src="https://www.sandbox.paypal.com/es_ES/i/scr/pixel.gif" width="1" height="1">
</form>
</c:if>

</text>
</div>
</h4>


<footer class="container-fluid text-center">
  <p>Tienda Online Copyright</p>  
 <p>José Antonio & Juan Francisco</p>
</footer>




</body>
</html>