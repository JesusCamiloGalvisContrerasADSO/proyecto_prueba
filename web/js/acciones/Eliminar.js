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




  