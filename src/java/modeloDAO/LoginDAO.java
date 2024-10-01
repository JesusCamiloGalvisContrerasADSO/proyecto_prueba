
package modeloDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import modelo.Usuario;

import config.conexion;
import interfaces.login;
import modelo.Usuario;
import org.mindrot.jbcrypt.BCrypt;


public class LoginDAO implements login{
    
    conexion cn = new conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    Usuario user = new Usuario();
    
    //aqui se maneja el crud del login
    @Override
    public boolean VerificarLogin(Usuario user) {
        String sql = "SELECT u.contrasena, u.rol_id, p.id AS id_perfil " +
                     "FROM usuarios u " +
                     "INNER JOIN perfil p ON p.usuario_id = u.id " +
                     "WHERE u.documento = ?;";

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql); 
            ps.setLong(1, user.getDocumento());
            rs = ps.executeQuery();

            if (rs.next()) {
                // Verificar la contraseña encriptada
                String contrasenaEncriptada = rs.getString("contrasena");

                boolean contrasena = BCrypt.checkpw(user.getContrasena(), contrasenaEncriptada);

                if (contrasena) {
                    // Establecer el rol y el id del usuario autenticado
                    user.setRol(rs.getInt("rol_id")); // Usa 'rol_id' en lugar de 'rol'
                    user.setIdUsuario(rs.getInt("id_perfil")); // Usa 'id_perfil' en lugar de 'id'
                    return true;
                }
            } else {
                System.out.println("No se encontró ningún usuario con el documento proporcionado.");
            }
        } catch (Exception e) {
            System.err.println("Error al verificar login: ");
        } 
        return false; // Si ocurre un error o las credenciales no coinciden, se retorna false
    }


}
