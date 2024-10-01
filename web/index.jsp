

<%
    request.setAttribute("pageTitle", "Iniciar secion");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">

<%@ include file="componentes/head.jsp" %>

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
      <div class="container ingresar ">
        <!-- aqui se realizaron dos div, uno es el que corresponde al logo, nombre y eslogan y el otro corresponde a los datos donde se valida para ingresar  -->
        <div class="logo_apli">
            <img src="Recursos/logo-BoviControl.png" class="logo_login"  alt="logo">
          <p class="Nombre_login">BoviControl</p>
          <p class="Texto_login">Control de pesos en ganado bovino</p>

        </div>
        <div class="datos_logeo center sombras--contenedor">
        <h1 class="">Iniciar sesión</h1>
            <form action="ControlLogin" method="POST" id="registro" class="center">
            <!-- ingresar numero de documento y contraseña  -->
            <input class="input_ingresar" type="number" name="txtDocum" id="numDoc" placeholder="Ingresa el numero de documento" type="" required="">
            
            <input id="contra" class="input_ingresar" name="txtContra" placeholder="Ingresa tu contraseña" type="password" required="">  
            <br>
            <button id="enviar" class="boton boton--ingresar" type="submit" name="accion" value="Ingresar">Ingresar</button>
          </form>
          <!-- botones de ingreso, olvido contraseña o registrarse  -->
          <div class="center">
            <hr>
            <div class="boton__usuario">
                <a class="boton boton--modificador" href="ControlUsuario?accion=add">Registrarse</a>
              
            </div>
            
          </div>
        </div>
      </div>
    </section>
  </main>
  
  <%@ include file="componentes/error_ingreso.jsp" %>

</body>

</html>
