
package modelo;

import java.util.Date;


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
