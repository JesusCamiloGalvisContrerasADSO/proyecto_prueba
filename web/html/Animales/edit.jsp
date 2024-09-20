<%@page import="modelo.Salud"%>
<%@page import="modeloDAO.SaludDAO"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<%@page import="modelo.Animal"%>
<%@page import="modeloDAO.AnimalDAO"%>
<%
    request.setAttribute("pageTitle", "Editar animal");
%>
<%@ page import="modelo.LoteM" %>
<%@ page import="modeloDAO.LoteDAO" %>

<%@ include file="../../componentes/validacionRol.jsp" %>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <%@ include file="../../componentes/head.jsp" %>
    <body>
        <header class=" fondo_header">
            <div class="container encabezado">

              <div class="encabezado">
                 <%@ include file="../../componentes/btn_salir.jsp" %>
                <img class="logo" src="Recursos/logo-BoviControl.png" alt="">
                <p>BoviControl</p>
              </div>
              <%@ include file="../../componentes/botones_header.jsp" %>
            </div>
        </header>
        
                <% 
                    AnimalDAO dao = new AnimalDAO();
                    int id = Integer.parseInt((String) request.getAttribute("idAnimal"));
                    Animal anim = dao.list(id);
                %>
        <main>
            <section class="fondo__cinta">

                <div class="cinta__opciones container">
                    <div class="cinta__opciones--titulo">
                        <div>
                            <img class="cinta__logo" src="Recursos/vaquita.png" alt="">
                        </div>
                        <div>
                            <p class="cinta__Titulo">Animal <%= anim.getNum() %>  </p>
                        </div>
                    </div>
                </div>
                <hr class="linea__cinta">
            </section>
            
            <section class="center registro__fondo">
                <div class="container center registro sombras--contenedor">
                    <form id="registro" action="ControlAnimal" method="POST" novalidate="">
                        <input type="hidden" name="txtid" value="<%= anim.getId()%>">
                        <input type="hidden" name="txtidLote" value="<%= anim.getLote_id() %>">
                        <input type="hidden" name="txtnumLote" value="<%= anim.getLote().getNum()%>">
                            
                            <div class="registro__input">
                                <label for="tipoDocumento">Lote:</label>
                                <select class="input_registro " name="txtLote" id="Raza">
                                    <% 
                                    LoteDAO lot = new LoteDAO();
                                    List<LoteM> lotLista = lot.listar();
                                    Iterator<LoteM> lotIter = lotLista.iterator();
                                    while (lotIter.hasNext()) {
                                        LoteM lote = lotIter.next();
                                        if((lote.getId()) == (anim.getLote_id())){
                                    %>
                                    <option value="<%= lote.getId()%>" selected=""><%= lote.getNum() %></option>
                                    <% }else{ %>
                                        <option value="<%= lote.getId()%>"><%= lote.getNum() %></option>
                                    <% }} %>
                                </select>
                            </div>
                                
                            <div class="registro__input">
                                <p>Estado de salud</p>
                                <select class="input_registro" name="txtsalud" id="salud">
                                    <% 
                                    SaludDAO saludDao = new SaludDAO();
                                    List<Salud> saludLista = saludDao.listar();
                                    Iterator<Salud> saludIter = saludLista.iterator();
                                    while (saludIter.hasNext()) {
                                        Salud salud = saludIter.next();
                                        if((salud.getId()) == (anim.getSalud_id())){
                                    %>
                                    <option value="<%= salud.getId()%>" selected=""><%= salud.getNombre()%></option>
                                    <% }else{ %>
                                        <option value="<%= salud.getId()%>"><%= salud.getNombre() %></option>
                                    <% }} %>
                                </select>
                            </div>
                                
                                <button id="enviar" class="boton boton__registro" type="submit" name="accion" value="Actualizar">Actualizar</button>
                    </form>
                </div>
            </section>
        </main>
</body>
</html>
