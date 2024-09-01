package interfaces;

import java.util.List;
import modelo.Animal;

public interface animal {
    // Método para listar todos los lotes
    public List<Animal> listar(int idLote);
    
    // Método para listar todos los lotes
    public List<Animal> listarPapelera(int id);
    
    // Método para listar un lote por su ID
    public Animal list(int id);
    
    // Método para agregar un nuevo lote
    public boolean add(Animal Ani);
    
    // Método para editar un lote existente
    public boolean edit(Animal Ani);
    
    // Método para mandar a papelera un lote por su ID de 1 a 0
    public boolean cambiarFalse(Animal Anim);
    
    // Método para mandar a listar un lote por su ID de 0 a 1
    public boolean cambiarVerdad(Animal Anim);
    
    //Metodo para eliminar un lote por su ID
    public boolean eliminar(int id);
}
