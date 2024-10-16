/**
 * en esta funcion asignamos los contenidos que van a tener los contenedores
 * y las rutas que van a enviar los enlaces
 * @crearFilaLote
 * @param {*} lote
 * 
 * se recibe como argumento el objeto lote con el cual se va a trabajar
 * en este se seleccionan los contenedores que se van a utilizar referentes del 
 * fragmento creado en el html y se le van agregando los valores
 * segun se necesite para mostrar al usuario, todo esto se asigna al 
 * nodo clone el cual se va a retornar y que se pueda utilizar al asignarlo al 
 * fragmento
 */


const template = document.querySelector("#tb_lotes");  // Referencia al template

 export default function crearFilaLote(lote){
    // Crear un clon del contenido del template
    const clone = document.importNode(template.content, true);

    // Verificar si el elemento ".seleccion" existe antes de asignar su valor
    const seleccion = clone.querySelector(".seleccion");
    if (seleccion) {
        seleccion.value = lote.id;
    }
    // Configurar el enlace del lote con el número y la URL adecuada
    const enlace = clone.querySelector(".numLote");
    enlace.textContent = "Lote " + lote.num;
    enlace.href = "ControlAnimal?accion=listar&id=" + lote.id + "&num=" + lote.num;
    
    // Asignar la cantidad de animales
    clone.querySelector(".cantidadAnimales").textContent = "Cantidad: " + lote.cantidad;

    // Configurar el enlace de edición del lote
    // Verificar si el enlace de edición existe antes de asignar la URL
    const editarLote = clone.querySelector(".editarLote");
    
    if (editarLote) {
        editarLote.href = "controlLote?accion=editar&id=" + lote.id;
    }

    const RestaurarLote = clone.querySelector(".restaurarLote");
    
    if (RestaurarLote) {
        // RestaurarLote.href = "ControlPapeLote?accion=cambiarVerdad&id=" + lote.id;
        RestaurarLote.setAttribute("data-id", lote.id);
    }

    // Retornar el clon modificado
    return clone;
}
