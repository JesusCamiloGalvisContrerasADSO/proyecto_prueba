
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ include file="componentes/validacionAdmin.jsp" %>

<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Acciones</title>
  <link rel="stylesheet" href="css/style.css">

</head>

<body>
  <!-- este es el encabezado de la pagina -->
  <header class=" fondo_header">
    <div class="container encabezado">
      <div class="encabezado">
          <img class="logo" src="Recursos/logo-BoviControl.png" alt="logo">
        <p>BoviControl</p>
      </div>
    </div>
  </header>
  <!-- este es el cuerpo del aplicativo web -->
  <main>
    <section>
      <div class="container ingresar">
        <!-- aqui se realizaron dos div, uno es el que corresponde al logo, nombre y eslogan y el otro corresponde a los datos donde se valida para ingresar  -->
        
        <a class="boton boton__olvidar " href="ControlTipSan?accion=listar">Tipos de sangre</a>
        <br><!-- comment -->
        <a class="boton boton__olvidar " href="ControlTipoDoc?accion=listar">Tipos de documentos</a>
        <br><!-- comment -->
        <a class="boton boton__olvidar " href="ControlUsuario?accion=listar">Usuarios registrados</a>
        <br><!-- comment -->
        <a class="boton boton__olvidar " href="controlLote?accion=listar">Lotes </a>
        <br><!-- comment -->
        <a class="boton boton__olvidar " href="ControlRaza?accion=listar">Tipos de razas</a>
        
      </div>
    </section>
  </main>

</body>

</html>

