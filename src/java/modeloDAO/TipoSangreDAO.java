package modeloDAO;

import config.conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import interfaces.TipSan;
import java.util.ArrayList;
import java.util.List;
import modelo.TipoSangre;

//aqui se realizan todos los metodos crud para poder manejar el tipo de sangre
public class TipoSangreDAO implements TipSan {

    conexion cn = new conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    TipoSangre tip = new TipoSangre();

    @Override
    public List<TipoSangre> listar() {
        List<TipoSangre> list = new ArrayList<>();
        String sql = "SELECT * FROM tipo_sangre";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                TipoSangre tipo = new TipoSangre();
                tipo.setId(rs.getInt("id"));
                tipo.setNom(rs.getString("nombre"));
                list.add(tipo);
            }
        } catch (Exception e) {
            System.err.println("Error al listar tipos de sangre: " + e);
        }
        return list;
    }

    @Override
    public TipoSangre list(int id) {
        String sql = "SELECT * FROM tipo_sangre WHERE id=" + id;
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                tip.setId(rs.getInt("id")); // Asegúrate de que el nombre de columna es correcto
                tip.setNom(rs.getString("nombre")); // Asegúrate de que el nombre de columna es correcto
            }
        } catch (Exception e) {
            System.err.println("Error al listar tipos de sangre: " + e);
        }
        return tip;
    }

    @Override
    public boolean add(TipoSangre tipSa) {
        String sql = "INSERT INTO tipo_sangre (nombre) VALUES ('" + tipSa.getNom() + "')";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            System.err.println("Error al agregar tipo de sangre: " + e);
        }
        return false;
    }

    @Override
    public boolean edit(TipoSangre tipSa) {
        String sql = "UPDATE tipo_sangre SET nombre='" + tipSa.getNom() + "' WHERE id=" + tipSa.getId();
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            System.err.println("Error al editar tipo de sangre: " + e);
        }
        return false;
    }

    @Override
    public boolean eliminar(int id) {
        String sql = "DELETE FROM tipo_sangre WHERE id=" + id;
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            System.err.println("Error al eliminar tipo de sangre: " + e);
        }
        return false;
    }
}
