/**
 * Se captura el formulario, se le asigna un evento submit y se coloca
 * en preventDefault para que al dar clic al boton no se vaya a recargar la 
 * pagina ni enviar el formulario
 * 
 * en esta funcion se capturan los datos ingresados por el usuario 
 * y se realiza la peticion fech tipo post para poder enviar los datos del
 * formulario al dar clic al boton de agregar, despues se responde a la 
 * promesa ya que a su vez de enviar la informacion capturada recibe si fue correcta
 * la insercion de datos a la base de datos o retorno false
 * 
 * por ende se puede utilizar .then para convertir el json recibido a 
 * un objeto y poder mostrar mensajes de alerta al usuario para que entienda 
 * si fue correcto el envio de la informacion o hubo un error al enviar este 
 * 
 */

//importaciones de las alertas
import guardado from "./acciones/Correcto.js";
import error from "./acciones/Error.js";

//capturamos el formulario y le agregamos el evento submit
document.querySelector("#registro").addEventListener("submit", function(event) {
    event.preventDefault(); // Evitar el envío tradicional del formulario

    //capturamos el valor que el usuario acabo de ingresar
    const numLote = document.querySelector("#numDoc").value;

    // Crear el objeto lote para enviar al servidor
    const lote = {
        num: numLote,
        accion: "Agregar" // La acción que queremos realizar
    };

    // Realizar la solicitud con fetch
    fetch("controlLote?accion=Agregar", { // Cambia la ruta según tu servlet mapeado
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(lote) // Enviar los datos como JSON
    })
    //convertimos los datos recibitos a un objeto js, este nos muestra error o 
    //que todo esta bien solicitud 200
    .then(response => response.json())
    .then(data => {
        //hacemos la condicional para mostrar si fue correcto o se presento un error
        if (data.message) {
            //llamamos la funcion de guardado
            guardado("El lote fue guardado con éxito").then(() => {
                // Esto se ejecutará después de que la alerta se cierre
                window.location.href = "/JSP__proyecto7/controlLote?accion=listar";
            });
        } else {
            //llamamos la funcion de error
            error("Error al guardar el lote");
        }
    })
    //en caso de error mosrar alerta con el error presentado
    .catch(error => {
        alert("Error en la solicitud: " + error.message);
    });
});
