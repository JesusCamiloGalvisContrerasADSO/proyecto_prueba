<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="modelo.TipoDocum" %>
<%@ page import="modeloDAO.TipoDocDAO" %>
<%@ page import="controlador.ControlTipoDoc" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div>
            <div>
                <a href="ControlAcciones?accion=volver">Volver</a>
                <h1>Tipos de documentos</h1>
            </div>
            <a href="ControlTipoDoc?accion=add">Agregar Nuevo</a>
            <table border="1">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Nombre</th>
                        <th>Acciones</th>
                    </tr>
                </thead>
                <% 
                    TipoDocDAO dao = new TipoDocDAO();
                    List<TipoDocum> lista = dao.listar();
                    Iterator<TipoDocum> iter = lista.iterator();
                    TipoDocum Tipo = null;
                    while (iter.hasNext()) {
                    Tipo = iter.next();
                %>
                <tbody>
                    <tr>
                        <td><%= Tipo.getId() %></td>
                        <td><%= Tipo.getNom() %></td>
                        <td>
                            <a href="ControlTipoDoc?accion=editar&id=<%= Tipo.getId() %>">Editar</a>
                            <a href="ControlTipoDoc?accion=eliminar&id=<%= Tipo.getId() %>">Eliminar</a>
                        </td>
                    </tr>
                <% } %>
                </tbody>
            </table>
        </div>
    </body>
</html>
