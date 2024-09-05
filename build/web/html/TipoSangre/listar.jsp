<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="modelo.TipoSangre" %>
<%@ page import="modeloDAO.TipoSangreDAO" %>
<%@ page import="controlador.ControlTipSan" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>

<%@ include file="../../componentes/validacionAdmin.jsp" %>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Lotes</title>
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
                <a href="ControlAcciones?accion=volver">
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
            <section class="fondo__cinta">

                <div class="cinta__opciones container">
                    <div class="cinta__opciones--titulo">
                        <div>
                            <img class="cinta__logo" src="Recursos/vaquita.png" alt="">
                        </div>
                        <div>
                            <p class="cinta__Titulo">Tipos de sangre</p>
                        </div>
                    </div>
                    <div class="menu">
                        <ul class="opciones__botones">
                            <li>
                                <a href="ControlTipSan?accion=add"><button class="opciones__botones--boton btn--agregarSangre" ><i class="bi bi-plus"></i> <p>Agregar</p> </button> </a>
                            </li>
                            <li>
                                
                            </li>
                            
                        </ul>
                    </div>
                </div>
            </section>
            
            <section>
                <div class="container tabla__listar">
                    <table class="tabla__listar--datos" >
                        <thead class="listar--cabecero">
                            <tr>
                                <th class="listar--datos">ID</th>
                                <th class="listar--datos">Nombre</th>
                                <th class="listar--datos">Acciones</th>
                            </tr>
                        </thead>
                        <% 
                            TipoSangreDAO dao = new TipoSangreDAO();
                            List<TipoSangre> lista = dao.listar();
                            Iterator<TipoSangre> iter = lista.iterator();
                            TipoSangre Tipo = null;
                            while (iter.hasNext()) {
                            Tipo = iter.next();
                        %>
                        <tbody>
                            <tr class="listar--elementos">
                                <td class="listar--datos"><%= Tipo.getId() %></td>
                                <td class="listar--datos"><%= Tipo.getNom() %></td>
                                <td class="listar--datos ">
                                    <div class="btn--acciones">  
                                    <a class="boton boton--editar" href="ControlTipSan?accion=editar&id=<%= Tipo.getId() %>"><i class="bi bi-pencil"></i></a>
                                    <a class="boton boton--eliminar " href="ControlTipSan?accion=eliminar&id=<%= Tipo.getId() %>"><i class="bi bi-trash3"></i></a>
                                    <!--<button  class="boton boton--eliminar show-example-btn" href=""><i class="bi bi-trash3"></i></button>-->
                                    </div>
                                </td>
                            </tr>
                        <% } %>
                        </tbody>
                    </table>
                </div>
            </section>
        </main>
        
    </body>
</html>
