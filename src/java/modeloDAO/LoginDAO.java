
package modeloDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import modelo.Usuario;

import config.conexion;
import interfaces.login;
import modelo.Usuario;


public class LoginDAO implements login{
    
    conexion cn = new conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    Usuario user = new Usuario();
    
     @Override
    public boolean VerificarLogin(Usuario user) {
        String sql = "SELECT u.documento, u.contrasena, u.rol_id, p.id AS id_perfil "+
                     "FROM usuarios u "+
                     "INNER JOIN perfil p ON p.usuario_id = u.id "+
                     "WHERE u.documento = ? AND u.contrasena = ?;";

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql); 
            ps.setLong(1, user.getDocumento());
            ps.setString(2, user.getContrasena());
            rs = ps.executeQuery();

            if (rs.next()) {
                user.setRol(rs.getInt("rol_id")); // Cambia 'id' a 'rol_id'
                user.setIdUsuario(rs.getInt("id_perfil")); 
                return true; // Credenciales válidas
            } else {
                return false; // Credenciales inválidas
            }
            
        } catch (Exception e) {
            System.err.println("Error al verificar login: " + e);
        }
        
        return false; // Si ocurre un error, se retorna false
    }
}
