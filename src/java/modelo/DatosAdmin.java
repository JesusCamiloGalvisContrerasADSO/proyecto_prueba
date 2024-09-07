
package modelo;

public class DatosAdmin {
    
    private int CantUsu;
    private int CantLotVent;
    private int CantLot;
    private int CantAnim;

    public DatosAdmin() {
    }

    public DatosAdmin(int CantUsu, int CantLotVent, int CantLot, int CantAnim) {
        this.CantUsu = CantUsu;
        this.CantLotVent = CantLotVent;
        this.CantLot = CantLot;
        this.CantAnim = CantAnim;
    }

    public int getCantUsu() {
        return CantUsu;
    }

    public void setCantUsu(int CantUsu) {
        this.CantUsu = CantUsu;
    }

    public int getCantLotVent() {
        return CantLotVent;
    }

    public void setCantLotVent(int CantLotVent) {
        this.CantLotVent = CantLotVent;
    }

    public int getCantLot() {
        return CantLot;
    }

    public void setCantLot(int CantLot) {
        this.CantLot = CantLot;
    }

    public int getCantAnim() {
        return CantAnim;
    }

    public void setCantAnim(int CantAnim) {
        this.CantAnim = CantAnim;
    }
    
    
    
    
}
