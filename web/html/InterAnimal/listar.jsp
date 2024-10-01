<%@page import="modelo.Pesos"%>
<%@page import="modeloDAO.PesosDAO"%>
<%
    request.setAttribute("pageTitle", "Animales");
%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %>
<%@page import="modelo.Animal"%>
<%@page import="modeloDAO.AnimalDAO"%>

<%@ include file="../../componentes/validacionRol.jsp" %>


<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    
    <%@ include file="../../componentes/head.jsp" %>
    <%@ include file="../../componentes/capturarLote_num.jsp" %>
    
    <body>
        
        <header class=" fondo_header">
            <div class="container encabezado">

              <div class="encabezado">
                  
                  <a href="ControlAnimal?accion=listar&id=<%= idLote %>&num=<%= numLote %>">
                    <button class="boton_salir"><i class="bi bi-chevron-left"></i></button>
                </a>
                
                <img class="logo" src="Recursos/logo-BoviControl.png" alt="">
                <p>BoviControl</p>
              </div>
              <%@ include file="../../componentes/botones_header.jsp" %>
            </div>
        </header>
        
       <main>
            <!-- aquí determina qué es lo que se está viendo -->
            
            <section class="fondo__cinta">
                <%@ include file="../../componentes/capturarAnimal.jsp" %>

                <div class="cinta__opciones container">
                    <div class="cinta__opciones--titulo">
                        <div>
                            <img class="cinta__logo" src="Recursos/vaquita.png" alt="">
                            
                        </div>
                        <div>
                            <p class="cinta__Titulo">Animal <%= numAnimal %></p>
                        </div>
                    </div>
                </div>
                <hr class="linea__cinta">
            </section>
            
            <section class="container" >
                <div class="container datos--historia">
                    <div class=" historia historia--peso">
                    <div class="historia__titulo">
                      <span>Control de peso</span>
                      <a href="ControlInterAnimal?accion=add&animal_id=<%= animal_id %>&numAnimal=<%= numAnimal%>&id=<%= idLote %>&num=<%= numLote %>" class="historia__titulo-btn"><i class="bi bi-plus-lg"></i></a>
                    </div>
                    <table class="tabla">
                      <thead>
                        <tr>
                          <th class="tabla__encabezado">#</th>
                          <th class="tabla__encabezado">Peso</th>
                          <th class="tabla__encabezado">Fecha del control</th>
                          <th class="tabla__encabezado">Descripción</th>
                          <th class="tabla__encabezado">Acción</th>
                        </tr>
                      </thead>
                      <tbody>
                      <% 
                            PesosDAO dao = new PesosDAO();
                            List<Pesos> pesolista = dao.listar(animal_id);
                            Iterator<Pesos> iter = pesolista.iterator();
                            Pesos pesos = null;
                            int numero = 1;
                            while (iter.hasNext()) {
                            pesos = iter.next();
                        %>
                        <tr>
                          <td class="tabla__celda"><%= numero %></td>
                          <td class="tabla__celda"><%= pesos.getPeso() %> KG</td>
                          <td class="tabla__celda"><%= pesos.getFechaPeso()%></td>
                          <td class="tabla__celda"><p class="tabla__celda--descripcion"><%= pesos.getDescripcion()%></p></td>
                          <td class="tabla__celda center"><a href="ControlInterAnimal?accion=ModifiDesc&animal_id=<%= animal_id %>&numAnimal=<%= numAnimal%>&id=<%= idLote %>&num=<%= numLote %>&id_peso=<%= pesos.getIdPesos() %>" class="boton boton--editar" ><i class="bi bi-pencil"></i></a></td>
                        </tr>
                        <%
                            numero += 1;
                            } 
                        %>
                      </tbody>
                    </table>
                  </div>
                </div>
                
            </section>
        </main>
    </body>
</html>
