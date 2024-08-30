package interfaces;

import java.util.List;
import modelo.Raza;

public interface raza {
    // Método para listar todos los lotes
    public List<Raza> listar();
    
    // Método para listar un lote por su ID
    public Raza list(int id);
    
    // Método para agregar un nuevo lote
    public boolean add(Raza raz);
    
    // Método para editar un lote existente
    public boolean edit(Raza raz);
    
    //Metodo para eliminar un lote por su ID
    public boolean eliminar(int id);
}
