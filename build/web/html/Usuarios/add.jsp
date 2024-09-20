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
                  <h1 class="registro__titulo">Registrate para continuar</h1>
                  <hr class="registro__linea">
              </div>
              <form id="registro" action="ControlUsuario" method="POST" >
                  <div class="registro__input">
                    <label for="nombre">Nombre:</label>
                      <input id="nombre" class="input_registro" name="txtNom" type="text" required>
                      
                  </div>
                  <div class="registro__input">
                    <label for="apellido">Apellido:</label>
                      <input id="apellido" class="input_registro" name="txtApell" type="text" required>
                      
                  </div>
                  <div class="registro__input">
                    <label for="numDoc">Ingresa tu documento:</label>
                      <input id="numDoc" class="input_registro" name="txtNumDoc" type="number" required>
                      
                  </div>
                  <div class="registro__input">
                    <label for="tipDoc">Tipo de documento:</label>
                    <select class="input_registro" name="txtTipDoc" id="tipDoc" required>
                        <option value="" disabled="" selected=""></option>
                        <% 
                        TipoDocDAO docDao = new TipoDocDAO();
                        List<TipoDocum> docLista = docDao.listar();
                        Iterator<TipoDocum> docIter = docLista.iterator();
                        while (docIter.hasNext()) {
                            TipoDocum docTipo = docIter.next();
                        %>
                            <option value="<%= docTipo.getId() %>"><%= docTipo.getNom() %></option>
                        <% } %>
                    </select>
                  </div>
                  <div class="registro__input">
                    <label for="tipSan">Tipo de sangre:</label>
                    <select class="input_registro" name="txtTipSang" id="tipSan" required>
                        <option value="" disabled="" selected=""></option>
                        <% 
                        TipoSangreDAO sangreDao = new TipoSangreDAO();
                        List<TipoSangre> sangreLista = sangreDao.listar();
                        Iterator<TipoSangre> sangreIter = sangreLista.iterator();
                        while (sangreIter.hasNext()) {
                            TipoSangre sangreTipo = sangreIter.next();
                        %>
                            <option value="<%= sangreTipo.getId() %>"><%= sangreTipo.getNom() %></option>
                        <% } %>
                    </select>
                  </div>
                  
                  <div class="registro__input">
                    <label for="telefono">Teléfono:</label>
                      <input id="telefono" class="input_registro" name="txtTel" type="number" required>
                      
                  </div>
                  <div class="registro__input">
                    <label for="contra">Escribe tu contraseña:</label>
                      <input id="contra" class="input_registro" name="txtContra" type="password" required>
                      
                  </div>
                  <div class="registro__input">
                    <label for="contraConfir">Confirma tu contraseña:</label>
                      <input id="contraConfir" class="input_registro" name="txtContra" type="password" required>
                      
                  </div>
                  <div class="registro__input">
                      <label for="correo">Ingresa tu correo:</label>
                      <input id="correo" class="input_registro" name="txtCorreo" type="email" required>
                      
                  </div>
                  <div class="registro__input condiciones">
                    <input  type="checkbox" id="politicas">
                    <label for="politicas" >politicas de uso </label>
                </div>
                <div>
                    
                    <button id="enviar" class="boton boton__registro" type="submit" name="accion" value="Agregar">Registrarse</button>
                    
                </div>
              </form>
          </div>
      </section>
    </main>
    <%@ include file="/componentes/error_ingreso.jsp" %>
</body>
</html>