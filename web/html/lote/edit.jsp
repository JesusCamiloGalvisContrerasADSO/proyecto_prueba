<%@ page import="modelo.LoteM" %>
<%@ page import="modeloDAO.LoteDAO" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Editar Lote</title>
</head>
<body>
    <% 
        LoteDAO dao = new LoteDAO();
        int id = Integer.parseInt((String) request.getAttribute("idLote"));
        LoteM lote = dao.list(id);
        
    %>
    <h1>Editar Lote</h1>
    
    <form action="controlLote" method="POST">
        Número:<br>
        <input type="text" name="txtNum" value="<%=lote.getNum()%>"><br>
        <input type="submit" name="accion" value="Actualizar"><br>
    </form>
</body>
</html>
