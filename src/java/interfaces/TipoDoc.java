package interfaces;

import java.util.List;
import modelo.TipoDocum;

//estos son los metodos que se van a trabajar en el dao referente a su modelo
//a interfaces se refiere a los metodos los cuales van a enviar las consultas 
//a la base de datos
public interface TipoDoc {
    public List listar();
    public TipoDocum list(int id);
    public boolean add(TipoDocum Tipo);
    public boolean edit(TipoDocum Tipo);
    public boolean eliminar(int id);
}