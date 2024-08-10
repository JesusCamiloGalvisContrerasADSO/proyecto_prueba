<%@ page import="java.util.List" %>
<%@ page import="modelo.Perfil" %>
<%@ page import="modelo.Usuario" %>
<%@ page import="modeloDAO.PerfilDAO" %>
<%@ page import="modeloDAO.UsuarioDAO" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Lista de Usuarios</title>
</head>
<body>
    <div>
        <div>
            <a href="../../proyecto/paginasAcciones.html">Volver a Acciones</a>
            <h1>Usuarios registrados</h1>
        </div>
        
        <a href="controladorRegistro?accion=add">Agregar Nuevo</a>
        <table border="1">
            <thead>
                <tr>
                    <th>ID_perfil</th>
                    <th>Nombre</th>
                    <th>Apellido</th>
                    <th>Teléfono</th>
                    <th>Correo Electrónico</th>
                    <th>Dirección</th>
                    <th>Fecha de Nacimiento</th>
                    <th>Tipo Doc</th>
                    <th>Sangre</th>
                    <th>ID Usuario</th>
                    <th>Documento</th>
                    <th>Acciones</th>
                </tr>
            </thead>
            <tbody>
                <% 
                    PerfilDAO Pdao = new PerfilDAO();
                    List<Perfil> Plista = Pdao.listar();
                    
                    UsuarioDAO Udao = new UsuarioDAO();
                    List<Usuario> Ulista = Udao.listar();
                  
                    for (Perfil per : Plista) {
                        Usuario user = Udao.list(per.getUsuarioId());
                %>
                <tr>
                    <td><%= per.getIdPerfil() %></td>
                    <td><%= per.getNombre() %></td>
                    <td><%= per.getApellido() %></td>
                    <td><%= per.getTelefono() %></td>
                    <td><%= per.getEmail() %></td>
                    <td><%= per.getDireccion() %></td>
                    <td><%= per.getFechaNacimiento() %></td>
                    <td><%= per.getTipoDocId() %></td>
                    <td><%= per.getSangreId() %></td>
                    <td><%= user.getIdUsuario() %></td>
                    <td><%= user.getDocumento() %></td>
                    <td>
                        <a href="controladorRegistro?accion=editar&id=<%= per.getIdPerfil() %>">Editar</a>
                        <a href="controladorRegistro?accion=eliminar&id=<%= per.getIdPerfil() %>">Eliminar</a>
                    </td>
                </tr>
                <% } %>
            </tbody>
        </table>
    </div>
</body>
</html>
