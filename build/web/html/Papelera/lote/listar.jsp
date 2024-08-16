<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="modelo.LoteM" %>
<%@ page import="modeloDAO.LoteDAO" %>
<%@ page import="controlador.ControlPapeLote" %>
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
                <h1>Lotes</h1>
            </div>
            <table border="1">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Numero lote</th>
                        <th>Acciones</th>
                    </tr>
                </thead>
                <% 
                    LoteDAO dao = new LoteDAO();
                    List<LoteM> listarPapelera = dao.listarPapelera();
                    Iterator<LoteM> iter = listarPapelera.iterator();
                    LoteM Tipo = null;
                    while (iter.hasNext()) {
                    Tipo = iter.next();
                %>
                <tbody>
                    <tr>
                        <td><%= Tipo.getId() %></td>
                        <td><%= Tipo.getNum() %></td>
                        <td>
                            <a href="ControlPapeLote?accion=cambiarVerdad&id=<%= Tipo.getId() %>">Restaurar</a>
                            <a href="ControlPapeLote?accion=eliminar&id=<%= Tipo.getId() %>">Eliminar</a>
                        </td>
                    </tr>
                <% } %>
                </tbody>
            </table>
        </div>
    </body>
</html>
