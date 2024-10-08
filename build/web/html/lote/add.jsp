<%
    request.setAttribute("pageTitle", "agregar lote");
%>

<%@ include file="../../componentes/validacionAdmin.jsp" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<%@ include file="/componentes/head.jsp" %>
    <body>
        <header class=" fondo_header">
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
                    <form action="controlLote" method="POST">
                        <div class="alinear__Row">
                            <div class="alinear__colum">
                                <label>Ingresa el numero del lote:</label>
                                <input class="input_ingresar input--editar" type="text" name="txtNum" required=""><br>
                            </div>
                            <input class="boton boton--listar input--editar" type="submit" name="accion" value="Agregar"><br>
                        </div>
                    </form>
                </div>
            </section>
        </main>
        <%@ include file="/componentes/error_ingreso.jsp" %>
    </body>
</html>
