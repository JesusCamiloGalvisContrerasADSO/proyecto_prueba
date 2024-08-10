<%@ page import="java.util.*" %>
<%@ page import="modelo.TipoDocum" %>
<%@ page import="modeloDAO.TipoDocDAO" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Editar Tipo de Sangre</title>
    </head>
    <body>
        <% 
            TipoDocDAO dao = new TipoDocDAO();
            int id = Integer.parseInt((String) request.getAttribute("idTipo"));
            TipoDocum Tip = dao.list(id);
        %>
        <h1>Editar Tipo de documento</h1>
        
        <form action="ControlTipoDoc" method="GET">
            Nombres:<br>
            <input type="text" name="txtNom" value="<%= Tip.getNom() %>"><br>
            <input type="hidden" name="txtid" value="<%= Tip.getId() %>">
            <input type="submit" name="accion" value="Actualizar"><br>
        </form>
    </body>
</html>
