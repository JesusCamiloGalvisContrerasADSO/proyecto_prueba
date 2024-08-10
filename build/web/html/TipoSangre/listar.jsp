<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="modelo.TipoSangre" %>
<%@ page import="modeloDAO.TipoSangreDAO" %>
<%@ page import="controlador.controladorTipSan" %>
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
                <a href="../../proyecto/paginasAcciones.html">Volver a Acciones</a>
                <h1>Tipo de Sangre</h1>
            </div>
            
            <a href="controladorTipSan?accion=add">Agregar Nuevo</a>
            <table border="1">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Nombre</th>
                        <th>Acciones</th>
                    </tr>
                </thead>
                <% 
                    TipoSangreDAO dao = new TipoSangreDAO();
                    List<TipoSangre> lista = dao.listar();
                    Iterator<TipoSangre> iter = lista.iterator();
                    TipoSangre Tipo = null;
                    while (iter.hasNext()) {
                    Tipo = iter.next();
                %>
                <tbody>
                    <tr>
                        <td><%= Tipo.getId() %></td>
                        <td><%= Tipo.getNom() %></td>
                        <td>
                            <a href="controladorTipSan?accion=editar&id=<%= Tipo.getId() %>">Editar</a>
                            <a href="controladorTipSan?accion=eliminar&id=<%= Tipo.getId() %>">Eliminar</a>
                        </td>
                    </tr>
                <% } %>
                </tbody>
            </table>
        </div>
    </body>
</html>
