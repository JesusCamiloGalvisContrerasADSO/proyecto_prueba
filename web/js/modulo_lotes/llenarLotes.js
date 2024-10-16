/**
 * funcion para llenar el frontend con los datos de los lotes que estan 
 * en la base de datos
 * en ella se recibe como argumento el objeto de los lotes y luego iteramos 
 * el objeto con un forEach llamando la funcion 
 * @crearFilaLote y pasando como parametro al objeto del lote
 * 
 * funcion y sus lotes como argumentos
 * @llenarLotes
 * @param {*} lotes
 */

//importacion de la funcion crearFilaLote
import crearFilaLote from "./crearFilaLote.js";

//funcion exportada como default para poder enviarla
export default function llenarLotes(lotes){

    const fragmento = document.createDocumentFragment();  // Crear el fragmento

    //iteramos el objeto que recibimos como argumento
    lotes.forEach(lote => {
        //pasamos el objeto lote como argumento que nos da la iteracion
        //el resultado de la funcion crearFilaLote lo asignamos a la constante fila
        const fila = crearFilaLote(lote)
        // agregamos como hijo al fragmento la constante fila
        fragmento.appendChild(fila);
    });
    //seleccionamos el div al cual queremos agregar
    const div = document.querySelector(".cajaLotes");
    //le agregamos el fragmento al contenedor seleccionado
    div.appendChild(fragmento);
}