
package modelo;

import java.util.Date;

//la clase pesos representa los atributos que se encuentran presentes en la base de datos
//se declaran como privados y se crean los metodos getters y setters para poder acceder a ellos 
//poder consultar o enviar informacion atravez de ellos

//Getter
//Definición: Un método getter es un método que se utiliza para obtener el valor de 
//un atributo privado de una clase.

//Setter
//Definición: Un método setter es un método que se utiliza para establecer o modificar
//el valor de un atributo privado de una clase.

public class Pesos {
    
    private int idPesos;
    private float peso;
    private Date fechaPeso;
    private int animal_id;
    private String descripcion;

    public Pesos() {
    }

    public Pesos(int idPesos, float peso, Date fechaPeso, int animal_id, String descripcion) {
        this.idPesos = idPesos;
        this.peso = peso;
        this.fechaPeso = fechaPeso;
        this.animal_id = animal_id;
        this.descripcion = descripcion;
    }

    public int getIdPesos() {
        return idPesos;
    }

    public void setIdPesos(int idPesos) {
        this.idPesos = idPesos;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public Date getFechaPeso() {
        return fechaPeso;
    }

    public void setFechaPeso(Date fechaPeso) {
        this.fechaPeso = fechaPeso;
    }

    public int getAnimal_id() {
        return animal_id;
    }

    public void setAnimal_id(int animal_id) {
        this.animal_id = animal_id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    
}
