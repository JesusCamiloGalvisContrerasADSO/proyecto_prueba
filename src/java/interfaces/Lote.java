package interfaces;

import java.util.List;
import modelo.LoteM;

public interface Lote {
    // Método para listar todos los lotes
    public List<LoteM> listar();
    
    // Método para listar todos los lotes
    public List<LoteM> listarPapelera();
    
    // Método para listar un lote por su ID
    public LoteM list(int id);
    
    // Método para agregar un nuevo lote
    public boolean add(LoteM lot);
    
    // Método para editar un lote existente
    public boolean edit(LoteM lot);
    
    // Método para mandar a papelera un lote por su ID de 1 a 0
    public boolean cambiarFalse(LoteM lote);
    
    // Método para mandar a listar un lote por su ID de 0 a 1
    public boolean cambiarVerdad(LoteM lote);
    
    //Metodo para eliminar un lote por su ID
    public boolean eliminar(int id);
}
