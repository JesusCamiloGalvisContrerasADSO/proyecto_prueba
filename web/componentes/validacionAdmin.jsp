
<%
Integer rol = (Integer) session.getAttribute("rol");
Integer idPerfil = (Integer) session.getAttribute("idPerfil");
if (rol == null || rol != 1 ) {
    response.sendRedirect("accesoDenegado.jsp");
    return;
}
%>
