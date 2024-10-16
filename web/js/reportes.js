/**
 * se ajecuta la funcion
 * @obtenerLotes
 * la cual traera todos los lotes que se encuentren en la base de datos
 * y que esten listos para mostrar al usuario al momento de que este 
 * solicite la informacion
 * 
 * ademas se manejan los eventos que se necesitan para poder generar el 
 * reporte de los lotes y la impresion del mismo en formato pdf
 */

//inportaciones de los modulos 
import obtenerLotes from "./modulo_reportes/obtenerLotes.js";
import consultarLote from "./modulo_reportes/consultarLote.js";
import descargaReporte from "./modulo_reportes/descargaReporte.js";

//se capturan por medio del DOM los botones para poder realizar los eventos
const consultar = document.querySelector("#consultar");
const descarga = document.querySelector("#descarga");

//llamamos la funcion para traer los lotes de la base de datos
obtenerLotes();

//evento para cuando se de clic al boton muestre en el selet los lotes
//seun corresponda su estado (activo/inactivo)
consultar.addEventListener("click",  () => { consultarLote() });
//evento para cuando se de clic en el boton de descargar llame la funcion 
//descargaReporte donde se captura la informacion y genera el archivo pdf
descarga.addEventListener('click', () => { descargaReporte()});


