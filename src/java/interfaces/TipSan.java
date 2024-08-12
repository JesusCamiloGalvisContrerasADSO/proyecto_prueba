package interfaces;

import java.util.List;
import modelo.TipoSangre;

public interface TipSan {
    public List listar();
    public TipoSangre list(int id);
    public boolean add(TipoSangre Tipo);
    public boolean edit(TipoSangre Tipo);
    public boolean eliminar(int id);
}