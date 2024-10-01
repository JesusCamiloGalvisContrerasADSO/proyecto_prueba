
package modelo;

//la clase loteM representa los atributos que se encuentran presentes en la base de datos
//se declaran como privados y se crean los metodos getters y setters para poder acceder a ellos 
//poder consultar o enviar informacion atravez de ellos

//Getter
//Definición: Un método getter es un método que se utiliza para obtener el valor de 
//un atributo privado de una clase.

//Setter
//Definición: Un método setter es un método que se utiliza para establecer o modificar
//el valor de un atributo privado de una clase.

public class LoteM {
    // Atributos de la clase
    private int id;
    private int num;
    private int est;
    private int cantidad;

    // Constructor vacío
    public LoteM() {
    }

    public LoteM(int id, int num, int est, int cantidad) {
        this.id = id;
        this.num = num;
        this.est = est;
        this.cantidad = cantidad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getEst() {
        return est;
    }

    public void setEst(int est) {
        this.est = est;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    
    
}
