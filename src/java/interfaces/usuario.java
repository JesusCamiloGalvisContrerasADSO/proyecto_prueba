package interfaces;

import java.util.List;
import modelo.Usuario;

public interface usuario {
    public boolean add(Usuario user);
    public int addUsuario(Usuario user);
    public boolean addPerfil(Usuario user);
    public List<Usuario> listar();
    public Usuario list(int id);
    public boolean edit(Usuario usuario);
    public boolean editPerfil(Usuario user);
    public boolean eliminar(int id);
    
    public boolean VerificarLogin(Usuario user);
    
}
