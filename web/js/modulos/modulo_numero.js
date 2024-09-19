export default function numero(event, docum) {
    if (event.keyCode < 48 || event.keyCode > 57) {
        
        event.preventDefault(); // Esto evitará que se ingrese el valor
    }

    
    if (docum.value.length < 6) {
        docum.classList.remove("correcto");
        docum.classList.add("error");
    } else if (docum.value.length > 9) {
        event.preventDefault(); // Esto evitará que se ingrese el valor
        docum.classList.remove("correcto");
        docum.classList.add("error");
    } else {
        docum.classList.remove("error");
        docum.classList.add("correcto");
    }
    
}

// Función para validar el formulario al enviar
export function validarFormularioNumero(event, docum) {
    // Validar si el campo "documento" tiene al menos 6 caracteres
    if (docum.value.length < 8) {
        event.preventDefault(); // Evitar el envío del formulario
        docum.classList.add("error");
    }
}


// Función para validar el formulario al enviar
export function validarFormularioTelefono(event, docum) {
    // Validar si el campo "telefono" tiene entre 6 y 10 caracteres
    if (docum.value.length < 6 || docum.value.length > 10) {
        event.preventDefault(); // Evitar el envío del formulario
        docum.classList.add("error");
        docum.style.borderColor = 'red'; // Establecer el borde en rojo si no cumple la condición
    } else {
        docum.classList.remove("error");
        docum.style.borderColor = 'green'; // Establecer el borde en verde si cumple la condición
    }
}
