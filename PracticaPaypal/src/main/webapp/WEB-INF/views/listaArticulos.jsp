<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Tu Tienda online</title>
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

    <div class="collapse navbar-collapse" id="myNavbar">
      <ul class="nav navbar-nav">
        <li class="active"> <a>Inicio</a></li>
        <li><a href="Modificar">Modificar Usuario</a></li>
        <li><a href="cerrarSesion">Cerrar sesión</a></li>
        <li><a href="Carrito">Carrito de Productos</a></li>
      </ul>
    </div>
  </div>
</nav>

 
<div class="container">    
<c:forEach items= "${listaArticulos}" var="lista">
  <c:if test= "${lista.codigo == 0}" >
  <div class="row">
    <div class="col-sm-4">
      <div class="panel panel-primary">
        <div class="panel-heading">Liquidación</div>
        <div class="panel-body"><img src="resources/images/bmc.jpg" class="img-responsive" style="width:100%"></div>
        <div class="panel-footer">Articulo: ${lista.nombre}<br>Precio: ${lista.precio }
       <!-- Botones para el carrito  -->
	 <form action="Add" method=post>
	 <input type="hidden" name="accion_servlet" value="accion1">
	<input type=submit value=Añadir></form>
      </div>
      </div>
    </div>
    </c:if>
    <c:if test= "${lista.codigo == 1}" >
    <div class="col-sm-4"> 
      <div class="panel panel-primary">
        <div class="panel-heading">Nuevo</div>
        <div class="panel-body"><img src="resources/images/mondraker.jpg" class="img-responsive" style="width:100%"></div>
		<div class="panel-footer">Articulo: ${lista.nombre}<br>Precio: ${lista.precio }
		 <!-- Botones para el carrito  -->
		 <form action="Add" method=post>
		 <input type="hidden" name="accion_servlet" value="accion2">
		<input type=submit value=Añadir></form>
		
		</div>
      </div>
    </div>
    </c:if>
    <c:if test= "${lista.codigo == 2}" >
    <div class="col-sm-4"> 
      <div class="panel panel-primary">
        <div class="panel-heading">Oferta</div>
        <div class="panel-body"><img src="resources/images/santa_cruz.jpg" class="img-responsive" style="width:100%"></div>
     <div class="panel-footer">Articulo: ${lista.nombre}<br>Precio: ${lista.precio }
       <!-- Botones para el carrito  -->
	 <form action="Add" method=post>
	 <input type="hidden" name="accion_servlet" value="accion3">
	<input type=submit value=Añadir></form>

      </div>
    </div>
  </div>
  </c:if>
  </c:forEach>
</div><br><br>



<footer class="container-fluid text-center">
  <p>Tienda Online Copyright</p>  
 <p>José Antonio & Juan Francisco</p>
</footer>

</body>
</html>
