<%
    request.setAttribute("pageTitle", "Papelera lotes");
%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="modelo.LoteM" %>
<%@ page import="modeloDAO.LoteDAO" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>

<%@ include file="../../../componentes/validacionRol.jsp" %>
<!DOCTYPE html>
<html lang="es">
    <%@ include file="../../../componentes/head.jsp" %>
    <body>
        <header class="fondo_header">
            <div class="container encabezado">
                <div class="encabezado">
                    <a href="controlLote?accion=listar">
                        <button class="boton_salir"><i class="bi bi-chevron-left"></i></button>
                    </a>
                    <img class="logo" src="Recursos/logo-BoviControl.png" alt="">
                    <p>BoviControl</p>
                </div>
                <%@ include file="../../../componentes/botones_header.jsp" %>
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
                                <button class="opciones__botones--boton " id="btnEliminar">
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
                <div class="cajaLotes">
                    <hr class="linea__datos">

                </div>
            </section>
        </main>
        <template id="tb_lotes">
            
            <div class="container Datos__lote" >
                <div class="Datos__lote--numero">
                    <div class="num__lote">
                        <input class="checkbox seleccion" type="checkbox" name="selectedLotes" >
                        <a class="boton boton__lote lote--ventas numLote" href="">Lote </a>
                    </div>
                    <div class="Datos__lote--texto">
                        <p class="Datos__lote--cantidad cantidadAnimales">Cantidad: </p>
                    </div>
                </div>
                <div>
                    <p><a href="" class="btn-restaurar restaurarLote" >Restaurar</a></p>
                    <!--<p class="Datos__lote--texto Datos__lote--texto2">Última fecha de revisión: 12/05/2024</p>-->
                </div>
            </div>
            <hr class="linea__datos">
        </template>

        <!-- <script src="js/eliminarLote.js" type="module"></script> -->
        <script src="js/listarLotesPape.js" type="module"></script>
    </body>
</html>
