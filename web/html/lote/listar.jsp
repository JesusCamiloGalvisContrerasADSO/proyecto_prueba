<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="modelo.LoteM" %>
<%@ page import="modeloDAO.LoteDAO" %>
<%@ page import="controlador.controlLote" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>

<%@ include file="../../componentes/validacionRol.jsp" %>

<!DOCTYPE html>
<html lang="es">
    <%@ include file="../../componentes/head.jsp" %>
    <body>
        
        <header class=" fondo_header">
            <div class="container encabezado">
              <div class="encabezado">
                  <%
                        if (rol == 1) {
                    %>
                  <a href="ControlLogin?accion=acciones">
                    <button class="boton_salir"><i class="bi bi-chevron-left"></i></button>
                   </a>
                  <% } %>
                <img class="logo" src="Recursos/logo-BoviControl.png" alt="">
                <p>BoviControl</p>
              </div>
                  <%@ include file="/componentes/botones_header.jsp" %>
            </div>
        </header>
       <main>
        <form action="controlLote" method="POST">
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
                                <a href="controlLote?accion=add"><div class="opciones__botones--boton" ><i class="bi bi-plus"></i></div></a>
                            </li>
                            <li>
                                
                                <a href="ControlPapeLote?accion=cambiarVerdad" >
                                <div class="opciones__botones--boton"><i class="bi bi-card-checklist"></i></div>
                                </a>
                            </li>
                            <li>
                                <!-- Mueve el formulario aquí y coloca el botón eliminar dentro del mismo -->
                                
                                <button type="button" id="btn-mover-papelera" class="opciones__botones--boton">
                                    <i class="bi bi-trash"></i>
                                </button>
                                <!-- <a class=" opciones__botones--boton" href="controlLote?accion=cambiarFalse"></a> -->
                                
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
                <div class="cajaLotes">

                </div>
            </section>
        </form>
        </main>
        <template id="tb_lotes">
            <hr class="linea__datos">
                    <div>
                        <div class="container Datos__lote" >
                            <div class="Datos__lote--numero">
                                <div class="num__lote">
                                    <% if (rol == 1) {%>
                                    <input class="checkbox seleccion" type="checkbox" name="selectedLotes" >
                                    <% } %>
                                    <a class="boton boton__lote numLote" href="">Lote </a>
                                </div>
                                <div class="Datos__lote--texto">
                                    <p class="Datos__lote--cantidad cantidadAnimales">Cantidad: </p>
                                </div>
                            </div>
                                <%
                                    if (rol == 1) {
                                %>
                            <div>
                                <p><a class=" boton boton--editar editarLote" href=""><i class="bi bi-pencil"></i></a></p>
                                <!--<p class="Datos__lote--texto Datos__lote--texto2">Última fecha de revisión: 12/05/2024</p>-->
                            </div>
                                <%}%>
                        </div>
                    </div>
        </template>
        <script src="js/listarLotes.js" type="module"></script>
        <script src="js/envioPape.js" type="module"></script>
    </body>
</html>
