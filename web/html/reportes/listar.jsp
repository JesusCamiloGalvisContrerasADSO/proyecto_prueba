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
    <script>

        const selectLote = document.querySelector('#lote');  // Referencia al select
        const fragmento = document.createDocumentFragment(); // Crear un fragmento para optimizar la inserción en el DOM
        const consultar = document.querySelector("#consultar");
        const fechaReporte = document.querySelector("#fecha--reporte");

        // Función para capturar los lotes desde el servidor
        async function obtenerLotes() {
            try {
                // Realizamos la petición al servlet que devuelve los lotes en formato JSON
                const response = await fetch('ControlReportes?accion=listarLotes');

                // Verificamos si la respuesta es exitosa
                if (!response.ok) {
                    throw new Error(`Error HTTP: ${response.status}`);
                }

                // Convertimos la respuesta en un objeto JSON
                const lotes = await response.json();

                // Esperamos a que el usuario seleccione un estado para filtrar los lotes
                document.querySelector('#estado').addEventListener('change', function () {
                    const estadoSeleccionado = this.value; // Obtener el valor seleccionado
                    llenarSelectLotes(lotes, estadoSeleccionado); // Llamar a la función con el estado
                });

            } catch (error) {
                console.error('Error al obtener los lotes:', error);
            }
        }

        // Función para llenar el <select> con los lotes obtenidos según el estado
        function llenarSelectLotes(lotes, estado) {


            // Limpiar las opciones anteriores del select de lotes
            selectLote.innerHTML = '';

            // Agregar opción por defecto
            let opcionDefault = document.createElement("option");
            opcionDefault.value = '';
            opcionDefault.textContent = 'Seleccione...';
            opcionDefault.disabled = true;
            opcionDefault.selected = true;
            fragmento.appendChild(opcionDefault);

            // Iterar sobre los lotes y crear opciones según el estado seleccionado
            lotes.forEach(lote => {
                if (lote.est == estado) {
                    let option = document.createElement("option");
                    option.value = lote.id;  // El valor del ID del lote
                    option.textContent = "Lote " + lote.num;  // El texto que se mostrará
                    fragmento.appendChild(option);
                }
            });

            // Añadir todas las opciones al select de una sola vez
            selectLote.appendChild(fragmento);
        }

        // Llamar a la función obtenerLotes para llenar el select cuando se cargue la página
        obtenerLotes();



        // ------------------------------------------------------------------------------------------------------

        

        document.querySelector("#consultar").addEventListener("click", async function() {
            
    const idLote = document.querySelector("#lote");
    const Numlote = document.querySelector("#NumLote");
    

    try {
        // Hacemos la solicitud al servidor para obtener los animales del lote
        const respuesta = await fetch(`ControlReportes?accion=ConsultaLote&id=` + idLote.value);

        // Verificamos si la respuesta fue exitosa
        if (!respuesta.ok) {
            throw new Error('Error en la solicitud: ' + respuesta.status);
        }

        // Convertimos la respuesta en un objeto JSON
        const animales = await respuesta.json();
        // console.log(animales)

        // Llamamos a la función que llena la tabla con los animales obtenidos
        llenarTablaAnimales(animales, Numlote);

    } catch (error) {
        console.error('Error al obtener los animales:', error);
    }
});

const fecha = () => {
    let date = new Date();
        let day = date.getDate().toString().padStart(2, '0');  // Asegurar que el día tenga dos dígitos
        let mes = (date.getMonth() + 1).toString().padStart(2, '0');  // Asegurar que el mes tenga dos dígitos
        let ano = date.getFullYear();

        let fechaCompleta = day + "/" + mes + "/" + ano

        return fechaCompleta;
}

function llenarTablaAnimales(animales, Numlote) {
    const tbody = document.querySelector(".factura__animales tbody");  // Referencia al cuerpo de la tabla

    const activos = document.querySelectorAll(".Total");
    const sanos = document.querySelector("#sanos");
    const enfermos = document.querySelector("#enfermos");
    const eliminados = document.querySelector("#eliminados");
    const totalAni = document.querySelector("#totalAni");

    tbody.innerHTML = '';  // Limpiamos las filas anteriores

    const template = document.querySelector("#tb_animals");  // Referencia al template
    const fragmento = document.createDocumentFragment();  // Crear el fragmento

    let totalAnimales = 0;
    let animalEliminado = 0;
    let animalSano = 0;
    let animalEnfermo = 0;

    // Separar los animales activos y eliminados
    const animalesActivos = animales.filter(animal => animal.estado !== 0);
    const animalesEliminados = animales.filter(animal => animal.estado === 0);

    // Función para crear filas de animales
    function crearFilaAnimal(animal, esPar) {
        // Clonamos el contenido del template
        const clone = document.importNode(template.content, true);

        // Rellenamos el contenido de cada celda
        clone.querySelector(".numero").textContent = animal.num;
        clone.querySelector(".raza").textContent = animal.Raza.nombre;
        clone.querySelector(".sexo").textContent = animal.nomTipoSex;
        clone.querySelector(".peso").textContent = animal.pesos.peso + " KG";
        clone.querySelector(".salud").textContent = animal.salud.nombre;
        clone.querySelector(".fechaVenta").textContent = animal.pesos.fechaPeso;

        // Alternar color: si la fila es par, añadimos la clase "fondo--celeste"
        if (esPar) {
            clone.querySelector("tr").classList.add("fondo--celeste");
        }

        return clone;
    }

    // Añadir animales activos primero
    animalesActivos.forEach((animal, index) => {
        const fila = crearFilaAnimal(animal, index % 2 === 0);
        fragmento.appendChild(fila);
        totalAnimales += 1;
        if(animal.salud.nombre === "Sano") {
            animalSano += 1;
        } else {
            animalEnfermo += 1;
        }
    });

     // Añadir una fila de separación y el título "Eliminados"
     if (animalesEliminados.length > 0) {
        const filaEliminados = document.createElement("tr");
        const celdaEliminados = document.createElement("td");
        celdaEliminados.colSpan = 6;  // Abarca todas las columnas de la tabla
        celdaEliminados.textContent = "Eliminados";
        celdaEliminados.classList.add("fondo-rojo");  
        filaEliminados.appendChild(celdaEliminados);
        fragmento.appendChild(filaEliminados);
    }

    // Añadir animales eliminados después
    animalesEliminados.forEach((animal, index) => {
        const fila = crearFilaAnimal(animal, (totalAnimales + index) % 2 === 0);
        fragmento.appendChild(fila);
        totalAnimales += 1;
        animalEliminado += 1;
        if(animal.salud.nombre === "Sano") {
            animalSano += 1;
        } else {
            animalEnfermo += 1;
        }
    });

    let TotalActivo = totalAnimales - animalEliminado;

    // Finalmente, añadimos el fragmento al tbody de la tabla
    tbody.appendChild(fragmento);

    // Itera sobre cada elemento de la lista y actualiza su textContent
    activos.forEach(element => {
        element.textContent = TotalActivo;
    });

    eliminados.textContent = animalEliminado;
    sanos.textContent = animalSano;
    enfermos.textContent = animalEnfermo;
    totalAni.textContent = totalAnimales;
    fechaReporte.textContent = fecha();

    Numlote.textContent = selectLote.options[selectLote.selectedIndex].textContent; // Muestra el texto de la opción seleccionada
    
}

// ---------------------------------------------------------------


document.querySelector('#descarga').addEventListener('click', () => {
    const element = document.querySelector('#reporte');
    const opt = {
        margin:       0.4,  // Reduce el margen para capturar más del contenido
        filename:     'reporte_completo.pdf',
        image:        { type: 'jpeg', quality: 0.98 },
        html2canvas:  { scale: 8, useCORS: true },  // Aumenta la escala para mejorar la resolución y captura
        jsPDF: { unit: 'in', format: 'letter', orientation: 'portrait' }

    };
    html2pdf().from(element).set(opt).save();
});

    
    
    </script>
</body>
</html>
