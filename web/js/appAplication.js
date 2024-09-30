import removerBorde from "./modulosAplication/removerBorders.js";
import numero from "./modulos/modulo_numero.js";
import validarSelec from "./modulos/modulo_Box.js";


const $formulario = document.querySelector("#registro");
const num = document.querySelector("#num");
const letra = document.querySelector("#letra");
const raza = document.querySelector("#Raza");
const sexo = document.querySelector("#sexo");
const salud = document.querySelector("#salud");
const pesos = document.querySelectorAll(".peso");

if(num){

    num.addEventListener("input", () => {
        num.value = num.value.toUpperCase();
    });

    num.addEventListener("keyup", () => {
        removerBorde(num);
    });

    num.addEventListener("keypress", (event) => removerBorde(event, num));


}

if(raza){
raza.addEventListener("keyup", () => {
    removerBorde(raza);
});

raza.addEventListener("change", (event) => {
    validarSelec(event, raza);
});
}

if(sexo){
sexo.addEventListener("keyup", () => {
    removerBorde(sexo);
});

sexo.addEventListener("change", (event) => {
    validarSelec(event, sexo);
});
}

if(salud){
salud.addEventListener("keyup", () => {
    removerBorde(salud);
});

salud.addEventListener("change", (event) => {
    validarSelec(event, salud);
});
}

if(pesos){
pesos.forEach((peso) => {
    peso.addEventListener("keyup", () => {
        removerBorde(null, peso); // null en 'event' si no lo necesitas aquí
    });

    peso.addEventListener("keypress", (event) => {
        removerBorde(event, peso);
    });
});
}