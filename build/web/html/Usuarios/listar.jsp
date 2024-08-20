<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="modelo.Usuario" %>
<%@ page import="modeloDAO.UsuarioDAO" %>
<%@ page import="controlador.ControlUsuario" %>

<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Lista de Usuarios</title>
</head>
<body>
    <div>
        <div>
            <a href="ControlAcciones?accion=volver">Volver</a>
            <h1>Usuarios registrados</h1>
        </div>
        
        <a href="ControlUsuario?accion=add">Agregar Nuevo</a>
        <table border="1">
            <thead>
                <tr>
                    <th>ID Usuario</th>
                    <th>Nombre</th>
                    <th>Apellido</th>
                    <th>Teléfono</th>
                    <th>Correo Electrónico</th>
                    <th>Fecha de Contrato</th>
                    <th>Tipo de Documento</th>
                    <th>Tipo de Sangre</th>
                    <th>Número de Documento</th>
                    <th>Acciones</th>
                </tr>
            </thead>
            <tbody>
                <% 
                    UsuarioDAO dao = new UsuarioDAO();
                    List<Usuario> lista = dao.listar();
                    Iterator<Usuario> iter = lista.iterator();
                    Usuario Tipo = null;
                    while (iter.hasNext()) {
                    Tipo = iter.next();
                %>
                <tr>
                    <td><%= Tipo.getIdUsuario() %></td>
                    <td><%= Tipo.getNombre() %></td>
                    <td><%= Tipo.getApellido() %></td>
                    <td><%= Tipo.getTelefono() %></td>
                    <td><%= Tipo.getEmail() %></td>
                    <td><%= Tipo.getFechaContra() %></td>
                    <td><%= Tipo.getTipoDocum().getNom() %></td> <!-- Mostrar nombre del tipo de documento -->
                    <td><%= Tipo.getTipoSangre().getNom() %></td> <!-- Mostrar nombre del tipo de sangre -->
                    <td><%= Tipo.getDocumento() %></td>
                    <td>
                        <a href="ControlUsuario?accion=editar&id=<%= Tipo.getIdUsuario() %>">Editar</a>
                        <a href="ControlUsuario?accion=eliminar&id=<%= Tipo.getIdUsuario() %>">Eliminar</a>
                    </td>
                </tr>

                <% } %>
            </tbody>
        </table>
    </div>
</body>
</html>
