/**
 * esto es una funcion de swit aletr, que con una libreria de la mano pueda 
 * mostrar las alertas de una manera mas interactiva con el usuario
 * se realiza un evento, que cargue el contenido antes de cargar el html
 * despues un forEach para que le asigne la alerta al boton con la clase
 * (".boton--eliminar") y se active al dar click sobre el boton
 */


document.addEventListener("DOMContentLoaded", function () {
    // Selecciona todos los botones con la clase 'boton--eliminar'
    const botones = document.querySelectorAll(".boton--eliminar");

    // Itera sobre cada botón y agrega el event listener
    botones.forEach((boton) => {
        boton.addEventListener("click", (e) => {
            // Evita la acción predeterminada si hay un atributo 'href' en el botón
            e.preventDefault();

            Swal.fire({
                title: "Estas seguro de eliminar?",
                text: "Despues de esta accion no podras recuperar la informacion!",
                icon: "warning",
                showCancelButton: true,
                confirmButtonColor: "#3085d6",
                cancelButtonColor: "#d33" ,
                confirmButtonText: "Si, eliminar!"
            }).then((result) => {
                if (result.isConfirmed) {
                    Swal.fire({
                        title: "Elininado!",
                        text: "Tus datos han sido eliminados.",
                        icon: "success"
                        
                    }).then(()=>{
                        window.location.href = boton.href;
                    })
                    
                    // Aquí podrías agregar código adicional para manejar la eliminación
                }
            });
        });
    });
});




  