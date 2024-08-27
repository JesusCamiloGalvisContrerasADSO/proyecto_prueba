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
    
    <form action="ControlUsuario" method="GET">
        <input type="hidden" name="accion" value="actualizar">
        <input type="hidden" name="id" value="<%= user.getIdUsuario()%>">

        <label for="documento">Documento:</label>
        <input type="number" id="documento" name="documento" value="<%=user.getDocumento()%>" readonly><br>

        <label for="contrasena">Contraseña:</label>
        <input type="password" id="contrasena" name="contrasena" value="<%=user.getContrasena()%>"><br>

        <label for="nombre">Nombre:</label>
        <input type="text" id="nombre" name="nombre" value="<%=user.getNombre()%>"><br>

        <label for="apellido">Apellido:</label>
        <input type="text" id="apellido" name="apellido" value="<%=user.getApellido()%>"><br>

        <label for="telefono">Teléfono:</label>
        <input type="text" id="telefono" name="telefono" value="<%=user.getTelefono()%>"><br>

        <label for="email">Email:</label>
        <input type="email" id="email" name="email" value="<%=user.getEmail()%>"><br>

        <label for="fechaContrato">Fecha inicio de Contrato:</label>
        <input type="text" id="fechaContrato" name="fechaContrato" value="<%=user.getFechaContra()%>" readonly><br>

        <label for="rol">Rol:</label>
        <input type="text" id="rol" name="rol" value="<%=user.getNomRol()%>" readonly><br>

        <label for="tipoDocumento">Tipo de Documento:</label>
        <select class="input_registro" name="txtTipDoc" id="">
                        <option id="tipoDocumento" name="tipoDocumento" value="<%= user.getSanid() %>" disabled="" selected=""><%=user.getTipoDocum().getNom()%></option>
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
                    
        <label for="tipoSangre">Tipo de Sangre:</label>
        <select class="input_registro" name="txtTipSang" id="">
                        <option value="<%= user.getSanid() %>" disabled="" selected=""><%=user.getTipoSangre().getNom()%></option>
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
                    
        <input type="submit" value="Actualizar">
    </form>
</body>
</html>
