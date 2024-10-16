/**
 * En este archivo permite enviar unoo mas lotes a papelera, 
 * se realiza una peticion fecht con el metodo PUT en la cual se
 * pasan los lotes que se desean modificar
 * 
 * se captura el boton y se le agrega un evento click, el cual va 
 * a capturar todos los inputs seleccionados y lo volvera en un arreglo
 * @param {*} selectedLotes el cual solo tendra numeros
 * se realiza la confirmacion al usuario y dependiendo de esta se hace la 
 * peticion fech con el metodo PUT para poder modificar el estado 
 * y enviar los lotes a papelera
 * esta solicitud nos dara una respuesta json, la convertimos a bjeto y 
 * dependiendo de la respuesta se muestra al usuario si fue correcta la
 * modificacion o no 
 * @response peticion fetch
 */

//importacion de alerta 
import advertir from "./acciones/advertir.js";

//se captura el boton y se agregan los eventos
document.querySelector("#btn-mover-papelera").addEventListener("click", async (event) => {
    //se coloca en preventDefault para que no se envie ni se recargue la pagina
    event.preventDefault();

    // Obtener todos los checkboxes seleccionados, convertimos a un arreglo 
    //los inpust seleccionados
    const selectedCheckboxes = document.querySelectorAll(".checkbox.seleccion:checked");
    const selectedLotes = Array.from(selectedCheckboxes).map(checkbox => checkbox.value);

    //verificamos que haya seleccionado algun lote
    if (selectedLotes.length === 0) {
        advertir("Debes seleccionar almenos un lote para continuar");
        return;
    }

    //preguntamos si esta seguro de realizar esta accion
    console.log(selectedLotes)
    const confirmar = await Swal.fire({
        title: "¿Estás seguro de enviar los lotes a la papelera?",
        text: "Esto moverá los lotes seleccionados a la papelera.",
        icon: "warning",
        showCancelButton: true,
        confirmButtonColor: "#3085d6",
        cancelButtonColor: "#d33",
        confirmButtonText: "Sí, mover",
        cancelButtonText: "Cancelar"
    });

    //validamos la respuesta anterior
    if (confirmar.isConfirmed) {
            //realizamos la peticion fetch con metodo put
            const response = await fetch('controlLote?accion=cambiarFalse', {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({ selectedLotes }) //se pasa el array
            });
            //convertimos el json a un objeto js
            const data = await response.json();
            
            //verificamos el objeto, si fue correcta o no la solicitud 
            //y mostramos al usuario el resultado
            if (data.success) {
                Swal.fire("Hecho", "Los lotes han sido enviados a la papelera.", "success")
                    .then(() => {
                        // Recargar la página para mostrar la lista actualizada
                        window.location.reload();
                    });
            } else {
                Swal.fire("Error", "No se pudo enviar los lotes a la papelera: " + data.message, "error");
            }
    }
});

        