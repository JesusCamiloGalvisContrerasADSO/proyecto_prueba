/**
 * Funcion para capturar los valores del lote segun su id
 * principalmemte se debe capturar el id que se pasa por la url 
 * para cuando se realice la peticion fech metodo GET se le pueda pasar el 
 * valor del id segun corresponda
 * Esta peticion nos retornara un objeto en el cual podemos acceder para 
 * obtener los valores y mostrar el numero del lote al usuario para que 
 * este pueda modificarlo
 * 
 * @consultarLote
 */

//importacion de la alerta
import error from "../acciones/Error.js";

// Obtener los parámetros de la URL
const params = new URLSearchParams(window.location.search);

// Obtener el valor del parámetro 'id'
const id = params.get("id");

export default async function consultarLote(){
            
    // Realizar la solicitud `GET` con `fetch` para obtener los datos del lote
    const respuesta = await fetch('controlLote?accion=listarLote&id='+id);

    //verificamos que la respuesta fuera 200
    if (!respuesta.ok) {
        throw new Error("Error en la solicitud GET");
    }

    //convertimos la respuesta fech a un objeto js
    const lote = await respuesta.json();
    
    //verificamos que si existan datos en el lote
    if (lote) {
        // Llenar los campos del formulario con los datos recibidos
        document.querySelector("#txtNum").value = lote.num;
        document.querySelector("#txtEst").value = lote.est;
        // Aquí puedes llenar otros campos si los hay
    } else {
        error("No se encontraron datos del lote.");
    }
}