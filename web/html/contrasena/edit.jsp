<%
    request.setAttribute("pageTitle", "Agregar usuario");
%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="modelo.TipoSangre" %>
<%@ page import="modeloDAO.TipoSangreDAO" %>
<%@ page import="controlador.ControlTipSan" %>
<%@ page import="modelo.TipoDocum" %>
<%@ page import="modeloDAO.TipoDocDAO" %>
<%@ page import="controlador.ControlTipoDoc" %>
<%@ page import="controlador.ControlUsuario" %>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">

    <%@ include file="../../componentes/head.jsp" %>

<head>
    
    <link rel="stylesheet" href="../../css/style.css">
    <link rel="stylesheet" href="validacion.css">
</head>
<body>
    <!-- este es el encabezado de la pagina -->
    <header class=" fondo_header sombras--contenedor">
        <div class="container encabezado">
            
          <div class="encabezado">
              <%@ include file="../../componentes/btn_salir.jsp" %>
              <img class="logo" src="Recursos/logo-BoviControl.png" alt="">
                <p>BoviControl</p>
            </div>
        </div>
    </header>
    <!-- en este estan cada uno de los campos del registro como, nombre, apellidos, fecha de nacimiento, tipo de documento entre otros -->
    <main>
      <section class="center registro__fondo">
          <div  class="container center registro sombras--contenedor">
              <div>
                  <h1 class="registro__titulo">Editar contraseña</h1>
                  <hr class="registro__linea">
              </div>
              <form id="registro" action="ControlContrasena" method="POST" >
                  <% String id = request.getParameter("id"); %>
                  <input type="hidden" name="id" value="<%= id %>"> 
                  
                  <div class="registro__input">
                    <label for="contra">Escribe tu contraseña:</label>
                      <input id="contra" class="input_registro" name="txtContra" type="password" required>
                      
                  </div>
                  <div class="registro__input">
                    <label for="contraConfir">Confirma tu contraseña:</label>
                      <input id="contraConfir" class="input_registro" name="txtContra" type="password" required>
                      
                  </div>
                  
                <div>
                    
                    <button id="enviar" class="boton boton__registro" type="submit" name="accion" value="Actualizar">Actualizar</button>
                    
                </div>
              </form>
          </div>
      </section>
    </main>
    <%@ include file="/componentes/error_ingreso.jsp" %>
</body>
</html>