<%
Integer rol = (Integer) session.getAttribute("rol");
if (rol == null || (rol != 1 && rol != 2)) {
    response.sendRedirect("accesoDenegado.jsp");
    return;
}
%>