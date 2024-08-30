<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="modelo.Raza" %>
<%@ page import="modeloDAO.RazaDAO" %>
<%@ page import="controlador.ControlRaza" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%
    request.setAttribute("pageTitle", "Tipos de documentos");
%>
<!DOCTYPE html>
<html lang="es">
   
<%@ include file="../../componentes/head.jsp" %>

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
                            <p class="cinta__Titulo">Tipos razas de ganado</p>
                        </div>
                    </div>
                    <div class="menu">
                        <ul class="opciones__botones">
                            <li>
                                <a href="ControlRaza?accion=add"><button class="opciones__botones--boton btn--agregarSangre" ><i class="bi bi-plus"></i> <p>Agregar</p> </button> </a>
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
                            RazaDAO dao = new RazaDAO();
                            List<Raza> lista = dao.listar();
                            Iterator<Raza> iter = lista.iterator();
                            Raza Tipo = null;
                            while (iter.hasNext()) {
                            Tipo = iter.next();
                        %>
                        <tbody>
                            <tr class="listar--elementos">
                                <td class="listar--datos"><%= Tipo.getId() %></td>
                                <td class="listar--datos"><%= Tipo.getNombre()%></td>
                                <td class="listar--datos">
                                    <div class="btn--acciones">
                                    <a class="boton boton--editar" href="ControlRaza?accion=editar&id=<%= Tipo.getId() %>"><i class="bi bi-pencil"></i></a>
                                    <a class="boton boton--eliminar" href="ControlRaza?accion=eliminar&id=<%= Tipo.getId() %>"><i class="bi bi-trash3"></i></a>
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
