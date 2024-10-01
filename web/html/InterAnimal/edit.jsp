<%@page import="modelo.Pesos"%>
<%@page import="modeloDAO.PesosDAO"%>
<%
    request.setAttribute("pageTitle", "Animales");
%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %>

<%@ include file="../../componentes/validacionRol.jsp" %>
<%@ include file="../../componentes/capturarAnimal.jsp" %>
<%@ include file="../../componentes/capturarLote_num.jsp" %>


<%@ page contentType="text/html; charset=UTF-8" language="java" %>


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
            <!-- aquí determina qué es lo que se está viendo -->
            
            <section class="fondo__cinta">

                <div class="cinta__opciones container">
                    <div class="cinta__opciones--titulo">
                        <div>
                            <img class="cinta__logo" src="Recursos/vaquita.png" alt="">
                            
                        </div>
                        <div>
                            <p class="cinta__Titulo">Agregar peso</p>
                        </div>
                    </div>
                </div>
                <hr class="linea__cinta">
            </section>
            
            <section class="center registro__fondo">
                <div class="container center registro sombras--contenedor">
                    <% 
                        PesosDAO dao = new PesosDAO();
                        int id = Integer.parseInt((String) request.getAttribute("id_peso"));
                        Pesos datos = dao.list(id);
                    %>
                    <form action="ControlInterAnimal" method="POST" accept-charset="UTF-8">
                        <div class="registro__input">
                           <input class="input_registro" type="hidden" name="txtNumAnimal" value="<%= numAnimal %>">
                           <input class="input_registro" type="hidden" name="txtIdAnimal" value="<%= animal_id %>">
                           <input class="input_registro" type="hidden" name="txtIdLote" value="<%= idLote %>">
                           <input class="input_registro" type="hidden" name="txtNumLote" value="<%= numLote %>">
                           <input class="input_registro" type="hidden" name="txtidPeso" value="<%= id %>">
                           
                           <label for="nombre">Agregar descripción:</label>
                           <input class="input_registro" type="text" id="nombre" name="descripcion" value="<%= datos.getDescripcion()%>" required="">
                       </div>
                       <div>

                         <button class="boton boton__registro" type="submit" name="accion" value="ActualizarDesc">Actualizar</button>

                       </div>
                    </form>
                </div>
                
            </section>
        </main>
    </body>
</html>
