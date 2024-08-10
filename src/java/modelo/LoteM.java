
package modelo;

public class LoteM {
    // Atributos de la clase
    private int id;
    private int num;

    // Constructor vacío
    public LoteM() {
    }

    // Constructor con parámetros
    public LoteM(int id, int num) {
        this.id = id;
        this.num = num;
    }

    // Métodos getters y setters
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
}
