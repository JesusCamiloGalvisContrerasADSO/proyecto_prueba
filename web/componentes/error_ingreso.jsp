  <%
    request.setAttribute("pageTitle", "Iniciar sesi�n");

    // Verificar si hay un mensaje de error en la solicitud
    String errorMessage = (String) request.getAttribute("error");
    if (errorMessage != null) { 
%>
<script src="js/acciones/error_ingreso.js"></script>
<% 
    } 
%>