<%
    request.setAttribute("pageTitle", "Editar usuario");
%>
<%@page import="modelo.Roles"%>
<%@page import="modeloDAO.RolesDAO"%>
<%@page import="modelo.TipoDocum"%>
<%@page import="modeloDAO.TipoDocDAO"%>
<%@page import="modelo.TipoSangre"%>
<%@page import="modeloDAO.TipoSangreDAO"%>
<%@ page import="java.util.*" %>
<%@ page import="modelo.Usuario" %>
<%@ page import="modeloDAO.UsuarioDAO" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../componentes/validacionAdmin.jsp" %>
<!DOCTYPE html>
<html lang="es">

<%@ include file="../../componentes/head.jsp" %>

<body>
    <!-- este es el encabezado de la pagina -->
    <header class=" fondo_header sombras--contenedor">
        <div class="container encabezado">
            
          <div class="encabezado">
              <%@ include file="../../componentes/btn_salir.jsp" %>
              <img class="logo" src="Recursos/logo-BoviControl.png" alt="">
                <p>BoviControl</p>
            </div>
            <div class="encabezado">
                <%@ include file="../../componentes/botones_header.jsp" %>
            </div>
        </div>
    </header>
    
    <main>
        <section class="center registro__fondo">
          <div  class="container center registro sombras--contenedor">
              <% 
                UsuarioDAO dao = new UsuarioDAO();
                int id = Integer.parseInt((String) request.getAttribute("idTipo"));
                Usuario user = dao.list(id);
              %>
              
              <div>
                  <h1 class="registro__titulo">Editar usuario</h1>
                  <hr class="registro__linea">
              </div>
              <form id="registro" action="ControlUsuario" method="POST">
                  
                <input type="hidden" name="txtid" value="<%= user.getIdUsuario()%>">
                  
                <div class="registro__input">
                      <label for="nombre">Nombre:</label>
                      <input class="input_registro" type="text" id="nombre" name="txtNom" value="<%=user.getNombre()%>" required="">
                  </div>
                  <div class="registro__input">
                      <label for="apellido">Apellido:</label>
                      <input class="input_registro" type="text" id="apellido" name="txtApell" value="<%=user.getApellido()%>" required="">
                  </div>
                  <div class="registro__input">
                      <label for="documento">Documento:</label>
                      <input class="input_registro" type="number" id="numDoc" name="txtNumDoc" value="<%=user.getDocumento()%>" required="" >
                  </div>
                  
                  <div class="registro__input">
                    <label for="tipoDocumento">Tipo de Documento:</label>
                    <select class="input_registro" name="txtTipDoc" id="tipDoc" required="">
                        <% 
                        TipoDocDAO docDao = new TipoDocDAO();
                        List<TipoDocum> docLista = docDao.listar();
                        Iterator<TipoDocum> docIter = docLista.iterator();
                        while (docIter.hasNext()) {
                            TipoDocum docTipo = docIter.next();
                            if((docTipo.getId()) == (user.getDocid())){
                        %>
                        <option id="tipoDocumento" name="txtTipDoc" value="<%= user.getDocid() %>" selected=""><%=user.getTipoDocum().getNom()%></option>
                        <% }else{ %>
                            <option value="<%= docTipo.getId() %>"><%= docTipo.getNom() %></option>
                        <% }} %>
                    </select>
                  </div>
                  <div class="registro__input">
                    <label for="tipoSangre">Tipo de Sangre:</label>
                    <select class="input_registro" name="txtTipSang" id="tipSan" required="">
                        
                        <% 
                        TipoSangreDAO sangreDao = new TipoSangreDAO();
                        List<TipoSangre> sangreLista = sangreDao.listar();
                        Iterator<TipoSangre> sangreIter = sangreLista.iterator();
                        while (sangreIter.hasNext()) {
                            TipoSangre sangreTipo = sangreIter.next();
                            if((sangreTipo.getId()) == (user.getSanid())){
                            
                        %>
                        <option  name="txtTipSang" value="<%= user.getSanid() %>" selected=""><%=user.getTipoSangre().getNom()%></option>
                        <% }else{%>
                        <option value="<%= sangreTipo.getId() %>"><%= sangreTipo.getNom() %></option>
                        <% }} %>
                    </select>
                  </div>
                  
                  <div class="registro__input">
                      <label for="telefono">Teléfono:</label>
                      <input class="input_registro" type="text" id="telefono" name="txtTel" value="<%=user.getTelefono()%>" required="">
                  </div>
                  <div class="registro__input">
                      <label for="email">Correo:</label>
                      <input class="input_registro" type="email" id="correo" name="txtCorreo" value="<%=user.getEmail()%>" required="">
                  </div>
                  <div class="registro__input">
                      <label for="fechaContrato">Fecha inicio de Contrato:</label>
                      <input class="input_registro" type="date" id="fechaContrato" name="fechaContrato" value="<%=user.getFechaContra()%>" readonly>
                  </div>
                  <div class="registro__input">
                      <label for="rol">Rol:</label>
                      <select class="input_registro" name="txtRol" id="" >
                        
                        <% 
                        RolesDAO Dao = new RolesDAO();
                        List<Roles> Lista = Dao.listar();
                        Iterator<Roles> rolIter = Lista.iterator();
                        while (rolIter.hasNext()) {
                            Roles rolP = rolIter.next();
                            if((rolP.getId()) == (user.getRol())){
                        %>
                        <option id="" name="txtRol" value="<%= user.getRol()%>" selected=""><%= user.getNomRol()%></option>
                        <% }else{ %>
                            <option value="<%= rolP.getId() %>"><%= rolP.getNom() %></option>
                        <% }} %>
                    </select>
                  </div>
                    <div class="registro__input input--olvidaste">
                      <a class="boton boton--olvidaste" href="ControlContrasena?accion=editar&id=<%= user.getIdPerfil()%>"">Actualizar contraseña</a>
                  </div>
                <div>
                    
                    <button id="enviar" class="boton boton__registro" type="submit" name="accion" value="Actualizar">Actualizar</button>
                    
                </div>
              </form>
          </div>
      </section>
    </main>
</body>
</html>
