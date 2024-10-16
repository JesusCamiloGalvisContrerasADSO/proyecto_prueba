/**
 * funcion para actualizar el numero del lote
 * @actualizarLote
 * @param {*} Lote
 * se recibe como argumento el objeto del lote con sus datos modificados
 * (id, num, est) el id, el numero del lote y su estado
 * se realiza una peticion fetch con el metodo PUT para poder modificar
 * el numero del lote, esta peticion nos envia una respuesta, un objeto 
 * donde si se presento un conflicto envia error y si todo esta bien envia 
 * un mensaje diciendo que todo esta correcto, se muestran alertas al
 * usuario indicando como fue el proceso, si todo es correcto se envia de nuevo 
 * a listar lotes con window.location.href = "controlLote?accion=listar"
 */

//importaciones de alertas
import guardado from "../acciones/Correcto.js";
import error from "../acciones/Error.js";

// funcion asincrona con argumento lote (objeto con los datos nuevos del lote)
export default async function actualizarLote(lote) {
    // Realizar la solicitud con fetch metodo PUT
    const respuesta = await fetch("controlLote?accion=actualizar", {
        method: "PUT",
        headers: {
            "Content-Type": "application/json"
        },
        //pasamos el objeto convertido en json 
        body: JSON.stringify(lote)
    })

    //se convierte la respuesta del fech a un objeto js
    const actualizado = await respuesta.json();
    
    //verificamos la respuesta de la solicitud
    if (actualizado.success) {
        // Mostrar una alerta de éxito
        guardado("Lote actualizado con éxito")
        .then(() => {
            // Redirigir a la página de listado después de que la alerta se cierre
            window.location.href = "controlLote?accion=listar";
        });
    } else {
        // Mostrar una alerta de error
        error( "No se pudo actualizar el lote. Verifique los datos.");
    }
}