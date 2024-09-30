package interfaces;

import java.util.List;
import modelo.Animal;
import modelo.LoteM;

public interface reportes {
    // Método para listar todos los lotes
    public List<LoteM> listarLotes();

    public List<Animal> ConsultaLote(int idLote);
    
}
