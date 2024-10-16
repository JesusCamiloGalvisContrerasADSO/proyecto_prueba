/**
 * funcion de swit alet donde muestra un mensaje de alerta de 
 * guardado
 * se retorna la promesa para poder utilizar el .then 
 * @returns 
 */

export default function guardado(mensaje){
    return Swal.fire({
        position: "top-end",
        icon: "success",
        title: mensaje,
        showConfirmButton: false,
        timer: 1500
    })
}