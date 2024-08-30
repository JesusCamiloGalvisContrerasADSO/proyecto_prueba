
package modeloDAO;

import config.conexion;
import interfaces.raza;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import modelo.Raza;


public class RazaDAO implements raza{
    
    conexion cn = new conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    Raza raz = new Raza();
    
    @Override
    public List<Raza> listar() {
        List<Raza> list = new ArrayList<>();
//        asignamos a una variable la consulta
        String sql = "SELECT * FROM tipo_raza";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Raza rol = new Raza();
                rol.setId(rs.getInt("id"));
                rol.setNombre(rs.getString("nombre"));
//                agregamos los datos de los seter y geter a list(instancia de modelo)
                list.add(rol);
            }
        } catch (Exception e) {
            System.err.println("Error al listar lotes: " + e);
        } 
        return list;
    }

    @Override
    public Raza list(int id) {
        String sql = "SELECT * FROM tipo_raza WHERE id=" + id;
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                raz.setId(rs.getInt("id")); // Asegúrate de que el nombre de columna es correcto
                raz.setNombre(rs.getString("nombre")); // Asegúrate de que el nombre de columna es correcto
            }
        } catch (Exception e) {
            System.err.println("Error al listar tipos de documento: " + e);
        }
        return raz;
    }

    @Override
    public boolean add(Raza raz) {
       String sql = "INSERT INTO tipo_raza (nombre) VALUES (?)";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, raz.getNombre());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            System.err.println("Error al agregar tipo de documento: " + e);
        }
        return false; 
    }

    @Override
    public boolean edit(Raza raz) {
        String sql = "UPDATE tipo_raza SET nombre = ? WHERE id=?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, raz.getNombre());
            ps.setInt(2, raz.getId());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            System.err.println("Error al editar tipo de raza: " + e);
        }
        return false;
    }

    @Override
    public boolean eliminar(int id) {
        String sql = "DELETE FROM tipo_raza WHERE id=?" ;
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            System.err.println("Error al eliminar tipo de documento: " + e);
        }
        return false;
    }
    
}
