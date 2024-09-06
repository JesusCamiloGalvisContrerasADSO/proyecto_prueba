<%-- 
    Document   : ConfirRegistro
    Created on : 20/08/2024, 6:33:50 p. m.
    Author     : hp
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Registro</title>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
  <link rel="stylesheet" href="css/style.css">

</head>

<body>
  <!-- este es el encabezado de la pagina -->
  <header class=" fondo_header sombras--contenedor">
    <div class="container encabezado">

      <div class="encabezado">
        <img class="logo" src="../../Recursos/logo/logo-BoviControl.svg" alt="">
        <p>BoviControl</p>
      </div>
    </div>
  </header>
  <!-- en este estan cada uno de los campos del registro como, nombre, apellidos, fecha de nacimiento, tipo de documento entre otros -->
  <main class="espaciador">
    <section class="center registro__fondo registro__comfir ">
      <div class="container center registro registro__comfir--container sombras--contenedor">
        <div>
          <h1 class="registro__titulo">Registrado</h1>
          <hr class="registro__linea">
        </div>
        <div>
          <p>
            Tu usuario fue registrado, vuelve a iniciar sesión
          </p>
        </div>
        <div>
          <a href="ControlUsuario?accion=index" class="boton boton__registro" >Iniciar sesion</a>
          
        </div>
      </div>
    </section>
  </main>
</body>

</html>