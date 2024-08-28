<%
    request.setAttribute("pageTitle", "Iniciar secion");
%>

<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="modelo.Usuario" %>
<%@ page import="modeloDAO.UsuarioDAO" %>
<%@ page import="controlador.ControlUsuario" %>

<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@ include file="../../componentes/head.jsp" %>
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
                            <p class="cinta__Titulo">Usuarios registrados</p>
                        </div>
                    </div>
                    <div class="menu">
                        <ul class="opciones__botones">
                            <li>
                                <a href="ControlUsuario?accion=add"><button class="opciones__botones--boton btn--agregarSangre" ><i class="bi bi-plus"></i> <p>Agregar</p> </button> </a>
                            </li>
                            <li>
                                
                            </li>
                            
                        </ul>
                    </div>
                </div>
        </section>
        
        <section>
                <div class="Tipo__vista container">
                    <div class="Tipo__vista--punto"></div>
                    <p class="Tipo__vista--texto">Número de lote</p>
                </div>
        </section>
        
        <section>
            <div class="container">
                <table >
                    <!--<thead>
                        <tr>
                            <th>ID Usuario</th>
                            <th>Nombre</th>
                            <th>Apellido</th>
                            <th>Teléfono</th>
                            <th>Correo Electrónico</th>
                            <th>Fecha de Contrato</th>
                            <th>Tipo de Documento</th>
                            <th>Tipo de Sangre</th>
                            <th>Número de Documento</th>
                            <th>Rol</th>
                            <th>Acciones</th>
                        </tr>
                    </thead>-->
                    <tbody>
                        <% 
                            UsuarioDAO dao = new UsuarioDAO();
                            List<Usuario> lista = dao.listar();
                            Iterator<Usuario> iter = lista.iterator();
                            Usuario Tipo = null;
                            while (iter.hasNext()) {
                            Tipo = iter.next();
                        %>
                        <tr>
                            <td><i class="bi bi-person-circle"></i></td>
                            <td><%= Tipo.getNombre() %> <%= Tipo.getApellido() %></td>
                            <td><%= Tipo.getTelefono() %></td>
                            <td><%= Tipo.getEmail() %></td>
                            <td><%= Tipo.getFechaContra() %></td>
                            <td><%= Tipo.getTipoDocum().getNom() %></td> <!-- Mostrar nombre del tipo de documento -->
                            <td><%= Tipo.getTipoSangre().getNom() %></td> <!-- Mostrar nombre del tipo de sangre -->
                            <td><%= Tipo.getDocumento() %></td>
                            <td><%= Tipo.getNomRol()%></td>
                            <td>
                                <a href="ControlUsuario?accion=editar&id=<%= Tipo.getIdUsuario() %>">Editar</a>
                                <a href="ControlUsuario?accion=eliminar&id=<%= Tipo.getIdUsuario() %>">Eliminar</a>
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
