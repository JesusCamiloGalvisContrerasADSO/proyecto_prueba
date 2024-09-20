<%
    request.setAttribute("pageTitle", "Editar perfil");
%>
<%@page import="modelo.TipoDocum"%>
<%@page import="modeloDAO.TipoDocDAO"%>
<%@page import="modelo.TipoSangre"%>
<%@page import="modeloDAO.TipoSangreDAO"%>
<%@page import="modelo.Roles"%>
<%@page import="modeloDAO.RolesDAO"%>
<%@ page import="java.util.*" %>
<%@page import="modelo.Usuario"%>
<%@page import="modeloDAO.UsuarioDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="../../componentes/validacionRol.jsp" %>
<!DOCTYPE html>
<html>
    
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
            
        <section class="center registro__fondo">
            <div class="container center registro sombras--contenedor">
                
        <% 
                UsuarioDAO dao = new UsuarioDAO();
                int id = Integer.parseInt((String) request.getAttribute("idUser"));
                Usuario user = dao.list(id);
                //Usuario user = (Usuario) request.getAttribute("usuarioDetalle");
                
                if(idPerfil != user.getIdUsuario()){
                    response.sendRedirect("accesoDenegado.jsp");
                    return;
                    }
        %>
        <h1 class="titulo--caja">Editar perfil</h1>
        
        <form action="ControlPerfil" id="registro" method="POST" >
                  
                  <div class="registro__input">
                      <label for="nombre">Nombre:</label>
                      <input disabled="" class="input_registro" type="text" id="nombre" name="txtNom" value="<%=user.getNombre()%>">
                  </div>
                  <div class="registro__input">
                      <label for="apellido">Apellido:</label>
                      <input disabled="" class="input_registro" type="text" id="apellido" name="txtApell" value="<%=user.getApellido()%>">
                  </div>
                  <div class="registro__input">
                      <label for="documento">Documento:</label>
                      <input disabled="" class="input_registro" type="number" id="documento" name="txtNumDoc" value="<%=user.getDocumento()%>" >
                  </div>
                  <div class="registro__input">
                    <label for="tipoDocumento">Tipo de Documento:</label>
                    <select class="input_registro borde" name="txtTipDoc" id="tipDoc" required>
                        <% 
                        TipoDocDAO docDao = new TipoDocDAO();
                        List<TipoDocum> docLista = docDao.listar();
                        Iterator<TipoDocum> docIter = docLista.iterator();
                        while (docIter.hasNext()) {
                            TipoDocum docTipo = docIter.next();
                            if((docTipo.getId()) == (user.getDocid())){
                        %>
                        <option id="tipoDocumento" name="txtTipDoc" value="<%= user.getDocid() %>" selected=""><%=user.getTipoDocum().getNom()%></option>
                        <% }else{ %>
                            <option value="<%= docTipo.getId() %>"><%= docTipo.getNom() %></option>
                        <% }} %>
                    </select>
                  </div>
                  <div class="registro__input">
                    <label for="tipoSangre">Tipo de Sangre:</label>
                        
                        <% 
                        TipoSangreDAO sangreDao = new TipoSangreDAO();
                        List<TipoSangre> sangreLista = sangreDao.listar();
                        Iterator<TipoSangre> sangreIter = sangreLista.iterator();
                        %>
                        <input class="input_registro" disabled="" name="txtTipSang" value="<%=user.getTipoSangre().getNom()%>" >
                        
                  </div>
                  
                  <div class="registro__input">
                      <label for="telefono">Teléfono:</label>
                      <input class="input_registro borde" type="text" id="telefono" name="txtTel" value="<%=user.getTelefono()%>" required>
                  </div>
                  <div class="registro__input">
                      <label for="email">Email:</label>
                      <input id="correo" class="input_registro borde" name="txtCorreo" type="email" value="<%=user.getEmail()%>" required>
                  </div>
                  <div class="registro__input">
                      <label for="fechaContrato">Fecha inicio de Contrato:</label>
                      <input disabled="" class="input_registro" type="date" id="fechaContrato" name="fechaContrato" value="<%=user.getFechaContra()%>" readonly>
                  </div>
                    
                <div>
                    
                    <button id="enviar" class="boton boton__registro" type="submit" name="accion" value="Actualizar">Actualizar</button>
                    
                </div>
              </form>
            </div>
        </section>
    </body>
</html>
