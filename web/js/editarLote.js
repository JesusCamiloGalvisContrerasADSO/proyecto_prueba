/**
 * modulo para actualizar el lote dependiendo su id
 * principalmente se realiza una consulta get por la funcion 
 * @consultarLote
 * para traer el numero del lote y mostrarlo al usuario
 * 
 * luego cuando el usuario de click al boton captura el valor 
 * del numero y del estado del lote, el id lo captura por la url 
 * y estos datos los convertimos a un objeto js con los mismos 
 * nombres en las variables que en el modelo para que no genere 
 * conflictos al momento de que los use el backend, posteriormente 
 * lo pasamos a la funcion 
 * @actualizarLote 
 * @param {*} lote 
 * con su paramatro lote para poder hacer la siguiente solicitud fech tipo put
 * al backend y poder actualizar el numero del lote
 */



//importaciones de la funcion
import actualizarLote from "./modulo_lotes/actualizarLote.js";
import consultarLote from "./modulo_lotes/consultarLote.js";

// Obtener los parámetros de la URL
const params = new URLSearchParams(window.location.search);

// Obtener el valor del parámetro 'id'
const id = params.get("id");

//se hace que carge a la vez que el html
document.addEventListener("DOMContentLoaded", function() {
    //funcion donde se consultan los datos de lote
    consultarLote();
});

//se captura el boton y se le asigna que al dar click realice la funcion
document.querySelector("#btnActualizar").addEventListener("click", function() {
    // Obtener los valores del formulario
    const est = document.querySelector("#txtEst").value;
    const num = document.querySelector("#txtNum").value;
    // Crear el objeto que se enviará, deben ser las mismas variables del modelo
    const lote = {
        id: id,
        est: est,
        num: num
    };
    
    //funcion para realizar la peticion fech y actualizar el numero del lote
    actualizarLote(lote);

});

