<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="modelo.LoteM" %>
<%@ page import="modeloDAO.LoteDAO" %>
<%@ page import="controlador.controlLote" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>

<%@ include file="../../componentes/validacionRol.jsp" %>

<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Lotes</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
        <link rel="stylesheet" href="css/style.css">
    </head>
    <body>
        
        <header class=" fondo_header">
            <div class="container encabezado">
              <div class="encabezado">
                  <%@ include file="../../componentes/btn_salir.jsp" %>
                <img class="logo" src="Recursos/logo-BoviControl.png" alt="">
                <p>BoviControl</p>
              </div>
              <div class="encabezado">
                <ul class="encabezado__lista">
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
                            <p class="cinta__Titulo">Lotes de ganado</p>
                        </div>
                    </div>
                    <%
                        if (rol == 1) {
                    %>
                    <div class="menu">
                        <ul class="opciones__botones">
                            <li>
                                <a href="controlLote?accion=add"><button class="opciones__botones--boton" ><i class="bi bi-plus"></i></button></a>
                            </li>
                            <li>
                                <a href="ControlPapeLote?accion=cambiarVerdad" >
                                <button class="opciones__botones--boton"><i class="bi bi-card-checklist"></i></button>
                                </a>
                            </li>
                            <li>
                                <!-- Mueve el formulario aquí y coloca el botón eliminar dentro del mismo -->
                                <form action="controlLote" method="POST">
                                    <button class=" opciones__botones--boton" type="submit" name="accion" value="cambiarFalse">
                                        <i class="bi bi-trash"></i>
                                    </button>
                                
                            </li>
                            
                        </ul>
                    </div>
                    <%
                        }
                    %>
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
                        List<LoteM> lista = dao.listar();
                        Iterator<LoteM> iter = lista.iterator();
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
                                    <a class="boton boton__lote" href="ControlAnimal?accion=listar&id=<%= Tipo.getId() %>&num=<%= Tipo.getNum() %>">Lote <%= Tipo.getNum() %></a>
                                </div>
                                <div class="Datos__lote--texto">
                                    <p class="Datos__lote--cantidad">Cantidad: <%= Tipo.getCantidad()%></p>
                                </div>
                            </div>
                                <%
                                    if (rol == 1) {
                                %>
                            <div>
                                <p><a class=" boton boton--editar" href="controlLote?accion=editar&id=<%= Tipo.getId() %>"><i class="bi bi-pencil"></i></a></p>
                                <!--<p class="Datos__lote--texto Datos__lote--texto2">Última fecha de revisión: 12/05/2024</p>-->
                            </div>
                                <%}%>
                        </div>
                    </div>
                    <% } %>
                    <hr class="linea__datos"> 
                </div>
                    
            </section>
        </main>
    </body>
</html>
