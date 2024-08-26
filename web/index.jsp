
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
      <div class="container ingresar">
        <!-- aqui se realizaron dos div, uno es el que corresponde al logo, nombre y eslogan y el otro corresponde a los datos donde se valida para ingresar  -->
        <div class="logo_apli">
            <img src="Recursos/logo-BoviControl.png" class="logo_login"  alt="logo">
          <p class="Nombre_login">BoviControl</p>
          <p class="Texto_login">Control de pesos en ganado bovino</p>

        </div>
        <h1 class="titulo_mobile_login">Iniciar sesión</h1>
        <div class="datos_logeo center">
            <form action="ControlUsuario" method="POST" class="center">
            <!-- ingresar numero de documento y contraseña  -->

            <input class="input_ingresar" name="txtDocum" id="num_doc" placeholder="Ingresa el numero de documento" type="number" >
            
            <input class="input_ingresar" name="txtContra" placeholder="Ingresa tu contraseña" type="password" >  
            <br>
            <button class="boton boton--ingresar" type="submit" name="accion" value="Ingresar">Ingresar</button>
          </form>
          <!-- botones de ingreso, olvido contraseña o registrarse  -->
          <div class="center">
            <hr>
            <div class="boton__usuario">
                <a class="boton boton--modificador" href="ControlUsuario?accion=add">Registrarse</a>
              <a class="boton boton__olvidar " href="">¿Olvidaste tu contraseña?</a>
              <br>
              <a class="boton boton__olvidar " href="paginasAcciones.html">Todas las paginas</a>
            </div>
            
            
            
            
            
            
          </div>
        </div>
      </div>
    </section>
  </main>

</body>

</html>
