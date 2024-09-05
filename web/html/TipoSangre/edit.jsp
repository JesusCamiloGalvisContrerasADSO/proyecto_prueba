<%@ page import="java.util.*" %>
<%@ page import="modelo.TipoSangre" %>
<%@ page import="modeloDAO.TipoSangreDAO" %>

<%@ include file="../../componentes/validacionAdmin.jsp" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
            <section class="fondo__cinta">

                <div class="cinta__opciones container">
                    <div class="cinta__opciones--titulo">
                        <div>
                            <img class="cinta__logo" src="Recursos/vaquita.png" alt="">
                        </div>
                        <div>
                            <p class="cinta__Titulo">Editar tipo de Sangre</p>
                        </div>
                    </div>
                </div>
            </section>
            
            <section>
                <% 
                    TipoSangreDAO dao = new TipoSangreDAO();
                    int id = Integer.parseInt((String) request.getAttribute("idTipo"));
                    TipoSangre Tip = dao.list(id);
                %>
                <div class="container tabla__listar">
                    <form action="ControlTipSan" method="POST">
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
