/**
 * Funcion para poder eliminar uno o varios lotes de la papelera
 * en el capturamos el boton y le asignamos el evento click,
 * capturamos todos los inputs seleccionados y los volvemos un arreglo,
 * el usuario confirma la eliminacion y se realiza la peticion 
 * fecht con el metodo delete para poder eliminar todos los lotes seleccionados, 
 * si se presenta algun error se mostraran las alertas al usuario
 * 
 * @param {*} numerosLotes arreglo con los numeros de los lotes
 */

//importaciones de alertas
import advertir from "../acciones/advertir.js";
import error from "../acciones/Error.js";


 export default async function eliminarLote() {
    // Obtener todos los checkboxes seleccionados
    //primero se capturan todos los checbox con nombre selectedLotes que esten chequiados
    const seleccionado = document.querySelectorAll('input[name="selectedLotes"]:checked');
    //Array.form combierte un arreglo, luego con map capturamos solo el valor
    //de los checbox y los asignamos a numerosLotes
    const numerosLotes = Array.from(seleccionado).map(checkbox => checkbox.value);

    //verificamos que el arreglo no este vacio, si no llamamos la alerta advertir
    if (numerosLotes.length === 0) {
        advertir("Debes seleccionar almenos un lote para continuar");
        return;
    }

    // Mostrar la alerta de confirmación con SweetAlert
    const confirmar = await Swal.fire({
        title: "¿Estás seguro de eliminar?",
        text: "¡Después de esta acción no podrás recuperar la información!",
        icon: "warning",
        showCancelButton: true,
        confirmButtonColor: "#3085d6",
        cancelButtonColor: "#d33",
        confirmButtonText: "Sí, eliminar",
        cancelButtonText: "Cancelar"
    });

    // Si el usuario confirma, proceder con la eliminación
    //con isConfirmed verificamos que fue true la respuesta
    if (confirmar.isConfirmed) {
        // Realizar la solicitud DELETE
        const response = await fetch('ControlPapeLote', {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ ids: numerosLotes })
        });

        //convertimos a un objeto js la respuesta del fech
        const data = await response.json();
        //verificamos que sea true
        if (data.success) {
            // Mostrar alerta de éxito
            await Swal.fire({
                title: "¡Eliminado!",
                text: "Tus datos han sido eliminados.",
                icon: "success"
            });

            // Recargar la página 
            window.location.reload();
        } else {
            // Mostrar alerta de error
            error("Error al eliminar los lotes: " + data.message)
        }
        
    }
};
