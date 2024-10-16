/**
 * Funcion donde se obtiene el json de los lotes que estan en la base de datos
 * posteriormente convertirlo en un objeto de javaScript y poder pasarlo a la funcion
 * @llenarLotes 
 * @param {*} lotes
 * 
 */

//importacion
import eliminarLote from "./modulo_lotes/eliminarLote.js";
import llenarLotes from "./modulo_lotes/llenarLotes.js";
import restaurarLotes from "./modulo_lotes/restaurarLotes.js";

// array funtion asincrona 
const  obtenerLotes = async () => {
    //se realiza la peticion fech, se utiliza await para que el codigo espere
    //y se le asigna a una variable 
    const respuesta = await fetch("ControlPapeLote?accion=listarPapeLote");

    //verificamos que la respuesta sea 200
    if (!respuesta.ok) {
        throw new Error('Error en la solicitud: ' + respuesta.status);
    }

    //convertimos el json recibido a un objeto js para trabajar con el
    const lotes = await respuesta.json();

    // llamamos la funcion llenar lotes y le pasamos el objeto de los lotes
    llenarLotes(lotes);
}

//llamamos la funcion anterior para que se ejecute 
document.addEventListener("DOMContentLoaded", async function() {
    await obtenerLotes();
    //esta funcion es para que capture todos los datos de restaurar y poder restaurar 
    //los lotes
    await restaurarLotes();
});


//se captura el boton y se le agrega el evento click llamando a la funcion eliminar lote
//que realiza la funcion fech
document.querySelector("#btnEliminar").addEventListener("click", function(){
    eliminarLote();
});




