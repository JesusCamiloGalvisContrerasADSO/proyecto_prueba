import contrasena from "./modulos/modulo_contrasena.js";
import is_valid from "./modulos/is_valid.js";
// import validar from "./modulos/modulo_validar.js";
import remover from "./modulos/moduloRemover.js";
import correo  from "./modulos/modulo_correo.js";
import letras from "./modulos/modulo_letras.js";
import numero from "./modulos/modulo_numero.js";
import validarContrasena from "./modulos/modulo_contrasena.js";
import validarSelec from "./modulos/modulo_Box.js";
import { validarFormulario } from "./modulos/modulo_contrasena.js";
import { validarFormularioNumero, validarFormularioTelefono} from "./modulos/modulo_numero.js";


const $formulario = document.querySelector("#registro");

const nombre = document.querySelector("#nombre");
const apellido = document.querySelector("#apellido");
const telefono = document.querySelector("#telefono");
const tipo_Doc = document.querySelector("#tipDoc");
const tipo_San = document.querySelector("#tipSan");
const documento = document.querySelector("#numDoc");
const contra = document.querySelector("#contra");
const confirContra = document.querySelector("#contraConfir");
const email = document.querySelector("#correo");
const politicas = document.querySelector("#politicas");
const enviar = document.querySelector("#enviar");
const tbody = document.querySelector("tbody");



const validar = (event) =>{
     // Llamar a is_valid y obtener el resultado
    const esValido = is_valid(event, '#registro [required]');

    // Solo evitar el envío si la validación falla
    if (!esValido) {
        event.preventDefault(); // Evitar el envío del formulario si hay errores
    }
};

$formulario.addEventListener("submit",validar) ;

if(politicas){
enviar.setAttribute('disabled', '');

addEventListener("DOMContentLoaded", (event) => {

    politicas.addEventListener("change", () => {
    if(politicas.checked ){
        enviar.removeAttribute("disabled","");
    }else{
        enviar.setAttribute("disabled","");
    }
});
});
}


if(nombre){
nombre.addEventListener("keyup", () => {
    remover(nombre);
});



apellido.addEventListener("keyup", () => {
    remover(apellido);
});
}

if(telefono){
telefono.addEventListener("keyup", () => {
    remover(telefono);
});
}

// function numero(event, docum) {
//     if (event.keyCode < 48 || event.keyCode > 57) {
        
//         event.preventDefault(); // Esto evitará que se ingrese el valor
//     }

    
//     if (docum.value.length < 6) {
//         docum.classList.remove("correcto");
//         docum.classList.add("error");
//     } else if (docum.value.length > 9) {
//         event.preventDefault(); // Esto evitará que se ingrese el valor
//         docum.classList.remove("correcto");
//         docum.classList.add("error");
//     } else {
//         docum.classList.remove("error");
//         docum.classList.add("correcto");
//     }
    
// }


if (documento) {
    documento.addEventListener("keyup", () => {
        remover(documento);  // Asegúrate de que la función remover esté definida
    });

    documento.addEventListener("keypress", (event) => numero(event, documento));  // Usa 'keydown' o 'keypress' según la necesidad
} else {
    console.error("El elemento 'documento' no se encontró en el DOM");
}

if(telefono){
telefono.addEventListener("keypress", (event) => numero(event, telefono));
}

if(nombre){
nombre.addEventListener("keypress", (event)=>{
    letras(event, nombre);
});
apellido.addEventListener("keypress", (event)=>{
    letras(event, apellido);
});
}

if(email){
email.addEventListener('input', (event) => {
    correo(event, email);
});
}

if(contra){
// Agregar los event listeners para las validaciones en el input
contra.addEventListener('keypress', (event) => {
    validarContrasena(event, contra);
});
}

if(confirContra){
confirContra.addEventListener('keypress', (event) => {
    validarContrasena(event, confirContra);
});

// Agregar el event listener para la validación del formulario
$formulario.addEventListener('submit', (event) => {
    validarFormulario(event, contra, confirContra);
});
}

if (email) {
    $formulario.addEventListener('submit', (event) => {
        correo(event, email);
    });
}

// if(documento){
// // Agregar el event listener para la validación al enviar el formulario
// $formulario.addEventListener('submit', (event) => {
//     validarFormularioNumero(event, documento);
// });
// }

if(telefono){
// Agregar el event listener para la validación al enviar el formulario
$formulario.addEventListener('submit', (event) => {
    validarFormularioTelefono(event, telefono);
});
}

if(tipo_Doc){
tipo_Doc.addEventListener("change", (event) => {
    validarSelec(event, tipo_Doc);
});
}

if(tipo_San){
tipo_San.addEventListener("change", (event) => {
    validarSelec(event, tipo_San);
});
}