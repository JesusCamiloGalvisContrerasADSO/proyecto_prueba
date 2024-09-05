
<%
Integer rol = (Integer) session.getAttribute("rol");
if (rol == null || rol != 1 ) {
    response.sendRedirect("accesoDenegado.jsp");
    return;
}
%>
