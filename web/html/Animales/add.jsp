<%
    request.setAttribute("pageTitle", "Editar perfil");
%>
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
            
    <section class="center registro__fondo">
        <div class="container center registro sombras--contenedor">
            
            <h1 class="titulo--caja" >Agregar Nuevo Animal</h1>

            <form action="ControlAnimal" method="POST">
            <%@ include file="../../componentes/capturarLote_num.jsp" %>
                <!-- El atributo "name" debe coincidir con los parámetros esperados por el servlet -->
                <input type="hidden" name="action" value="add"> <!-- Acción específica que manejará el servlet -->
                <input hidden="" type="number" name="txtid" value="<%= idLote %>">
                <input hidden="" type="number" name="txtnumLote" value="<%= numLote %>">

                <div class="registro__input">
                      <label for="num">Número del Animal:</label>
                <input class="input_registro" type="text" id="num" name="txtnum" required>
                  </div>
                

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
                          </div>

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
                          </div>

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
                
                <div class="registro__input">
                      <!-- Peso inicial del animal -->
                <label for="peso">Peso Inicial (kg):</label>
                <input class="input_registro" type="number" id="peso" name="txtpeso" step="0.01" required>
                </div>
                <!-- Botón para enviar el formulario -->
                <button class="boton boton__registro" type="submit" type="submit" name="accion" value="Agregar">Agregar Animal</button>
            </form>
        </div> 
    </section>
</body>
</html>
