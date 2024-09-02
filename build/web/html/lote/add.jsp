<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Lotes</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
        <link rel="stylesheet" href="css/style.css">
    </head>
    <body>
        <header class=" fondo_header">
            <div class="container encabezado">

              <div class="encabezado">
                <%@ include file="../../componentes/btn_salir.jsp" %>
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
                            <p class="cinta__Titulo">Agregar nuevo lote</p>
                        </div>
                    </div>
                </div>
            </section>
            
            <section>
                <div class="container tabla__listar">
                    <form action="controlLote" method="POST">
                        <div class="alinear__Row">
                            <div class="alinear__colum">
                                <label>Ingresa el numero del lote:</label>
                                <input class="input_ingresar input--editar" type="text" name="txtNum"><br>
                            </div>
                            <input class="boton boton--listar input--editar" type="submit" name="accion" value="Agregar"><br>
                        </div>
                    </form>
                </div>
            </section>
        </main>
    </body>
</html>
