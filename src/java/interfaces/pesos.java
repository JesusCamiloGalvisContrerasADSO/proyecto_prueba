package interfaces;

import java.util.List;
import modelo.Pesos;

public interface pesos {
    // Método para listar todos los lotes
    public List<Pesos> listar(int animal_id);
    
    // Método para listar un lote por su ID
    public Pesos list(int id);
    
    // Método para agregar un nuevo lote
    public boolean add(Pesos pes);
    
    // Método para editar un lote existente
    public boolean edit(Pesos pes);
    
    //Metodo para eliminar un lote por su ID
    public boolean eliminar(int id);
}
