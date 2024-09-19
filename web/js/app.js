import contrasena from "./modulos/modulo_contrasena.js";
import is_valid from "./modulos/is_valid.js";
// import validar from "./modulos/modulo_validar.js";
import remover from "./modulos/moduloRemover.js";
import correo  from "./modulos/modulo_correo.js";
import letras from "./modulos/modulo_letras.js";
import validarContrasena from "./modulos/modulo_contrasena.js";
import validarSelec from "./modulos/modulo_Box.js";
import { validarFormulario } from "./modulos/modulo_contrasena.js";
import numero from "./modulos/modulo_numero.js";
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

if(documento){
documento.addEventListener("keyup", () => {
    remover(documento);
});

documento.addEventListener("keypress",(event) => numero(event, documento));
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

if(documento){
// Agregar el event listener para la validación al enviar el formulario
$formulario.addEventListener('submit', (event) => {
    validarFormularioNumero(event, documento);
});
}

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