
<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %>
<%@page import="modelo.Animal"%>
<%@page import="modeloDAO.AnimalDAO"%>

<%@ include file="../../componentes/validacionRol.jsp" %>


<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Animales</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
        <link rel="stylesheet" href="css/style.css">

        <script src="js/acciones/Eliminar.js"></script>        
        <!-- Incluir SweetAlert2 CSS -->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@11/dist/sweetalert2.min.css">
        <!-- Incluir SweetAlert2 JS -->
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>

      </head>
    <body>
        
        <header class=" fondo_header">
            <div class="container encabezado">

              <div class="encabezado">
                  
                  <a href="controlLote?accion=listar">
                    <button class="boton_salir"><i class="bi bi-chevron-left"></i></button>
                   </a>
                
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
            <!-- aquí determina qué es lo que se está viendo -->
            
            <section class="fondo__cinta">
                <% 
                            int idLote = Integer.parseInt(request.getParameter("id"));
                            request.setAttribute("idLote", idLote);
                            
                            int numLote = Integer.parseInt(request.getParameter("num"));
                            request.setAttribute("numLote", numLote);
                            
                            %>

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
                 <div>
                        
                    <table class="datos__persona datos__animal centrar--texto">
                      <thead>
                      <tr >
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
                        <td class="center border--bottom"><button class="boton boton__lote"><%= animal.getNum() %></button></td>
                        <td class="animal--movil border--bottom"><%= animal.getRaza().getNombre() %></td>
                        <td class="animal--movil border--bottom"><%= animal.getNomTipoSex() %></td>
                        <td class="animal--tablet border--bottom"><%= animal.getPesos().getPeso() %> KG</td>
                        <td class="animal--tablet border--bottom"><input class="input_ingresar input--peso " type="number" placeholder="Ingresa..."></td>
                        <td class="animal--tablet border--bottom">
                          <p>Última actualización</p> 
                          <p><%= animal.getPesos().getFechaPeso() %></p>
                        </td>
                        <td class="animal--movil border--bottom col-salud"><%= animal.getSalud().getNombre()%></td>
                        <td class="border--bottom"><button class="boton boton--editar" ><i class="bi bi-pencil"></i></button></td>
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
