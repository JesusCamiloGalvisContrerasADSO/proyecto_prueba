/**
 * funcion para llenar el select con las opcines de lotes segun corrsponda el estado
 * @llenarSelectLotes
 * se reciben dos argumentos (lotes, estado) para la funcion llenarSelectLotes
 * objeto con todos los lotes que estan en la base de datos (lotes, no animales)
 * @param {*} lotes 
 * estado (activo / inactivo) para luego segun su estado mostrar los lotes
 * @param {*} estado 
 * 
 * en esta funcion se crean las opciones del select en un fragmento, 
 * se itera el objeto @lotes que se paso como argumento, con cada iteracion
 * se crea una nueva opcion.
 * luego este fragmento se le pase al frontend y pueda llenar el 
 * html con la informacion que se encuentra en la base de datos por medio del DOM
 */

// Función para llenar el <select> con los lotes obtenidos según el estado
export default function llenarSelectLotes(lotes, estado) {

    const selectLote = document.querySelector('#lote');  // Referencia al select
    const fragmento = document.createDocumentFragment(); // Crear un fragmento para optimizar la inserción en el DOM
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