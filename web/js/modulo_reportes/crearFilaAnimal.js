/**
 * funcion para crear fila de animales, poder llenar el reporte mediante el DOM
 * en esta funcion se crean todas las columnas con los datos del animal y los va agregando
 * al template
 * 
 * objeto con los datos del animal
 * @param {*} animal
 * resultado para determinar si es par la columna a crear, si es par colocarla con un fondo de color
 * diferente mostrar una pequeña diferencia
 * @param {*} esPar 
 */


const template = document.querySelector("#tb_animals");  // Referencia al template

// Función para crear filas de animales
export default function crearFilaAnimal(animal, esPar) {
    // Clonamos el contenido del template
    const clone = document.importNode(template.content, true);
    
    // Rellenamos el contenido de cada celda
    clone.querySelector(".numero").textContent = animal.num;
    clone.querySelector(".raza").textContent = animal.Raza.nombre;
    clone.querySelector(".sexo").textContent = animal.nomTipoSex;
    clone.querySelector(".peso").textContent = animal.pesos.peso + " KG";
    clone.querySelector(".salud").textContent = animal.salud.nombre;
    clone.querySelector(".fechaVenta").textContent = animal.pesos.fechaPeso;
    
    // Alternar color: si la fila es par, añadimos la clase "fondo--celeste"
    if (esPar) {
        clone.querySelector("tr").classList.add("fondo--celeste");
    }
    
    return clone;
}