
package modelo;

import java.util.Date;


/**
 * La clase Animal representa la entidad de un animal dentro del sistema de gestión ganadera.
 * Este modelo se encarga de mapear los datos de la tabla "animales" en la base de datos,
 * permitiendo la manipulación y gestión de sus atributos dentro de la aplicación.
 *
 * Atributos:
 * - id: Identificador único del animal.
 * - num: Número de identificación del animal.
 * - estado: Estado actual del animal (por ejemplo, activo, inactivo, vendido).
 * - fechaCompra: Fecha en la que el animal fue adquirido.
 * - Raza: Objeto que representa la raza del animal (relación con la clase Raza).
 * - raza_id: Identificador de la raza del animal.
 * - tipo_sexo: Código numérico que representa el sexo del animal (por ejemplo, 1 para macho, 2 para hembra).
 * - nomTipoSex: Nombre descriptivo del tipo de sexo (por ejemplo, "Macho" o "Hembra").
 * - Lote: Objeto que representa el lote al que pertenece el animal (relación con la clase LoteM).
 * - lote_id: Identificador del lote en el que está el animal.
 * - pesos: Objeto que almacena la información de los pesos del animal (relación con la clase Pesos).
 * - pesos_id: Identificador del registro de pesos del animal.
 * - salud: Objeto que almacena la información de salud del animal (relación con la clase Salud).
 * - salud_id: Identificador del registro de salud del animal.
 *
 * Esta clase incluye los métodos para acceder y modificar cada uno de los atributos,
 * así como otras operaciones necesarias para gestionar la información del animal dentro del sistema.
 */

public class Animal {
    private int id;
    private String num;
    private int estado;
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

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
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

    public Animal(int id, String num, int estado, Date fechaCompra, Raza Raza, int raza_id, int tipo_sexo, String nomTipoSex, LoteM Lote, int lote_id, Pesos pesos, int pesos_id, Salud salud, int salud_id) {
        this.id = id;
        this.num = num;
        this.estado = estado;
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

    
}
