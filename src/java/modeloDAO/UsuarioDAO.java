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
                         "u.id , " +
                         "u.documento, " +
                         "u.contrasena, " +
                         "u.rol_id,"+
                         "r.nombre AS nombre_rol,"+
                         "td.nombre AS tipo_documento, " +
                         "ts.nombre AS tipo_sangre " +
                         "FROM perfil p " +
                         "INNER JOIN usuarios u ON p.usuario_id = u.id " +
                         "INNER JOIN tb_roles r ON u.rol_id = r.id " +
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
            usuario.setRol(rs.getInt("rol_id"));
            usuario.setNomRol(rs.getString("nombre_rol"));
            usuario.setIdPerfil(rs.getInt("id"));

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
        
        String sql = "SELECT p.id AS persona_id, p.nombre, p.apellido, p.telefono, " +
             "p.email, p.fechaContrato, p.tipo_doc_id, p.sangre_id, u.documento, u.rol_id, " +
             "r.nombre AS nombre_rol, " +
             "td.nombre AS tipo_documento, ts.nombre AS tipo_sangre " +
             "FROM perfil p " +
             "INNER JOIN usuarios u ON p.usuario_id = u.id " +
             "INNER JOIN tb_roles r ON u.rol_id = r.id " +
             "INNER JOIN tipo_documento td ON p.tipo_doc_id = td.id " +
             "INNER JOIN tipo_sangre ts ON p.sangre_id = ts.id " +
             "WHERE p.id = ?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                user.setIdUsuario(rs.getInt("persona_id"));
                user.setNombre(rs.getString("nombre"));
                user.setApellido(rs.getString("apellido")); 
                user.setTelefono(rs.getLong("telefono")); 
                user.setEmail(rs.getString("email")); 
                user.setFechaContra(rs.getDate("fechaContrato"));
                user.setDocumento(rs.getLong("documento"));
                user.setRol(rs.getInt("rol_id"));
                user.setNomRol(rs.getString("nombre_rol"));
                
                // Mapeo del TipoDocumento
                user.setDocid(rs.getInt("tipo_doc_id"));
                TipoDocum tipoDocum = new TipoDocum();
                tipoDocum.setNom(rs.getString("tipo_documento"));
                user.setTipoDocum(tipoDocum);

                // Mapeo del TipoSangre
                user.setSanid(rs.getInt("sangre_id"));
                TipoSangre tipoSangre = new TipoSangre();
                tipoSangre.setNom(rs.getString("tipo_sangre"));
                user.setTipoSangre(tipoSangre);

            // Aquí puedes usar los valores obtenidos, por ejemplo:
//            System.out.println("ID: " + personaId + ", Nombre: " + nombre + ", Rol: " + nombreRol);
            }
        
        } catch (Exception e) {
            System.err.println("Error al obtener usuario por ID: " + e);
        }
        return user;
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
    String sqlUsuario = "INSERT INTO usuarios(documento, contrasena, rol_id) VALUES(?, ?, ?)";
    int userId = -1;
    
    try {
        con = cn.getConnection();
        ps = con.prepareStatement(sqlUsuario, PreparedStatement.RETURN_GENERATED_KEYS);
        ps.setLong(1, user.getDocumento());
        ps.setString(2, user.getContrasena());
        ps.setInt(3, 0);
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
    public boolean edit(Usuario user) {
        // La consulta SQL completa con los campos necesarios
        String sql = "UPDATE perfil "
            + "INNER JOIN usuarios ON perfil.usuario_id = usuarios.id "
            + "SET perfil.nombre = ?, "
            + "perfil.apellido = ?, "
            + "perfil.telefono = ?, "
            + "perfil.email = ?, "
            + "usuarios.documento = ?, "
            + "usuarios.rol_id = ?, "
            + "perfil.tipo_doc_id = ?, "
            + "perfil.sangre_id = ? "
            + "WHERE perfil.id = ?";

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);

            ps.setString(1, user.getNombre());
            ps.setString(2, user.getApellido());
            ps.setLong(3, user.getTelefono());
            ps.setString(4, user.getEmail());
            ps.setLong(5, user.getDocumento());
            ps.setInt(6, user.getRol());
            ps.setInt(7, user.getTipoDocum().getId());
            ps.setInt(8, user.getTipoSangre().getId());
            ps.setInt(9, user.getIdUsuario()); // Este es el ID del perfil

            int rowsUpdated = ps.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("El usuario fue actualizado exitosamente.");
                return true;
            }
        } catch (Exception e) {
            System.err.println("Error al actualizar el usuario: " + e);
        }

        return false;
    }
    
    @Override
    public boolean editPerfil(Usuario user) {
        // La consulta SQL completa con los campos necesarios
        String sql = "UPDATE perfil " +
                 "JOIN usuarios ON perfil.usuario_id = usuarios.id " +
                 "SET perfil.telefono = ?, " +
                 "perfil.email = ?, " +
                 "perfil.tipo_doc_id = ? " +
                 "WHERE perfil.id = ?";  

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);

            ps.setLong(1, user.getTelefono());
            ps.setString(2, user.getEmail());
            ps.setInt(3, user.getTipoDocum().getId());
            ps.setInt(4, user.getIdUsuario()); // Este es el ID del perfil

            int rowsUpdated = ps.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("El usuario fue actualizado exitosamente.");
                return true;
            }
        } catch (Exception e) {
            System.err.println("Error al actualizar el usuario: " + e);
        }

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
