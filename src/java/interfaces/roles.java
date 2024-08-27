package interfaces;

import java.util.List;
import modelo.Roles;

public interface roles {
    // Método para listar todos los lotes
    public List<Roles> listar();
    
    // Método para listar un lote por su ID
    public Roles list(int id);
    
    // Método para agregar un nuevo lote
    public boolean add(Roles rol);
    
    // Método para editar un lote existente
    public boolean edit(Roles rol);
    
    //Metodo para eliminar un lote por su ID
    public boolean eliminar(int id);
}
