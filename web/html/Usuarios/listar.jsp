<%
    request.setAttribute("pageTitle", "Usuarios");
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
    <!--<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
    <link rel="stylesheet" href="css/pages/Usuarios_Btns.css">-->
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
                <div class="Tipo__vista container border--bottom">
                    <div class="Tipo__vista--punto"></div>
                    <p class="Tipo__vista--texto">Usuarios</p>
                </div>
        </section>
        
        <section>
             <div class="container tabla__scroll">
                    <table class="datos__persona ">
                        <tbody class="persona__usuario">
                        <% 
                            UsuarioDAO dao = new UsuarioDAO();
                            List<Usuario> lista = dao.listar();
                            Iterator<Usuario> iter = lista.iterator();
                            Usuario Tipo = null;
                            while (iter.hasNext()) {
                            Tipo = iter.next();
                            if((Tipo.getIdUsuario()) != (1))
                            {
                        %>
                          <tr class="personas__fila listar--elementos">
                            <td class="persona__dato persona--icono"><i class="bi bi-person-circle"></i></td>
                            <td class="persona__dato">
                              <p class="persona--subTitulos"><%= Tipo.getNombre() %> <%= Tipo.getApellido() %></p>
                              <p><span > <%= Tipo.getTipoDocum().getNom() %>: </span> <%= Tipo.getDocumento() %></p>
                            </td>
                            <td class="persona__dato">
                              <p><span >Telefono: </span><%= Tipo.getTelefono() %></p>
                              <p class="persona--subTitulos"> <%= Tipo.getEmail() %></p>
                            </td>
                            <td class="persona__dato persona--sangre"><%= Tipo.getTipoSangre().getNom() %></td>
                            
                            <td class="persona__dato">
                                <span >Inicio de contrato:</span>
                                <p class="persona--subTitulos"><%= Tipo.getFechaContra() %></p>
                            </td>
                            <td class="persona__dato">
                                
                                <p class="persona--subTitulos"><%= Tipo.getNomRol()%></p>
                            </td>

                            <td class="persona__acciones">
                                <a class=" boton boton--editar" href="ControlUsuario?accion=editar&id=<%= Tipo.getIdUsuario() %>"><i class="bi bi-pencil"></i></a>
                                <a class=" boton boton--eliminar" href="ControlUsuario?accion=eliminar&id=<%= Tipo.getIdPerfil()%>"><i class="bi bi-trash3"></i></a>
                            </td>
                          </tr>
                          <tr >
                            <td class="persona__linea"></td>
                            <td class="persona__linea"></td>
                            <td class="persona__linea"></td>
                            <td class="persona__linea"></td>
                            <td class="persona__linea"></td>
                            <td class="persona__linea"></td>
                            <td class="persona__linea"></td>
                          </tr>
                          <% }} %>
                        </tbody>
                    </table>
             </div>
        </section>
        
    </main>
</body>
</html>
