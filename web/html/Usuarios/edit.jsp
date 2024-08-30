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
<!DOCTYPE html>
<html lang="es">

<%@ include file="../../componentes/head.jsp" %>

<body>
    <!-- este es el encabezado de la pagina -->
    <header class=" fondo_header sombras--contenedor">
        <div class="container encabezado">
            
          <div class="encabezado">
              <a href="ControlUsuario?accion=listar">
                <button class="boton_salir"><i class="bi bi-chevron-left"></i></button>
              </a>
              <img class="logo" src="Recursos/logo-BoviControl.png" alt="">
                <p>BoviControl</p>
            </div>
            <div class="encabezado">
                <ul class="encabezado__lista">
                    <li><a class="encabezado__lista--texto" href="../../Index.html">Ingresar</a></li>
                    <li class="encabezado__lista--icono"><i class="bi bi-person-circle"></i></li>
                </ul>
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
              <form action="ControlUsuario" method="POST">
                  
                <input type="hidden" name="txtid" value="<%= user.getIdUsuario()%>">
                  <div class="registro__input">
                      <label for="nombre">Nombre:</label>
                      <input class="input_registro" type="text" id="nombre" name="txtNom" value="<%=user.getNombre()%>">
                  </div>
                  <div class="registro__input">
                      <label for="apellido">Apellido:</label>
                      <input class="input_registro" type="text" id="apellido" name="txtApell" value="<%=user.getApellido()%>">
                  </div>
                  <div class="registro__input">
                      <label for="documento">Documento:</label>
                      <input class="input_registro" type="number" id="documento" name="txtNumDoc" value="<%=user.getDocumento()%>" >
                  </div>
                  <div class="registro__input">
                    <label for="tipoDocumento">Tipo de Documento:</label>
                    <select class="input_registro" name="txtTipDoc" id="">
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
                    <select class="input_registro" name="txtTipSang" id="">
                        
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
                      <input class="input_registro" type="text" id="telefono" name="txtTel" value="<%=user.getTelefono()%>">
                  </div>
                  <div class="registro__input">
                      <label for="contrasena">Contraseña:</label>
                      <input class="input_registro" type="password" id="contrasena" name="txtContra" value="<%=user.getContrasena()%>">
                  </div>
                  <div class="registro__input">
                      <label for="email">Email:</label>
                      <input class="input_registro" type="email" id="email" name="txtCorreo" value="<%=user.getEmail()%>">
                  </div>
                  <div class="registro__input">
                      <label for="fechaContrato">Fecha inicio de Contrato:</label>
                      <input class="input_registro" type="date" id="fechaContrato" name="fechaContrato" value="<%=user.getFechaContra()%>" readonly>
                  </div>
                  <div class="registro__input">
                      <label for="rol">Rol:</label>
                    <select class="input_registro" name="txtRol" id="">
                        
                        <% 
                        RolesDAO Dao = new RolesDAO();
                        List<Roles> Lista = Dao.listar();
                        Iterator<Roles> rolIter = Lista.iterator();
                        while (rolIter.hasNext()) {
                            Roles rol = rolIter.next();
                            if((rol.getId()) == (user.getRol())){
                        %>
                        <option id="rol" name="txtRol" value="<%= user.getRol()%>" selected=""><%= user.getNomRol()%></option>
                        <% }else{ %>
                            <option value="<%= rol.getId() %>"><%= rol.getNom() %></option>
                        <% }} %>
                    </select>
                  </div>
                <div>
                    
                    <button class="boton boton__registro" type="submit" name="accion" value="Actualizar">Actualizar</button>
                    
                </div>
              </form>
          </div>
      </section>
    </main>
</body>
</html>
