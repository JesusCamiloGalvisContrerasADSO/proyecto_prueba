/**
 * funcion para restaurar lotes, se modifica su estado de 0 a 1
 * se hace una peticion fecht de metodo PUT donde se envia el id
 * y por medio de este el backend pueda hacer las modificaciones 
 * respectivas, dependiendo la respuesta que llega de la peticion 
 * se mostrara el mensaje de alerta al usuario indicando
 * si el lote fue restaurado correctamente o no
 * @restaurarLotes
 * 
 */


export default function restaurarLotes(){
    //se capturan los enlaces de restaurar despues de que carguen para que no genere
    //conflictos
    const boton = document.querySelectorAll(".btn-restaurar")
    //iteramos el arreglo que genera al capturar los enlaces
    boton.forEach(button => {
        // agregamos el evento individualmente a cada enlace 
        button.addEventListener("click", async (event) => {
            event.preventDefault(); // Evita que el enlace recargue la página
            const loteId = button.getAttribute("data-id"); //capturamos el data-id (id)
            //mensaje para confirmar la restauracion
            const confirmar = await Swal.fire({
                title: "¿Estás seguro de restaurar este lote?",
                text: "Esto hará que el lote vuelva a estar disponible.",
                icon: "warning",
                showCancelButton: true,
                confirmButtonColor: "#3085d6",
                cancelButtonColor: "#d33",
                confirmButtonText: "Sí, restaurar",
                cancelButtonText: "Cancelar"
            });
            
            //verificamos la respuesta anterior
            if (confirmar.isConfirmed) {
                //realizamos la solicitud fech enviando el id
                const response = await fetch('ControlPapeLote', {
                    method: 'PUT',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify({ id: loteId })
                });
    
                //convertimos la respuesta de la peticion a un objeto js
                const data = await response.json();
    
                //verificamos la respuesta del backend y dependiendo mostramos
                //la alerta correspondiente
                if (data.success) {
                    Swal.fire("Restaurado", "El lote ha sido restaurado con éxito.", "success")
                        .then(() => {
                            // Recargar la página para mostrar la lista actualizada
                            window.location.reload();
                        });
                } else {
                    Swal.fire("Error", "No se pudo restaurar el lote: " + data.message, "error");
                }
            
            }
    });
});
}