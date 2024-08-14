
package modelo;

public class LoteM {
    // Atributos de la clase
    private int id;
    private int num;
    private int est;

    // Constructor vacío
    public LoteM() {
    }

    public LoteM(int id, int num, int est) {
        this.id = id;
        this.num = num;
        this.est = est;
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

    
}
