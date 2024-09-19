
export default function validarSelec (event, elemento) {
     
     if (elemento.value !== "0") {
         elemento.classList.remove("error");
         elemento.classList.add("correcto");
     } else {
         elemento.classList.remove("correcto");
         elemento.classList.add("error");
     }

}
