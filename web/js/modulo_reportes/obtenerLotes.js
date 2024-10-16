/**
 * funcion para obtener los lotes que estan la base de datos 
 * se realiza la peticion fech al backend, convertimos el
 * archivo json a un objeto JavaScript, luego dependiendo del resultado
 * del estado que desea seleccionar y llama a la funcion @llenarSelectLotes 
 * parandole los parametros 
 * @param {*} lotes que es el objeto con los lotes que acabamos de convertir
 * @param {*} estadoSeleccionado que es el estado que el usuario selecciono en el frontend
 * 
 */

//importaciones
import llenarSelectLotes from "./llenarSelectLotes.js";

// Función para capturar los lotes desde el servidor
export default async function obtenerLotes() {
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