package interfaces;

import java.util.List;
import modelo.Usuario;


//estos son los metodos que se van a trabajar en el dao referente a su modelo
//a interfaces se refiere a los metodos los cuales van a enviar las consultas 
//a la base de datos

public interface usuario {
    public boolean add(Usuario user);
    public int addUsuario(Usuario user);
    public boolean addPerfil(Usuario user);
    public List<Usuario> listar();
    public Usuario list(int id);
    public boolean edit(Usuario usuario);
    public boolean editPerfil(Usuario user);
    public boolean eliminar(int id);
    
    public boolean actualizarContra(Usuario user);
    
    public boolean VerificarLogin(Usuario user);
    
}
