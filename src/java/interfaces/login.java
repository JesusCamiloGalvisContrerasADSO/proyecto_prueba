package interfaces;

import java.util.List;
import modelo.Usuario;

//estos son los metodos que se van a trabajar en el dao referente a su modelo
//a interfaces se refiere a los metodos los cuales van a enviar las consultas 
//a la base de datos

public interface login {
    
    public boolean VerificarLogin(Usuario user);
    
}
