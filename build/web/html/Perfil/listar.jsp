<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="modelo.Perfil_usuario" %>
<%@ page import="modeloDAO.PerfilusuarioDAO" %>
<%@ page import="controlador.controladorPerfil" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Lista de Perfiles</title>
</head>
<body>
    <div>
        <a href="../../proyecto/paginasAcciones.html">Volver a Acciones</a>
        <h1>Perfiles</h1>
        <a href="controladorPerfil?accion=add">Agregar Nuevo</a>
        <table border="1">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Nombre</th>
                    <th>Apellido</th>
                    <th>Telefono</th>
                    <th>Email</th>
                    <th>Fecha de contrato</th>
                    <th>Tipo de documento</th>
                    <th>Tipo de sangre</th>
                    <th>Acciones</th>
                </tr>
            </thead>
            <tbody>
                <% 
                    PerfilusuarioDAO dao = new PerfilusuarioDAO();
                    List<Perfil_usuario> lista = dao.listar();
                    if (lista != null && !lista.isEmpty()) {
                        for (Perfil_usuario Tipo : lista) {
                %>
                <tr>
                    <td><%= Tipo.getId() %></td>
                    <td><%= Tipo.getNombre() %></td>
                    <td><%= Tipo.getApellido() %></td>
                    <td><%= Tipo.getTelefono() %></td>
                    <td><%= Tipo.getEmail() %></td>
                    <td><%= Tipo.getFechaContrato() %></td>
                    <td><%= Tipo.getTipoDocId() %></td>
                    <td><%= Tipo.getSangreId() %></td>
                    <td>
                        <a href="controladorPerfil?accion=editar&id=<%= Tipo.getId() %>">Editar</a>
                        <a href="controladorPerfil?accion=eliminar&id=<%= Tipo.getId() %>">Eliminar</a>
                    </td>
                </tr>
                <% 
                        } 
                    } else { 
                %>
                <tr>
                    <td colspan="9">No hay perfiles para mostrar.</td>
                </tr>
                <% 
                    } 
                %>
            </tbody>
        </table>
    </div>
</body>
</html>
