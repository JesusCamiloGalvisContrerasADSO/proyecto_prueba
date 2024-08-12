<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="modelo.TipoSangre" %>
<%@ page import="modeloDAO.TipoSangreDAO" %>
<%@ page import="controlador.ControlTipSan" %>
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
                <h1>Tipo de Sangre</h1>
            </div>
            
            <a href="ControlTipSan?accion=add">Agregar Nuevo</a>
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
                            <a href="ControlTipSan?accion=editar&id=<%= Tipo.getId() %>">Editar</a>
                            <a href="ControlTipSan?accion=eliminar&id=<%= Tipo.getId() %>">Eliminar</a>
                        </td>
                    </tr>
                <% } %>
                </tbody>
            </table>
        </div>
    </body>
</html>
