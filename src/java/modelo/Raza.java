
package modelo;

//la clase Raza representa los atributos que se encuentran presentes en la base de datos
//se declaran como privados y se crean los metodos getters y setters para poder acceder a ellos 
//poder consultar o enviar informacion atravez de ellos

//Getter
//Definición: Un método getter es un método que se utiliza para obtener el valor de 
//un atributo privado de una clase.

//Setter
//Definición: Un método setter es un método que se utiliza para establecer o modificar
//el valor de un atributo privado de una clase.


public class Raza {
    
    private int id ;
    private String nombre;

    public Raza() {
    }

    public Raza(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
}
