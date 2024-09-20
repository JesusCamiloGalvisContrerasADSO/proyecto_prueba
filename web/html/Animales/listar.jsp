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
    
    <%@ include file="../../componentes/capturarLote_num.jsp" %>
    <%@ include file="../../componentes/head.jsp" %>
    <body>
        
        <header class=" fondo_header">
            <div class="container encabezado">

              <div class="encabezado">
                  
                  <a href="controlLote?accion=listar&id=<%=idLote%>&num=<%=numLote%>">
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

                <div class="cinta__opciones container">
                    <div class="cinta__opciones--titulo">
                        <div>
                            <img class="cinta__logo" src="Recursos/vaquita.png" alt="">
                        </div>
                        <div>
                            <p class="cinta__Titulo">Lote <%= numLote %></p>
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
                                <a href="ControlAnimal?accion=add&id=<%= idLote %>&num=<%= numLote %>"><button class="opciones__botones--boton" ><i class="bi bi-plus"></i></button></a>
                            </li>
                            <li>
                                <a href="ControlAnimal?accion=listarPapelera&id=<%= idLote %>&num=<%= numLote %>">
                                <button class="opciones__botones--boton"><i class="bi bi-card-checklist"></i></button>
                                </a>
                            </li>
                            <li>
                                
                                <form action="ControlAnimal" method="POST">
                                    <button class="opciones__botones--boton" type="submit" name="accion" value="cambiarFalse">
                                        <i class="bi bi-trash"></i>
                                    </button>
                            </li>
                            
                        </ul>
                    </div>
                </div>
                <hr class="linea__cinta">
            </section>
            
            <section class="container">
                <input hidden="" type="number" name="txtidLote" value="<%= idLote %>">
                <input hidden="" type="number" name="txtnumLote" value="<%= numLote %>">
                 <div>
                        
                    <table class="datos__persona datos__animal centrar--texto">
                      <thead>
                      <tr >
                        <th></th>
                        <th>Número animal</th>
                        <th class="animal--movil ">Raza</th>
                        <th class="animal--movil">Sexo/tipo</th>
                        <th class="animal--tablet">Ultimo peso</th>
                        <th class="animal--tablet">Nuevo peso</th>
                        <th class="animal--tablet">Fecha revisión</th>
                        <th class="animal--tablet">estado salud</th>
                        <th>Acciones</th>
                      </tr>
                    </thead>
                    <tbody >
                     <% 
                            AnimalDAO dao = new AnimalDAO();
                            List<Animal> lista = dao.listar(idLote);
                            Iterator<Animal> iter = lista.iterator();
                            Animal animal = null;
                            while (iter.hasNext()) {
                            animal = iter.next();
                        %>
                      <tr>
                        <td class="animal--movil border--bottom"><input class="checkbox" type="checkbox" name="selectedAnimales" value="<%= animal.getId() %>"></td>
                        <td class="center border--bottom"><a href="ControlInterAnimal?accion=listar&animal_id=<%= animal.getId() %>&numAnimal=<%= animal.getNum()%>&id=<%= idLote %>&num=<%= numLote %>" class="boton boton__lote"><%= animal.getNum() %></a></td>
                        <td class="animal--movil border--bottom"><%= animal.getRaza().getNombre() %></td>
                        <td class="animal--movil border--bottom"><%= animal.getNomTipoSex() %></td>
                        <td class="animal--tablet border--bottom" ><%= animal.getPesos().getPeso() %> KG</td>
                        <td class="animal--tablet border--bottom">
                        
                        <input id="" class="input_ingresar input--peso peso" type="number" 
                                placeholder="Ingresa..." 
                                onblur="enviarDatos(this, <%= animal.getId() %>, '<%= animal.getNum() %>', <%= numLote %>, <%= idLote %>)">
                        

                        </td>
                        <td class="animal--tablet border--bottom">
                          <p>Última actualización</p> 
                          <p><%= animal.getPesos().getFechaPeso() %></p>
                        </td>
                        <td class="animal--movil border--bottom col-salud"><%= animal.getSalud().getNombre()%></td>
                        <td class="border--bottom"><a href="ControlAnimal?accion=editar&id=<%= animal.getId() %>" class="boton boton--editar" ><i class="bi bi-pencil"></i></a></td>
                      </tr>
                        <% } %>
                    
                    </tbody>
                  </table>
                </div>
            </section>

                        <script src="js/modulos/buscador.js" ></script>
                        <script>
                            function enviarDatos(inputElement, animalId, numAnimal, numLote, idLote) {
                            let peso = inputElement.value;

                            // Verifica que el valor del peso no esté vacío
                            if (peso.trim() === '') {
                                alert('Por favor, ingresa un peso.');
                                return;
                            }

                            // Construye la URL para la solicitud con los parámetros dinámicos
                            let url = 'ControlInterAnimal?accion=AgregarPeso' +
                              '&animal_id=' + encodeURIComponent(animalId) + 
                              '&numAnimal=' + encodeURIComponent(numAnimal) + 
                              '&peso=' + encodeURIComponent(peso) +
                              '&numLote=' + encodeURIComponent(numLote) +  
                              '&idLote=' + encodeURIComponent(idLote);    

                            // Redirige a la URL para agregar el peso
                            window.location.href = url;
                        }

                        </script>

     
        </main>
    </body>
</html>
