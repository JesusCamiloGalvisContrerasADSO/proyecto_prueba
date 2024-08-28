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
<head>
    <meta charset="UTF-8">
    <title>Editar Usuario</title>
    <link rel="stylesheet" href="">
</head>
<body>
    <h2>Editar Usuario</h2>
    
    <% 
       UsuarioDAO dao = new UsuarioDAO();
       int id = Integer.parseInt((String) request.getAttribute("idTipo"));
       Usuario user = dao.list(id);
     %>
    
    <form action="ControlUsuario" method="POST">
        <input type="hidden" name="txtid" value="<%= user.getIdUsuario()%>">

        <label for="documento">Documento:</label>
        <input type="number" id="documento" name="txtNumDoc" value="<%=user.getDocumento()%>" ><br>

        <label for="contrasena">Contraseña:</label>
        <input type="password" id="contrasena" name="txtContra" value="<%=user.getContrasena()%>"><br>

        <label for="nombre">Nombre:</label>
        <input type="text" id="nombre" name="txtNom" value="<%=user.getNombre()%>"><br>

        <label for="apellido">Apellido:</label>
        <input type="text" id="apellido" name="txtApell" value="<%=user.getApellido()%>"><br>

        <label for="telefono">Teléfono:</label>
        <input type="text" id="telefono" name="txtTel" value="<%=user.getTelefono()%>"><br>

        <label for="email">Email:</label>
        <input type="email" id="email" name="txtCorreo" value="<%=user.getEmail()%>"><br>

        <label for="fechaContrato">Fecha inicio de Contrato:</label>
        <input type="text" id="fechaContrato" name="fechaContrato" value="<%=user.getFechaContra()%>" readonly><br>

        <label for="rol">Rol:</label>
        <select class="input_registro" name="txtRol" id="">
                        <option id="rol" name="txtRol" value="<%= user.getRol()%>" selected=""><%= user.getNomRol()%></option>
                        <% 
                        RolesDAO Dao = new RolesDAO();
                        List<Roles> Lista = Dao.listar();
                        Iterator<Roles> rolIter = Lista.iterator();
                        while (rolIter.hasNext()) {
                            Roles rol = rolIter.next();
                        %>
                            <option value="<%= rol.getId() %>"><%= rol.getNom() %></option>
                        <% } %>
        </select><br>

        <label for="tipoDocumento">Tipo de Documento:</label>
        <select class="input_registro" name="txtTipDoc" id="">
                        <option id="tipoDocumento" name="txtTipDoc" value="<%= user.getDocid() %>" selected=""><%=user.getTipoDocum().getNom()%></option>
                        <% 
                        TipoDocDAO docDao = new TipoDocDAO();
                        List<TipoDocum> docLista = docDao.listar();
                        Iterator<TipoDocum> docIter = docLista.iterator();
                        while (docIter.hasNext()) {
                            TipoDocum docTipo = docIter.next();
                        %>
                            <option value="<%= docTipo.getId() %>"><%= docTipo.getNom() %></option>
                        <% } %>
                    </select><br>
                    
        <label for="tipoSangre">Tipo de Sangre:</label>
        <select class="input_registro" name="txtTipSang" id="">
                        <option  name="txtTipSang" value="<%= user.getSanid() %>" selected=""><%=user.getTipoSangre().getNom()%></option>
                        <% 
                        TipoSangreDAO sangreDao = new TipoSangreDAO();
                        List<TipoSangre> sangreLista = sangreDao.listar();
                        Iterator<TipoSangre> sangreIter = sangreLista.iterator();
                        while (sangreIter.hasNext()) {
                            TipoSangre sangreTipo = sangreIter.next();
                        %>
                        <option value="<%= sangreTipo.getId() %>"><%= sangreTipo.getNom() %></option>
                        <% } %>
                    </select><br>
                    
                    <button type="submit" name="accion" value="Actualizar">Guardar</button>
        <!--<input type="submit" name="accion" value="Actualizar"><br>-->
    </form>
</body>
</html>
