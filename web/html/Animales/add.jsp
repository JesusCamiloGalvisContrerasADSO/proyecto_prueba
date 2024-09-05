<%@page import="modeloDAO.SaludDAO"%>
<%@page import="modelo.Salud"%>
<%@page import="modelo.Salud"%>
<%@page import="modelo.Tipo_sexo"%>
<%@page import="modeloDAO.Tipo_sexoDAO"%>
<%@page import="java.util.Iterator"%>
<%@page import="modelo.Raza"%>
<%@page import="java.util.List"%>
<%@page import="modeloDAO.RazaDAO"%>

<%@ include file="../../componentes/validacionRol.jsp" %>


<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Agregar Animal</title>
    <link rel="stylesheet" href="css/style.css"> <!-- Incluye tu archivo de estilos CSS -->
</head>
<body>
    <h2>Agregar Nuevo Animal</h2>
    
    <form action="ControlAnimal" method="POST">
    <%
        int idLote = Integer.parseInt(request.getParameter("id"));
        request.setAttribute("idLote", idLote);
        
        int numLote = Integer.parseInt(request.getParameter("num"));
        request.setAttribute("numLote", numLote);
    %>
        <!-- El atributo "name" debe coincidir con los parámetros esperados por el servlet -->
        <input type="hidden" name="action" value="add"> <!-- Acción específica que manejará el servlet -->

        <label for="lote_id">Id lote</label>
        <input type="number" name="txtid" value="<%= idLote %>"><br><br>
        
        <label for="lote_id">numro lote</label>
        <input type="number" name="txtnumLote" value="<%= numLote %>"><br><br>
        
        <label for="num">Número del Animal:</label>
        <input type="text" id="num" name="txtnum" required><br><br>
        
        <div class="registro__input">
                    <p>Tipo de Raza</p>
                    <select class="input_registro" name="txtRaza" id="">
                        <option value="" disabled="" selected=""></option>
                        <% 
                        RazaDAO razaDAO = new RazaDAO();
                        List<Raza> razaLista = razaDAO.listar();
                        Iterator<Raza> razaIter = razaLista.iterator();
                        while (razaIter.hasNext()) {
                            Raza razaTipo = razaIter.next();
                        %>
                        <option value="<%= razaTipo.getId() %>"><%= razaTipo.getNombre()%></option>
                        <% } %>
                    </select>
                  </div><br><br>
                  
        <div class="registro__input">
                    <p>Tipo de sexo</p>
                    <select class="input_registro" name="txtSexo" id="">
                        <option value="" disabled="" selected=""></option>
                        <% 
                        Tipo_sexoDAO sexoDao = new Tipo_sexoDAO();
                        List<Tipo_sexo> sexoLista = sexoDao.listar();
                        Iterator<Tipo_sexo> sexoIter = sexoLista.iterator();
                        while (sexoIter.hasNext()) {
                            Tipo_sexo sexoTipo =  sexoIter.next();
                        %>
                        <option value="<%= sexoTipo.getId() %>"><%= sexoTipo.getNombre()%></option>
                        <% } %>
                    </select>
                  </div><br><br>
        
        <div class="registro__input">
                    <p>Estado de salud</p>
                    <select class="input_registro" name="txtsalud" id="">
                        <option value=""></option>
                        <% 
                        SaludDAO saludDao = new SaludDAO();
                        List<Salud> saludLista = saludDao.listar();
                        Iterator<Salud> saludIter = saludLista.iterator();
                        while (saludIter.hasNext()) {
                            Salud salud = saludIter.next();
                        %>
                        <option value="<%= salud.getId() %>"><%= salud.getNombre()%></option>
                        <% } %>
                    </select>
                  </div>

        <!-- Peso inicial del animal -->
        <label for="peso">Peso Inicial (kg):</label>
        <input type="number" id="peso" name="txtpeso" step="0.01" required><br><br>
        
        <!-- Botón para enviar el formulario -->
        <button type="submit" type="submit" name="accion" value="Agregar">Agregar Animal</button>
    </form>

    <!-- Enlace para regresar a la lista de animales -->
    <br><a href="AnimalServlet?action=list&">Regresar a la Lista de Animales</a> <!-- Ajusta la URL según sea necesario -->
</body>
</html>
