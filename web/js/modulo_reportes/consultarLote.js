/**
 * modulo es para hacer la peticion fech al backend el cual nos retornara un objeto con 
 * los datos necesarios para poder mostrar esta informacion en el frontend,
 * @consultarLote
 * 
 * esta es una funcion asincrona
 * selecionamos por medio del dom el id del lote y su numero
 * por medio de una peticion fech enviamos el id del lote capturado del dom, el cual nos 
 * traae un archivo tipo .json, lo convertimnos a un archivo javaScript y obtenemos un objeto
 * el cual se lo enviaremos al la funcion @llenarTablaAnimales junto con dos atributos
 */


import llenarTablaAnimales from "./llenarTablaAnimales.js";

export default async function consultarLote() {
    
    //asignamos en constantes el id del lote y el numero de este 
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

        // Llamamos a la función que llena la tabla con los animales obtenidos
        llenarTablaAnimales(animales, Numlote);

    } catch (error) {
        console.error('Error al obtener los animales:', error);
    }
}