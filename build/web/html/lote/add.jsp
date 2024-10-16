<%
    request.setAttribute("pageTitle", "agregar lote");
%>

<%@ include file="../../componentes/validacionAdmin.jsp" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<%@ include file="/componentes/head.jsp" %>
    <body>
        <header class="fondo_header">
            <div class="container encabezado">
                <div class="encabezado">
                    <%@ include file="../../componentes/btn_salir.jsp" %>
                    <img class="logo" src="Recursos/logo-BoviControl.png" alt="">
                    <p>BoviControl</p>
                </div>
                <div class="encabezado">
                    <%@ include file="../../componentes/botones_header.jsp" %>
                </div>
            </div>
        </header>
        
        <main>
            <section class="fondo__cinta">
                <div class="cinta__opciones container">
                    <div class="cinta__opciones--titulo">
                        <div>
                            <img class="cinta__logo" src="Recursos/vaquita.png" alt="">
                        </div>
                        <div>
                            <p class="cinta__Titulo">Agregar nuevo lote</p>
                        </div>
                    </div>
                </div>
            </section>
            
            <section>
                <div class="container tabla__listar">
                    <!-- Formulario de agregar lote -->
                    <form id="registro" action="#" method="POST">
                        <div class="alinear__Row">
                            <div class="alinear__colum">
                                <label>Ingresa el número del lote:</label>
                                <input id="numDoc" class="input_ingresar input--editar" type="text" name="txtNum" required=""><br>
                            </div>
                            <button class="boton boton--listar input--editar" type="submit">Agregar</button><br>
                        </div>
                    </form>
                </div>
            </section>
        </main>
        
        <%@ include file="/componentes/error_ingreso.jsp" %>

        <script src="js/agragrLotes.js" type="module"></script>

    </body>
</html>
