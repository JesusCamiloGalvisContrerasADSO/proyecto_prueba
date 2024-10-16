/**
 * esto es una funcion de swit aletr, que con una libreria de la mano pueda 
 * mostrar las alertas de una manera mas interactiva con el usuario
 */


(function () {
    Swal.fire({
        icon: "error",
        title: "Oops...",
        text: "Ocurrió un error al procesar tu información",
        footer: 'Vuelve a intentarlo'
    });
})();

