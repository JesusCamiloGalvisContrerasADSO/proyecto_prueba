<%
    request.setAttribute("pageTitle", "Papelera animales");
%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %>
<%@page import="modelo.Animal"%>
<%@page import="modeloDAO.AnimalDAO"%>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>

<%@ include file="../../../componentes/validacionRol.jsp" %>
<!DOCTYPE html>
<html lang="es">
    <%@ include file="../../../componentes/head.jsp" %>
    
    <body>
        <header class=" fondo_header">
            <div class="container encabezado">
        <%@ include file="../../../componentes/capturarLote_num.jsp" %>

              <div class="encabezado">
                  
                 <a href="ControlAnimal?accion=listar&id=<%= idLote %>&num=<%= numLote %>">
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
                            <p class="cinta__Titulo">Lote <%= numLote %> / Eliminados</p>
                        </div>
                    </div>
                  <div>
                    <form class="opciones__buscador" action="">
                      <input class="opciones__buscador--input" type="search" class="" placeholder="Buscar" autocomplete="off"><!-- react-empty: 118 -->
                      <p class="opciones__buscador--icono"><i class="bi bi-search"></i></p>
                    </form>
                  </div>
                    <div class="menu">
                        <ul class="opciones__botones">
                            <li>
                                <% if(rol == 1 ){%>
                                <form action="ControlAnimal" method="POST">
                                    <button class="opciones__botones--boton" type="submit" name="accion" value="eliminar">
                                        <i class="bi bi-trash"></i>
                                    </button>
                                <% } %>
                            </li>
                            
                        </ul>
                    </div>
                </div>
                <hr class="linea__cinta">
            </section>
            
            <section class="container">
                 <div>
                    <input type="hidden" name="txtidLote" value="<%= idLote %>">
                    <input type="hidden" name="txtnumLote" value="<%= numLote%>">
                    
                    <table class="datos__persona datos__animal centrar--texto">
                      <thead>
                      <tr >
                        <th></th>
                        <th>Número animal</th>
                        <th class="animal--movil ">Raza</th>
                        <th class="animal--movil">Sexo/tipo</th>
                        <th class="animal--tablet">Ultimo peso</th>
                        <th class="animal--tablet">Fecha revisión</th>
                        <th class="animal--tablet">estado salud</th>
                        <th></th>
                      </tr>
                    </thead>
                    <tbody >
                     <% 
                            AnimalDAO dao = new AnimalDAO();
                            List<Animal> lista = dao.listarPapelera(idLote);
                            Iterator<Animal> iter = lista.iterator();
                            Animal animal = null;
                            while (iter.hasNext()) {
                            animal = iter.next();
                        %>
                      <tr>
                        <td class="animal--movil border--bottom"><input class="checkbox" type="checkbox" name="selectedAnimales" value="<%= animal.getId() %>"></td>
                        <td class="center border--bottom "><button class="boton boton__lote lote--ventas"><%= animal.getNum() %></button></td>
                        <td class="animal--movil border--bottom"><%= animal.getRaza().getNombre() %></td>
                        <td class="animal--movil border--bottom"><%= animal.getNomTipoSex() %></td>
                        <td class="animal--tablet border--bottom"><%= animal.getPesos().getPeso() %> KG</td>
                        <td class="animal--tablet border--bottom">
                          <p>Última actualización</p> 
                          <p><%= animal.getPesos().getFechaPeso() %></p>
                        </td>
                        <td class="animal--movil border--bottom col-salud"><%= animal.getSalud().getNombre()%></td>
                        <td class="animal--movil border--bottom col-salud"><a href="ControlAnimal?accion=cambiarVerdad&id=<%= animal.getId() %>&idLote= <%=idLote%>&numLoteidLote=<%=numLote%>">Restaurar</a></td>
                      </tr>
                        <% } %>
                    
                    </tbody>
                  </table>
                </div>
            </section>

                        <script src="js/modulos/buscador.js" ></script>
     
        </main>
    </body>
</html>
