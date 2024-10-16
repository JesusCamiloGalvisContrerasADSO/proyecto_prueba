export default function advertir(mensaje){
    Swal.fire({
        title: "Error",
        text: mensaje,
        icon: "question"
        });
}