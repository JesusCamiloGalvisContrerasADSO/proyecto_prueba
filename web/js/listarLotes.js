/**
 * Funcion donde se obtiene el json de los lotes que estan en la base de datos
 * posteriormente convertirlo en un objeto de javaScript y poder pasarlo a la funcion
 * @llenarLotes 
 * @param {*} lotes
 * 
 */

//importacion
import llenarLotes from "./modulo_lotes/llenarLotes.js";

// array funtion asincrona 
const  obtenerLotes = async () => {
    //se realiza la peticion fech, se utiliza await para que el codigo espere
    //y se le asigna a una variable 
    const respuesta = await fetch("controlLote?accion=listarLotes");

    //verificamos que la respuesta sea 200
    if (!respuesta.ok) {
        throw new Error('Error en la solicitud: ' + respuesta.status);
    }

    //convertimos el json recibido a un objeto js para trabajar con el
    const lotes = await respuesta.json();

    // llamamos la funcion llenar lotes y le pasamos el objeto de los lotes
    llenarLotes(lotes);
}

document.addEventListener("DOMContentLoaded", function() {
    obtenerLotes();
});
//llamamos la funcion anterior para que se ejecute 

// document.getElementById("btn-mover-papelera").addEventListener("click", function (event)  {
//     envioPape(event);
//  })