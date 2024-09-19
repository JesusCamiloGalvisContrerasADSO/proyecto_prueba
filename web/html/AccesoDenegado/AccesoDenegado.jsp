
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Acceso denegado</title>
  <link rel="stylesheet" href="../../css/style.css">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">

</head>

<body>
  <header class=" fondo_header">
            <div class="container encabezado">
              <div class="encabezado">
                 <%@ include file="../../componentes/btn_salir.jsp" %>
                <img class="logo" src="../../Recursos/logo-BoviControl.png" alt="">
                <p>BoviControl</p>
              </div>
                  
            </div>
    </header>
                
                <main >
                    <div class="container alertaAcceso">
                        <p class="iconoAcceso"><i class="bi bi-exclamation-triangle-fill"></i></p>
                        <h1>No tienes permisos para realizar esta accion - Error 404</h1>
                    </div>
                </main>

</body>

</html>
