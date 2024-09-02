<%
    request.setAttribute("pageTitle", "Papelera lotes");
%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="modelo.LoteM" %>
<%@ page import="modeloDAO.LoteDAO" %>
<%@ page import="controlador.ControlPapeLote" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <%@ include file="../../../componentes/head.jsp" %>
    <body>
        <header class=" fondo_header">
            <div class="container encabezado">

              <div class="encabezado">
                <%@ include file="../../../componentes/btn_salir.jsp" %>
                <img class="logo" src="Recursos/logo-BoviControl.png" alt="">
                <p>BoviControl</p>
              </div>
              <div class="encabezado">
                <ul class="encabezado__lista">
                  <li><a class="encabezado__lista--texto" href="controlLote?accion=listar">Lotes</a></li>
                  <li><a class="encabezado__lista--texto" href="">Usuario</a></li>

                  <li class="encabezado__lista--icono"><i class="bi bi-person-circle"></i></li>
                </ul>
              </div>
            </div>
        </header>
        
        <main>
             <!-- aquí determina qué es lo que se está viendo -->
            <section class="fondo__cinta">

                <div class="cinta__opciones container">
                    <div class="cinta__opciones--titulo">
                        <div>
                            <img class="cinta__logo" src="Recursos/vaquita.png" alt="">
                        </div>
                        <div>
                            <p class="cinta__Titulo">Lotes eliminados / vendidos</p>
                        </div>
                    </div>
                    <div class="menu">
                        <ul class="opciones__botones">
                            <li>
                                <!-- Mueve el formulario aquí y coloca el botón eliminar dentro del mismo -->
                                <form action="ControlPapeLote" method="POST">
                                    <button class="  opciones__botones--boton" type="submit" name="accion" value="eliminar">
                                        <i class="bi bi-trash"></i>
                                    </button>
                            </li>
                            
                        </ul>
                    </div>
                </div>
                <hr class="linea__cinta">
            </section>
             
             <section>
                <div class="Tipo__vista container">
                    <div class="Tipo__vista--punto"></div>
                    <p class="Tipo__vista--texto">Número de lote</p>
                </div>
            </section>

             <section>
                <div>
                    <!-- Formulario que contiene los checkboxes -->
                    <% 
                        LoteDAO dao = new LoteDAO();
                        List<LoteM> listarPapelera = dao.listarPapelera();
                        Iterator<LoteM> iter = listarPapelera.iterator();
                        LoteM Tipo = null;
                        while (iter.hasNext()) {
                        Tipo = iter.next();
                    %>
                    <hr class="linea__datos">
                    <div class="">
                        <div class="container Datos__lote" >
                            <div class="Datos__lote--numero">
                                <div class="num__lote">
                                    <input class="checkbox" type="checkbox" name="selectedLotes" value="<%= Tipo.getId() %>">
                                    <a class="boton boton__lote lote--ventas" href="ControlAnimal?accion=listar&id=<%= Tipo.getId() %>&num=<%= Tipo.getNum() %>">Lote <%= Tipo.getNum() %></a>
                                </div>
                                <div class="Datos__lote--texto">
                                    <p class="Datos__lote--cantidad">Cantidad: <%= Tipo.getCantidad()%></p>
                                </div>
                            </div>
                            <div>
                                <p><a href="ControlPapeLote?accion=cambiarVerdad&id=<%= Tipo.getId() %>">Restaurar</a></p>
                                <!--<p class="Datos__lote--texto Datos__lote--texto2">Última fecha de revisión: 12/05/2024</p>-->
                            </div>
                        </div>
                    </div>
                    <% } %>
                    <hr class="linea__datos">
                    </form> <!-- Cierra el formulario después del último checkbox -->
                </div>
            </section>
        </main>