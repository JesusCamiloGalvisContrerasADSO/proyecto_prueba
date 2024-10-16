/**
 * funcion de swit alet donde muestra un mensaje de alerta de 
 * error 
 * se retorna la promesa para poder utilizar el .then 
 * @returns 
 */

export default function error(mensaje){
    return Swal.fire({
        icon: "error",
        title: "Oops...",
        text: mensaje,
        footer: 'Vuelve a intentarlo'
    });
}