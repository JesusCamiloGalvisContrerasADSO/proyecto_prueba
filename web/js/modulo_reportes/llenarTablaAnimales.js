/**
 * Funcion para separar animales activos e inactivos y luego iterar sobre ellos
 * creando fragmentos y agregandoselos al html
 * Se usan el metodo filter para filtrar al animal por su estado y luego 
 * iteramos con forEach los dos objetos que separamos del argumento que pasamos (animales)
 * llenando el fragmento con los datos de cada animal
 * 
 * luego pasamos al html el fragmento con los datos asignados para que se llene el frontend con los datos 
 * reales de la base de datos
 * 
 * se reciben dos argumentos (animales, Numlote) para la funcion llenarTablaAnimales
 * objeto con todos los animales que estan en la base de datos 
 * @param {*} animales 
 * Numero de lote referente al animal
 * @param {*} Numlote 
 */

//importaciones
import crearFilaAnimal from "./crearFilaAnimal.js";
import fecha from "./fecha.js";

//variables, id capturados con el DOM
const selectLote = document.querySelector('#lote');  // Referencia al select
const fechaReporte = document.querySelector("#fecha--reporte");
const tbody = document.querySelector(".factura__animales tbody");  // Referencia al cuerpo de la tabla
    
const activos = document.querySelectorAll(".Total");
const sanos = document.querySelector("#sanos");
const enfermos = document.querySelector("#enfermos");
const eliminados = document.querySelector("#eliminados");
const totalAni = document.querySelector("#totalAni");

//funcion llenarTablaAnimales con los argumentos animales (objeto con los animales), Numlote(numero del lote donde estan esos animales)
export default function llenarTablaAnimales(animales, Numlote) {
    
    tbody.innerHTML = '';  // Limpiamos las filas anteriores
    
    const fragmento = document.createDocumentFragment();  // Crear el fragmento
    
    //inicializamos las variables en 0 en caso de que no cuente condatos para llenar
    let totalAnimales = 0;
    let animalEliminado = 0;
    let animalSano = 0;
    let animalEnfermo = 0;
    
    // Separar los animales activos y eliminados
    const animalesActivos = animales.filter(animal => animal.estado !== 0);
    const animalesEliminados = animales.filter(animal => animal.estado === 0);
    
    // Añadir animales activos primero, iteramos el objeto
    animalesActivos.forEach((animal, index) => {
        const fila = crearFilaAnimal(animal, index % 2 === 0);
        fragmento.appendChild(fila);
        totalAnimales += 1;
        if(animal.salud.nombre === "Sano") {
            animalSano += 1;
        } else {
            animalEnfermo += 1;
        }
    });
    
    // Añadir una fila de separación y el título "Eliminados"
    if (animalesEliminados.length > 0) {
        const filaEliminados = document.createElement("tr");
        const celdaEliminados = document.createElement("td");

        celdaEliminados.colSpan = 6;  // Abarca todas las columnas de la tabla
        celdaEliminados.textContent = "Eliminados";
        celdaEliminados.classList.add("fondo-rojo");  
        filaEliminados.appendChild(celdaEliminados);
        fragmento.appendChild(filaEliminados);
    }
    
    // Añadir animales eliminados después, iteramos el objeto
    animalesEliminados.forEach((animal, index) => {
        const fila = crearFilaAnimal(animal, (totalAnimales + index) % 2 === 0);
        fragmento.appendChild(fila);
        totalAnimales += 1;
        animalEliminado += 1;
        if(animal.salud.nombre === "Sano") {
            animalSano += 1;
        } else {
            animalEnfermo += 1;
        }
    });
    
    //calculamos el total de animales activos
    let TotalActivo = totalAnimales - animalEliminado;
    
    // Finalmente, añadimos el fragmento al tbody de la tabla
    tbody.appendChild(fragmento);
    
    // Itera sobre cada elemento de la lista y actualiza su textContent
    activos.forEach(element => {
        element.textContent = TotalActivo;
    });
    
    // agregamos con el DOM las estadisticas que sacamos mientras realizabamos las iteraciones anteriores
    eliminados.textContent = animalEliminado;
    sanos.textContent = animalSano;
    enfermos.textContent = animalEnfermo;
    totalAni.textContent = totalAnimales;
    
    //Agregamos la fecha
    fechaReporte.textContent = fecha();
    
    //Agregamos el numero del lote
    Numlote.textContent = selectLote.options[selectLote.selectedIndex].textContent; // Muestra el texto de la opción seleccionada
    
    }