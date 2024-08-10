package interfaces;

import java.util.List;
import modelo.TipoDocum;

public interface TipoDoc {
    public List listar();
    public TipoDocum list(int id);
    public boolean add(TipoDocum Tipo);
    public boolean edit(TipoDocum Tipo);
    public boolean eliminar(int id);
}