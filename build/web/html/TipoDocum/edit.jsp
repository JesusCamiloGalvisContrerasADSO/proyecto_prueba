<%@ page import="java.util.*" %>
<%@ page import="modelo.TipoDocum" %>
<%@ page import="modeloDAO.TipoDocDAO" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    request.setAttribute("pageTitle", "editar documento");
%>

<%@ include file="../../componentes/validacionAdmin.jsp" %>


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
              <div class="encabezado">
                <ul class="encabezado__lista">
                  
                  <li><a class="encabezado__lista--texto" href="">Usuario</a></li>

                  <li class="encabezado__lista--icono"><i class="bi bi-person-circle"></i></li>
                </ul>
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
                            <p class="cinta__Titulo">Editar Tipo de documento</p>
                        </div>
                    </div>
                </div>
            </section>
            
            <section>
                <% 
                    TipoDocDAO dao = new TipoDocDAO();
                    int id = Integer.parseInt((String) request.getAttribute("idTipo"));
                    TipoDocum Tip = dao.list(id);
                %>
                <div class="container tabla__listar">
                    <form action="ControlTipoDoc" method="GET">
                        <div class="alinear__Row">
                            <input class="input_ingresar input--editar" type="text" name="txtNom" value="<%= Tip.getNom() %>"><br>
                            <input type="hidden" name="txtid" value="<%= Tip.getId() %>">
                            <input class="boton boton--listar input--editar" type="submit" name="accion" value="Actualizar"><br>
                        </div>
                    </form>
                </div>
            </section>
        </main>
    </body>
</html>
