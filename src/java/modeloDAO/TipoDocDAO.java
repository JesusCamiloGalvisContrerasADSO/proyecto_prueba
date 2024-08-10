package modeloDAO;

import config.conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import interfaces.TipoDoc;
import java.util.ArrayList;
import java.util.List;
import modelo.TipoDocum;

public class TipoDocDAO implements TipoDoc {

    conexion cn = new conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    TipoDocum tip = new TipoDocum();

    @Override
    public List<TipoDocum> listar() {
        List<TipoDocum> list = new ArrayList<>();
        String sql = "SELECT * FROM tipo_documento";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                TipoDocum tipo = new TipoDocum();
                tipo.setId(rs.getInt("id"));
                tipo.setNom(rs.getString("nombre"));
                list.add(tipo);
            }
        } catch (Exception e) {
            System.err.println("Error al listar tipos de documento: " + e);
        }
        return list;
    }

    @Override
    public TipoDocum list(int id) {
        String sql = "SELECT * FROM tipo_documento WHERE id=" + id;
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                tip.setId(rs.getInt("id")); // Asegúrate de que el nombre de columna es correcto
                tip.setNom(rs.getString("nombre")); // Asegúrate de que el nombre de columna es correcto
            }
        } catch (Exception e) {
            System.err.println("Error al listar tipos de documento: " + e);
        }
        return tip;
    }

    @Override
    public boolean add(TipoDocum Tipo)  {
        String sql = "INSERT INTO tipo_documento (nombre) VALUES ('" + Tipo.getNom() + "')";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            System.err.println("Error al agregar tipo de documento: " + e);
        }
        return false;
    }

    @Override
    public boolean edit(TipoDocum Tipo) {
        String sql = "UPDATE tipo_documento SET nombre='" + Tipo.getNom() + "' WHERE id=" + Tipo.getId();
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
        String sql = "DELETE FROM tipo_documento WHERE id=" + id;
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            System.err.println("Error al eliminar tipo de documento: " + e);
        }
        return false;
    }


}
