<%@ page import="java.util.*" %>
<%@ page import="modelo.TipoSangre" %>
<%@ page import="modeloDAO.TipoSangreDAO" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Editar Tipo de Sangre</title>
    </head>
    <body>
        <% 
            TipoSangreDAO dao = new TipoSangreDAO();
            int id = Integer.parseInt((String) request.getAttribute("idTipo"));
            TipoSangre Tip = dao.list(id);
        %>
        <h1>Editar Tipo de Sangre</h1>
        
        <form action="controladorTipSan" method="GET">
            Nombres:<br>
            <input type="text" name="txtNom" value="<%= Tip.getNom() %>"><br>
            <input type="hidden" name="txtid" value="<%= Tip.getId() %>">
            <input type="submit" name="accion" value="Actualizar"><br>
        </form>
    </body>
</html>
