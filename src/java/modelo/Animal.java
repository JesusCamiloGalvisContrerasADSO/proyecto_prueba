
package modelo;


public class Animal {
    private int id;
    private int lote_id;
    private int num;
    Raza Raza;
    private int raza_id;
    private int tipo_sexo;
    private String nomTipoSex;

    public Animal() {
    }

    public Animal(int id, int lote_id, int num, Raza Raza, int raza_id, int tipo_sexo, String nomTipoSex) {
        this.id = id;
        this.lote_id = lote_id;
        this.num = num;
        this.Raza = Raza;
        this.raza_id = raza_id;
        this.tipo_sexo = tipo_sexo;
        this.nomTipoSex = nomTipoSex;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLote_id() {
        return lote_id;
    }

    public void setLote_id(int lote_id) {
        this.lote_id = lote_id;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public Raza getRaza() {
        return Raza;
    }

    public void setRaza(Raza Raza) {
        this.Raza = Raza;
    }

    public int getRaza_id() {
        return raza_id;
    }

    public void setRaza_id(int raza_id) {
        this.raza_id = raza_id;
    }

    public int getTipo_sexo() {
        return tipo_sexo;
    }

    public void setTipo_sexo(int tipo_sexo) {
        this.tipo_sexo = tipo_sexo;
    }

    public String getNomTipoSex() {
        return nomTipoSex;
    }

    public void setNomTipoSex(String nomTipoSex) {
        this.nomTipoSex = nomTipoSex;
    }
    
    
    
}
