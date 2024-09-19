// Función para validar la contraseña en el evento de pulsación de tecla
export default function validarContrasena(event, elemento) {
    // Expresión regular que permite letras, números y algunos caracteres especiales, con una longitud entre 1 y 48 caracteres
    let contrasenaRegex = /^[A-Za-z0-9!@#$%^&*()_+]{1,48}$/;

    // Concatenar el valor actual del elemento con la nueva tecla presionada
    let valorCompleto = elemento.value + event.key;

    // Validar si el valor completo cumple con la expresión regular
    if (!contrasenaRegex.test(valorCompleto)) {
        event.preventDefault(); // Evitar que se agregue el carácter si no cumple con la expresión regular
    }
}

// Función para validar la contraseña al enviar el formulario
export function validarFormulario(event, inputContrasena, inputConfirContrasena) {
    // Longitud mínima requerida
    const longitudMinima = 8;

    // Obtener los valores de los inputs de contraseña
    const valorContrasena = inputContrasena.value;
    const valorConfirContrasena = inputConfirContrasena.value;

    let esValido = true;

    // Validar la longitud mínima para la contraseña
    if (valorContrasena.length < longitudMinima) {
        inputContrasena.style.borderColor = 'red';
        // Mostrar un mensaje de error (opcional)
        // document.getElementById('errorContrasena').innerText = 'La contraseña debe tener al menos 8 caracteres';
        esValido = false;
    } else {
        inputContrasena.style.borderColor = 'green';
    }

    // Validar que las contraseñas coincidan
    if (valorContrasena !== valorConfirContrasena) {
        inputConfirContrasena.style.borderColor = 'red';
        // Mostrar un mensaje de error (opcional)
        // document.getElementById('errorConfirContrasena').innerText = 'Las contraseñas no coinciden';
        esValido = false;
    } else {
        inputConfirContrasena.style.borderColor = 'green';
    }

    // Evitar el envío del formulario si alguna validación falla
    if (!esValido) {
        event.preventDefault();
    }
}

