export default function correo(event, elemento){
    
    let exprecion = /^[\w._-]+@[\w._-]+\.([a-zA-Z]{2,3}){1,2}$/
    if (exprecion.test(elemento.value)) {
        elemento.classList.remove("error");
        elemento.classList.add("correcto");
    } else {
        elemento.classList.remove("correcto");
        elemento.classList.add("error");
    }
}
