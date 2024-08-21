package modeloDAO;

import config.conexion;
import interfaces.usuario;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import modelo.TipoDocum;
import modelo.TipoSangre;
import modelo.Usuario;

public class UsuarioDAO implements usuario {
    conexion cn = new conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    Usuario user = new Usuario();

    @Override
public List<Usuario> listar() {
    List<Usuario> lista = new ArrayList<>();
    String sql = "SELECT " +
                         "p.id AS persona_id, " +
                         "p.nombre, " +
                         "p.apellido, " +
                         "p.telefono, " +
                         "p.email, " +
                         "p.fechaContrato, " +
                         "u.documento, " +
                         "u.contrasena, " +
                         "td.nombre AS tipo_documento, " +
                         "ts.nombre AS tipo_sangre " +
                         "FROM perfil p " +
                         "INNER JOIN usuarios u ON p.usuario_id = u.id " +
                         "INNER JOIN tipo_documento td ON p.tipo_doc_id = td.id " +
                         "INNER JOIN tipo_sangre ts ON p.sangre_id = ts.id " +
                         "WHERE p.estado = 1";
    
    try {
        con = cn.getConnection();
        ps = con.prepareStatement(sql);
        rs = ps.executeQuery();
        while (rs.next()) {
            System.err.println("Usuario enviado");
            Usuario usuario = new Usuario(); // Crear un nuevo objeto Usuario en cada iteración
            usuario.setIdUsuario(rs.getInt("persona_id"));
            usuario.setDocumento(rs.getLong("documento"));
            usuario.setContrasena(rs.getString("contrasena"));
            usuario.setNombre(rs.getString("nombre"));
            usuario.setApellido(rs.getString("apellido"));
            usuario.setTelefono(rs.getLong("telefono"));
            usuario.setEmail(rs.getString("email"));
            usuario.setFechaContra(rs.getDate("fechaContrato"));

            // Mapeo del TipoDocumento
            TipoDocum tipoDocum = new TipoDocum();
            tipoDocum.setNom(rs.getString("tipo_documento"));
            usuario.setTipoDocum(tipoDocum);

            // Mapeo del TipoSangre
            TipoSangre tipoSangre = new TipoSangre();
            tipoSangre.setNom(rs.getString("tipo_sangre"));
            usuario.setTipoSangre(tipoSangre);

            lista.add(usuario); // Añadir el objeto Usuario a la lista
        }
    } catch (Exception e) {
        System.err.println("Error al listar usuarios: " + e);
    }
    return lista;
}



    @Override
    public Usuario list(int id) {
        Usuario usuario = new Usuario();
        String sql = "SELECT p.id AS persona_id, p.nombre, p.apellido, p.telefono, " +
             "p.email, p.fechaContrato, u.documento, u.contrasena, " +
             "td.nombre AS tipo_documento, ts.nombre AS tipo_sangre " +
             "FROM perfil p " +
             "INNER JOIN usuarios u ON p.usuario_id = u.id " +
             "INNER JOIN tipo_documento td ON p.tipo_doc_id = td.id " +
             "INNER JOIN tipo_sangre ts ON p.sangre_id = ts.id"+
             "WHERE p.id = ?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                
                usuario.setIdUsuario(rs.getInt("usuario_id"));
                usuario.setDocumento(rs.getInt("documento"));
                usuario.setContrasena(rs.getString("contrasena"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setApellido(rs.getString("apellido"));
                usuario.setTelefono(rs.getInt("telefono"));
                usuario.setEmail(rs.getString("email"));
                usuario.setFechaContra(rs.getDate("fechaContrato"));

                // Mapeo del TipoDocumento
                TipoDocum tipoDocum = new TipoDocum();
                tipoDocum.setNom(rs.getString("tipo_documento"));
                usuario.setTipoDocum(tipoDocum);

                // Mapeo del TipoSangre
                TipoSangre tipoSangre = new TipoSangre();
                tipoSangre.setNom(rs.getString("tipo_sangre"));
                usuario.setTipoSangre(tipoSangre);

            }
        } catch (Exception e) {
            System.err.println("Error al obtener usuario por ID: " + e);
        }
        return usuario;
    }
    
    @Override
    public boolean add(Usuario user) {
        int userId = addUsuario(user);
        if (userId != -1) {
            user.setIdUsuario(userId); // Establece el ID del usuario en el objeto Usuario
            return addPerfil(user);
        } else {
            return false;
        }
    }


   @Override
    public int addUsuario(Usuario user) {
    String sqlUsuario = "INSERT INTO usuarios(documento, contrasena) VALUES(?, ?)";
    int userId = -1;
    
    try {
        con = cn.getConnection();
        ps = con.prepareStatement(sqlUsuario, PreparedStatement.RETURN_GENERATED_KEYS);
        ps.setLong(1, user.getDocumento());
        ps.setString(2, user.getContrasena());
        ps.executeUpdate();
        
        // Obtener el ID generado automáticamente
        ResultSet rs = ps.getGeneratedKeys();
        if (rs.next()) {
            userId = rs.getInt(1);
        }
        ps.close();
    } catch (Exception e) {
        System.err.println("Error al agregar el usuario: " + e);
    }
    
    return userId;
}


    @Override
public boolean addPerfil(Usuario user) {
    String sql = "INSERT INTO perfil (nombre, apellido, telefono, email, fechaContrato, usuario_id, tipo_doc_id, sangre_id, estado) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    
    try {
        con = cn.getConnection();
        ps = con.prepareStatement(sql);
        ps.setString(1, user.getNombre());
        ps.setString(2, user.getApellido());
        ps.setLong(3, user.getTelefono());
        ps.setString(4, user.getEmail());
        ps.setDate(5, new java.sql.Date(user.getFechaContra().getTime()));
        ps.setInt(6, user.getIdUsuario()); // Usa el ID del usuario obtenido
        ps.setInt(7, user.getDocid());
        ps.setInt(8, user.getSanid());
        ps.setInt(9, 1); // Asignar el estado (1 como ejemplo)
        ps.executeUpdate();
        return true;
    } catch (Exception e) {
        System.err.println("Error al agregar el perfil: " + e);
    }
    
    return false;
}


    @Override
    public boolean edit(Usuario usuario) {
        
        return false;
    }

    @Override
    public boolean eliminar(int id) {
        String sql = "DELETE FROM usuarios WHERE id=?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            System.err.println("Error al eliminar usuario: " + e);
        }
        return false;
    }

    @Override
    public boolean VerificarLogin(Usuario user) {
        String sql = "SELECT documento, contrasena FROM usuarios WHERE documento = ? AND contrasena = ?;";

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql); 
            ps.setLong(1, user.getDocumento());
            ps.setString(2, user.getContrasena());
            rs = ps.executeQuery();

            if (rs.next()) {
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
