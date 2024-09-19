<%@page import="modelo.Usuario"%>
<%@page import="modeloDAO.UsuarioDAO"%>
<%
    request.setAttribute("pageTitle", "Perfil");
%>

<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %>

<%@ page contentType="text/html" pageEncoding="UTF-8"%>
    <%@ include file="../../componentes/validacionRol.jsp" %>
    
<!DOCTYPE html>

<html lang="es">
    <%@ include file="../../componentes/head.jsp" %>
    
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
                  <%@ include file="../../componentes/botones_header.jsp" %>
            </div>
        </header>
        
       <main>
        <section class="fondo__cinta cinta--perfil">
           
            <div class="cinta__opciones cinta--veterinaria container">
                <div class="cinta__opciones--titulo">
                    <div class="perfil--icono">
                        <i class="bi bi-person"></i>
                    </div>
                    <div >
                        <h1 class="cinta__Titulo vet--titulo perfil--titulo">Perfil</h1>
                    </div>
                </div>
                
                <div class="menu">
                    <ul class="opciones__botones botones--usuario">                        
                        <li>
                            <a href="ControlPerfil?accion=editar&id=<%= idPerfil %>" >
                            <button class="opciones__botones--boton " ><i class="bi bi-pencil"></i></button>
                            </a>
                        </li>
                    </ul>
                </div>
                
            </div>
        </section>
        <hr class="linea__cinta">

      

        <section>
             <% 
                UsuarioDAO dao = new UsuarioDAO();
                int id = Integer.parseInt((String) request.getAttribute("idUser"));
                Usuario user = dao.list(id);
                //Usuario user = (Usuario) request.getAttribute("usuarioDetalle");
                
                //con esto tenemos la seguridad para que si no corresponde a su id no muestre
                //la informacion de otra persona
                 if(idPerfil != id){
                    response.sendRedirect("accesoDenegado.jsp");
                    return;
                }
              %>
            <div class="container Datos__usuario">
                <div>
                    <ul class="usuario__seccion">
                        <li>
                            <h3 class="usu__seccion--titulo">Nombre:</h3>
                            <p class="usu__seccion--texto"><%=user.getNombre()%></p>
                        </li>
                        <li>
                            <h3 class="usu__seccion--titulo">Apellido:</h3>
                            <p class="usu__seccion--texto"><%=user.getApellido()%></p>
                        </li>
                        <li>
                            <h3 class="usu__seccion--titulo">Tipo de documento:</h3>
                            <p class="usu__seccion--texto"><%=user.getTipoDocum().getNom()%></p>
                        </li>
                        <li>
                            <h3 class="usu__seccion--titulo">Documento:</h3>
                            <p class="usu__seccion--texto"><%=user.getDocumento()%></p>
                        </li>
                        <li>
                            <h3 class="usu__seccion--titulo">Teléfono:</h3>
                            <p class="usu__seccion--texto"><%=user.getTelefono()%></p>
                        </li>
                    </ul>
                </div>
                <div >
                    <ul class="usuario__seccion">
                        <li>
                            <h3 class="usu__seccion--titulo">Tipo de sangre:</h3>
                            <p class="usu__seccion--texto"><%=user.getTipoSangre().getNom()%></p>
                        </li>
                        <li>
                            <h3 class="usu__seccion--titulo">Correo electronico:</h3>
                            <p class="usu__seccion--texto"><%=user.getEmail()%></p>
                        </li>
                        <li>
                            <h3 class="usu__seccion--titulo">Fecha de inicio de contrato:</h3>
                            <p class="usu__seccion--texto"><%=user.getFechaContra()%></p>
                        </li>
                    </ul>
                </div>
            </div>
        </section>
    </main>
    </body>
</html>

