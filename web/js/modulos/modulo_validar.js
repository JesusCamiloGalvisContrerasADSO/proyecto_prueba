export default function validar(event){
    event.preventDefault()
    console.log(nombre.value);
    if (nombre.value === "") {
        // alert("el campo no puede estar vacio")
        nombre.focus()
        nombre.classList.add("error")
        // nombre.addEventListener.remove("error")


// keydown -- cuando ecribo tecla por tecla 
// keypress -- cuando la presiono
// keyup -- cuando la oprimo 


    }
    if( apellido.value === ""){
        // alert("el campo no puede estar vacio")
        apellido.focus()
        apellido.classList.add("error")

    }if(tipo_doc.value === "0"){
        // alert("el campo no puede estar vacio")
        tipo_doc.focus()
        tipo_doc.classList.add("error")
    }
    if(direccion.value === ""){
        // alert("el campo no puede estar vacio")
        direccion.focus()
        direccion.classList.add("error")
    }if(telefono.value === ""){
        // alert("el campo no puede estar vacio")
        telefono.focus()
        telefono.classList.add("error")
    }if(documento.value === ""){
        // alert("el campo no puede estar vacio")
        documento.focus()
        documento.classList.add("error")
    }

    if (email.value === "") {
        email.focus();
        email.classList.add("error");
    }
}