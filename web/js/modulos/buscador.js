
document.addEventListener("DOMContentLoaded", function () {
    // Obtener el input de búsqueda y la tabla
    const inputBusqueda = document.querySelector(".opciones__buscador--input");
    const tabla = document.querySelector(".datos__animal tbody");

    // Añadir un evento de escucha para cuando el usuario escribe en el campo de búsqueda
    inputBusqueda.addEventListener("keyup", function () {
        const filtro = inputBusqueda.value.toLowerCase(); // Convertir el texto del input a minúsculas para comparar sin distinción de mayúsculas/minúsculas
        const filas = tabla.getElementsByTagName("tr"); // Obtener todas las filas de la tabla

        // Iterar sobre las filas de la tabla
        for (let i = 0; i < filas.length; i++) {
            let mostrarFila = false;
            const celdas = filas[i].getElementsByTagName("td"); // Obtener todas las celdas de la fila

            // Iterar sobre cada celda de la fila
            for (let j = 0; j < celdas.length; j++) {
                const textoCelda = celdas[j].textContent || celdas[j].innerText;
                
                // Si el texto de la celda coincide con el filtro, mostrar la fila
                if (textoCelda.toLowerCase().indexOf(filtro) > -1) {
                    mostrarFila = true;
                    break;
                }
            }

            // Mostrar u ocultar la fila basado en la coincidencia de búsqueda
            filas[i].style.display = mostrarFila ? "" : "none";
        }
    });
});
