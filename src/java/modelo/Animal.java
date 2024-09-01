
package modelo;

import java.util.Date;

public class Animal {
    private int id;
    private String num;
    private Date fechaCompra;
    private Raza Raza;
    private int raza_id;
    private int tipo_sexo;
    private String nomTipoSex;
    private LoteM Lote;
    private int lote_id;
    private Pesos pesos;
    private int pesos_id;
    private Salud salud;
    private int salud_id;

    public Animal() {
    }

    public Animal(int id, String num, Date fechaCompra, Raza Raza, int raza_id, int tipo_sexo, String nomTipoSex, LoteM Lote, int lote_id, Pesos pesos, int pesos_id, Salud salud, int salud_id) {
        this.id = id;
        this.num = num;
        this.fechaCompra = fechaCompra;
        this.Raza = Raza;
        this.raza_id = raza_id;
        this.tipo_sexo = tipo_sexo;
        this.nomTipoSex = nomTipoSex;
        this.Lote = Lote;
        this.lote_id = lote_id;
        this.pesos = pesos;
        this.pesos_id = pesos_id;
        this.salud = salud;
        this.salud_id = salud_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public Date getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(Date fechaCompra) {
        this.fechaCompra = fechaCompra;
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

    public LoteM getLote() {
        return Lote;
    }

    public void setLote(LoteM Lote) {
        this.Lote = Lote;
    }

    public int getLote_id() {
        return lote_id;
    }

    public void setLote_id(int lote_id) {
        this.lote_id = lote_id;
    }

    public Pesos getPesos() {
        return pesos;
    }

    public void setPesos(Pesos pesos) {
        this.pesos = pesos;
    }

    public int getPesos_id() {
        return pesos_id;
    }

    public void setPesos_id(int pesos_id) {
        this.pesos_id = pesos_id;
    }

    public Salud getSalud() {
        return salud;
    }

    public void setSalud(Salud salud) {
        this.salud = salud;
    }

    public int getSalud_id() {
        return salud_id;
    }

    public void setSalud_id(int salud_id) {
        this.salud_id = salud_id;
    }

    
}
