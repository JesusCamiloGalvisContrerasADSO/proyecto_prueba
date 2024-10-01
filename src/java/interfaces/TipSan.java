package interfaces;

import java.util.List;
import modelo.TipoSangre;

//estos son los metodos que se van a trabajar en el dao referente a su modelo
//a interfaces se refiere a los metodos los cuales van a enviar las consultas 
//a la base de datos

public interface TipSan {
    public List listar();
    public TipoSangre list(int id);
    public boolean add(TipoSangre Tipo);
    public boolean edit(TipoSangre Tipo);
    public boolean eliminar(int id);
}