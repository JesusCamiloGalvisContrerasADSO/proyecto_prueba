export default function letras(event, elemento) {
    // Expresión regular que permite letras, incluyendo acentuadas y espacios, y limita la longitud a un máximo de 10 caracteres
    let letras = /^[A-Za-zÀ-ÿ\s]{1,48}$/;

    // Concatenar el valor actual del elemento con la nueva tecla presionada
    let valorCompleto = elemento.value + event.key;

    // Validar si el valor completo cumple con la expresión regular
    if (!letras.test(valorCompleto)) {
        event.preventDefault(); // Esto evita que se agregue el carácter si no cumple con la expresión regular
    }
}
