<%
    request.setAttribute("pageTitle", "Reportes");
%>

<%@ include file="/componentes/validacionAdmin.jsp" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<%@ include file="/componentes/head.jsp" %>
<body>

    <!-- este es el header de la vista veterinaria  -->
    <header class=" fondo_header">
            <div class="container encabezado">
              <div class="encabezado">
                  <a href="ControlLogin?accion=acciones">
                    <button class="boton_salir"><i class="bi bi-chevron-left"></i></button>
                   </a>
                <img class="logo" src="Recursos/logo-BoviControl.png" alt="">
                <p>BoviControl</p>
              </div>
                  <%@ include file="../../componentes/botones_header.jsp" %>
            </div>
        </header>

    <main>
        <section class="fondo__cinta">
            <div class="cinta__opciones cinta--veterinaria container">
                <div class="cinta__opciones--titulo">
                    <h1 class="cinta__Titulo vet--titulo">Informes</h1>
                </div>
                
                <div class="menu">
                    <ul class="opciones__botones botones--usuario">                        
                        
                        <li>
                            <button class="opciones__botones--boton " id="descarga" type="submit"><i class="bi bi-printer"></i></button>
                        </li>
                    </ul>
                </div>
                
            </div>
            <!-- <hr class="linea__cinta"> -->
        </section>

        <section>
            <div class="Reportes__seleccion container center">
              <div class="fac__formulario">
                <div class="fac--selec--title">
                  <div class="Tipo__vista--punto"></div>
                  <label for="" class="Tipo__vista--texto subtitulo--usuario">Selecciona estado y lote</label>
                </div>
                    <div>
                        <!-- Select para elegir el estado de los lotes -->
                        <select class="input_ingresar input--facturacion" id="estado">
                            <option value="" disabled selected >Estado</option>
                            <option value="1">Activo</option>
                            <option value="0">Eliminado</option>
                        </select>

                        <!-- Select para los lotes -->
                        <select class="input_ingresar input--facturacion" id="lote">
                            <!-- Las opciones se agregarán dinámicamente aquí -->
                        </select>
                    </div>
                    <div>
                        <button class="boton boton--adaptado" id="consultar" type="submit"> consultar</button>
                    </div>
            </div>
        </section >
        <section>
          <div  class="tamaño__factura container">
            <div id="reporte" class= "factura ">
              <div class="factura__encabezado">
                <div class="logo__factura">
                  <img class="logo--factura" src="Recursos/logo-BoviControl.png" alt="">
                  <p>BoviControl</p>
                </div>
                <div class="titulo__factura">
                  <h2>Informe de animales</h2>
                </div>
              </div>

              <div class="informacion__lote">
                <div class="infor__lote--cambios">
                  <h1 id="NumLote">Lote</h1>
                </div>
                <div class="center">
                    <div class="factura__animales datos--basicos">
                        
                        <div class="alinear-izquierda borde-1px">
                            <p class="numero texto--inicio padding-3px with-160">Animales sanos:</p>
                            <p class="numero tamaño--100" id="sanos">0</p>
                        </div>
                        <div class="alinear-izquierda borde-1px ">
                            <p class="numero texto--inicio padding-3px with-160">Animales enfermos:</p>
                            <p class="numero tamaño--100" id="enfermos">0</p>
                        </div>
                        <div class="alinear-izquierda borde-1px">
                            <p class="numero texto--inicio padding-3px with-160">Animales eliminados:</p>
                            <p class="numero tamaño--100" id="eliminados">0</p>
                        </div>
                        
                    </div>
                    <div>

                        <div class="alinear-izquierda borde-1px">
                            <p class="numero texto--inicio padding-3px with-160">Total neto:</p>
                            <p class="numero tamaño--100 " id="totalAni">0</p>
                        </div>
                        <div class="alinear-izquierda borde-1px fondo--celeste">
                            <p class="numero texto--inicio padding-3px with-160 negrita">Total activo:</p>
                            <p class="numero tamaño--100 Total" >0</p>
                        </div>
                    </div>
                </div>
              </div>

              <div>
                <p>Aquí se muestran los datos más actuales que se encuentran en el lote como su numero, raza, tipo de sexo, peso, salud y la ultima fecha donde se registro algun peso.</p><br>
                <table class="factura__animales">
                  <thead>
                    <tr>
                      <th class="fact__animales--campos">Numero animal</th>
                      <th class="fact__animales--campos">Raza</th>
                      <th class="fact__animales--campos">Sexo/tipo</th>
                      <th class="fact__animales--campos">Peso</th>
                      <th class="fact__animales--campos">Salud</th>
                      <th class="fact__animales--campos">Fecha Ultimo Peso</th>
                    </tr>
                  </thead>
                  <tbody>

                  </tbody>
                </table>
              </div>

              <div>
                <table class="factura__resumen">
                  <tr>
                    <td class="factura__resumen--campos">Fecha de informe: <span id="fecha--reporte">00/00/00</span></td>
                    <td class="factura__resumen--campos">Total de animales: <span class="Total">0</span></td>
                    
                  </tr>
                </table>
              </div>
            </div>
          </div>    
        </section>
    </main>

    <template id="tb_animals">
        <tr>
            <td class="numero fact__animales--campos"></td>
            <td class="raza fact__animales--campos"></td>
            <td class="sexo fact__animales--campos"></td>
            <td class="peso fact__animales--campos"></td>
            <td class="salud fact__animales--campos"></td>
            <td class="fechaVenta fact__animales--campos"></td>
        </tr>
    </template>
    


    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/html2pdf.js/0.9.2/html2pdf.bundle.min.js"></script>
    <script src="js/reportes.js" type="module"></script>
</body>
</html>
