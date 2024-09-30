export default function removerBorde(event, docum) {
    // if (event.keyCode < 48 || event.keyCode > 57) {
    //     event.preventDefault(); // Esto evitará que se ingrese el valor
    // }

    if (docum.value.length < 6) {
        docum.value = docum.value.toUpperCase();
        docum.classList.remove("error");
        docum.classList.add("correcto");
    } else if (docum.value.length > 3) {
        docum.value = docum.value.toUpperCase();
        event.preventDefault(); // Esto evitará que se ingrese el valor
        docum.classList.remove("correcto");
        docum.classList.add("error");
    } else {
        docum.value = docum.value.toUpperCase();
        docum.classList.remove("error");
        docum.classList.add("correcto");
    }
};


