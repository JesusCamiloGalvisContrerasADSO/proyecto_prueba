package interfaces;

import java.util.List;
import modelo.LoteM;

public interface Lote {
    // Método para listar todos los lotes
    public List<LoteM> listar();
    
    // Método para listar un lote por su ID
    public LoteM list(int id);
    
    // Método para agregar un nuevo lote
    public boolean add(LoteM lot);
    
    // Método para editar un lote existente
    public boolean edit(LoteM lot);
    
    // Método para mandar a papelera un lote por su ID
    public boolean cambiarFalse(LoteM lote);
    
    //Metodo para eliminar un lote por su ID
    public boolean eliminar(int id);
}
