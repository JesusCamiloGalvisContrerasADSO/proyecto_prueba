export default function remover(input, validacion) {
    if (input.value !== "" && input.value.length >= 4) {
        input.classList.add("correcto");
        input.classList.remove("error");
    } else {
        input.classList.remove("correcto");
        input.classList.add("error");
    }
};