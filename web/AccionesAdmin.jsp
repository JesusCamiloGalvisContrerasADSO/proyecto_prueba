
<%@page import="modelo.DatosAdmin"%>
<%@page import="modeloDAO.DatosAdminDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ include file="componentes/validacionAdmin.jsp" %>
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Acciones</title>
  <link rel="stylesheet" href="css/style.css">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
  <link rel="stylesheet" href="acciones.css">
  <script src="js/acciones/menu_desplegable.js"> </script>
</head>

<body>
  <!-- este es el encabezado de la pagina -->
  <header class=" fondo__cinta">
    <div class="cinta__opciones container">
        <div class="cinta__opciones--titulo">
            <div>
                <img class="cinta__logo" src="Recursos/logo-BoviControl.png" alt="">
            </div>
            <div>
                <p class="cinta__Titulo">Control de ganado </p>
            </div>
        </div>
        <div>
            <ul class="menu">
                <li class="menu-item"><a  href="controlLote?accion=listar" class="menu-linkNormal">Lotes</a></li>
                    
                <li class="menu-item "><a href="ControlUsuario?accion=listar" class="menu-linkNormal">Usuarios</a></li>
                
                <li class="menu-item">
                    <a href="#" class="menu-link">Configuraciones</a>
                    <ul class="submenu">
                        <li class="submenu-item"><a href="ControlTipSan?accion=listar" class="submenu-link">Tipos de Sangre</a></li>
                        <li class="submenu-item"><a href="ControlTipoDoc?accion=listar" class="submenu-link">Tipos de Documentos</a></li>
                        <li class="submenu-item"><a href="ControlRaza?accion=listar" class="submenu-link">Tipos de Razas</a></li>
                    </ul>
                </li>
                <li class="menu-item">
                    <a href="#" class="menu-link icon-person"><i class="bi bi-person-circle"></i></a>
                    <ul class="submenu">
                        <li class="submenu-item"><a href="ControlPerfil?accion=listar&id=<%= idPerfil %>" class="submenu-link">Pefil</a></li>
                        <li class="submenu-item"><a href="ControlLogin?accion=cerrarSecion" class="submenu-link">Cerrar secion</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
    <hr class="linea__cinta">
  </header>
  <!-- este es el cuerpo del aplicativo web -->
  <main>

    <section class="fondo__cinta">

        
    </section>

    <section>
        <div class="Tipo__vista padding-40 container">
            <div class="Tipo__vista--punto"></div>
            <p class="Tipo__vista--texto">Con bovicontrol, manejo de animales de manera rapida y sencilla, 
                donde tengas informacion mas exacta sobre tus animales</p>
        </div>
    </section>
    <section>
        <% 
            DatosAdminDAO dao = new DatosAdminDAO();
            DatosAdmin datos = dao.listar();
        %>
        <div class="container cards__Acciones">
            <div class="cards--admin">
                <div>
                    <img class="imgCards--Admin" src="Recursos/usuario.jpg" alt="">
                </div>
                <div class="admind--contenido">
                    <h2 class="admind--titulo">Usuarios</h2>
                    <p class="letra--16">Tenemos un total de <%= datos.getCantUsu() %> usuarios registrados, los cuales nos brindan sus servicios</p>
                </div>
            </div>
            <div class="cards--admin">
                <div>
                    <img class="imgCards--Admin" src="Recursos/ventas.jpg" alt="">
                </div>
                <div class="admind--contenido">
                    <h2 class="admind--titulo">Ultimos movimientos</h2>
                    <p class="letra--16">Se han realizado un total de <%= datos.getCantLotVent() %> ventas de lotes de ganado</p>
                </div>
            </div>
            <div class="cards--admin">
                <div>
                    <img class="imgCards--Admin" src="Recursos/animales3.jpeg" alt="">
                </div>
                <div class="admind--contenido">
                    <h2 class="admind--titulo">Animales actuales</h2>
                    <p class="letra--16">Se cuenta con un total de <%= datos.getCantAnim() %> animales distribuidos en <%= datos.getCantLot() %> lotes</p>
                </div>
            </div>
        </div>
    </section>
    <a href="ControlReportes?accion=listars">Reportes</a>
  </main>
  
</body>

</html>