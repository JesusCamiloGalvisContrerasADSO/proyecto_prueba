/**
 * funcion para retornar la fecha actual, dia/mes/año
 * en esta funcion instanciamos el objeto @DATE para posteriormente 
 * utilizar los metodos que tiene este objeto, de esa manera poder obtener la 
 * fecha actual
 * luego se retorna
 * @returns 
 *despues exportamos la funcion ya que se uso un array funtion
 */

const fecha = () => {
    //se instancia el objeto date y se le asigna a una variable
    let date = new Date();
    //se crea una variable para almacenar el dia, despues se utilizan los metodos para poder trear el dia actual
    // se hace lo mismo con mes y año
    let day = date.getDate().toString().padStart(2, '0');  // Asegurar que el día tenga dos dígitos
    let mes = (date.getMonth() + 1).toString().padStart(2, '0');  // Asegurar que el mes tenga dos dígitos
    let ano = date.getFullYear();
    
    //almacenamos en la variable fechaCompleta 
    let fechaCompleta = day + "/" + mes + "/" + ano
    
    //retornamos la fecha
    return fechaCompleta;
}

//exportamos la funcion
export default fecha