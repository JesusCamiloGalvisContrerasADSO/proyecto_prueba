<%@page import="modelo.Raza"%>
<%@page import="modeloDAO.RazaDAO"%>
<%@ page import="java.util.*" %>
<%@ page import="modelo.TipoDocum" %>
<%@ page import="modeloDAO.TipoDocDAO" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    request.setAttribute("pageTitle", "Editar Raza");
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
              <%@ include file="../../componentes/botones_header.jsp" %>
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
                    RazaDAO dao = new RazaDAO();
                    int id = Integer.parseInt((String) request.getAttribute("idRaza"));
                    Raza Raz = dao.list(id);
                %>
                <div class="container tabla__listar">
                    <form action="ControlRaza" method="POST">
                        <div class="alinear__Row">
                            <input class="input_ingresar input--editar" type="text" name="txtNom" value="<%= Raz.getNombre()%>" required=""><br>
                            <input type="hidden" name="txtid" value="<%= Raz.getId() %>">
                            <input class="boton boton--listar input--editar" type="submit" name="accion" value="Actualizar"><br>
                        </div>
                    </form>
                </div>
            </section>
        </main>
    </body>
</html>
