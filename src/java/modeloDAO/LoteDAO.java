package modeloDAO;

import config.conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import interfaces.Lote;
import java.util.ArrayList;
import java.util.List;
import modelo.LoteM;

public class LoteDAO implements Lote {

    // Instancias de conexión y manejo de la base de datos
    conexion cn = new conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    @Override
    public List<LoteM> listar() {
        List<LoteM> list = new ArrayList<>();
        String sql = "SELECT * FROM lote";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                LoteM lote = new LoteM();
                lote.setId(rs.getInt("id"));
                lote.setNum(rs.getInt("num"));
                list.add(lote);
            }
        } catch (Exception e) {
            System.err.println("Error al listar lotes: " + e);
        } finally {
            // Cerrar conexiones para evitar fugas de memoria
            try { rs.close(); } catch (Exception e) { /* Ignorar */ }
            try { ps.close(); } catch (Exception e) { /* Ignorar */ }
            try { con.close(); } catch (Exception e) { /* Ignorar */ }
        }
        return list;
    }

    @Override
    public LoteM list(int id) {
        String sql = "SELECT * FROM lote WHERE id=?";
        LoteM lote = new LoteM();
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                lote.setId(rs.getInt("id"));
                lote.setNum(rs.getInt("num"));
            }
        } catch (Exception e) {
            System.err.println("Error al listar el lote: " + e);
        } finally {
            // Cerrar conexiones para evitar fugas de memoria
            try { rs.close(); } catch (Exception e) { /* Ignorar */ }
            try { ps.close(); } catch (Exception e) { /* Ignorar */ }
            try { con.close(); } catch (Exception e) { /* Ignorar */ }
        }
        return lote;
    }

    @Override
    public boolean add(LoteM lote) {
        String sql = "INSERT INTO lote(num) VALUES(?)";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, lote.getNum());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            System.err.println("Error al agregar el lote: " + e);
            return false;
        } finally {
            // Cerrar conexiones para evitar fugas de memoria
            try { ps.close(); } catch (Exception e) { /* Ignorar */ }
            try { con.close(); } catch (Exception e) { /* Ignorar */ }
        }
    }

    @Override
    public boolean edit(LoteM lote) {
        String sql = "UPDATE lote SET num=? WHERE id=?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, lote.getNum());
            ps.setInt(2, lote.getId());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            System.err.println("Error al editar el lote: " + e);
            return false;
        } finally {
            // Cerrar conexiones para evitar fugas de memoria
            try { ps.close(); } catch (Exception e) { /* Ignorar */ }
            try { con.close(); } catch (Exception e) { /* Ignorar */ }
        }
    }

    @Override
    public boolean eliminar(int id) {
        String sql = "DELETE FROM lote WHERE id=?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            System.err.println("Error al eliminar el lote: " + e);
            return false;
        } finally {
            // Cerrar conexiones para evitar fugas de memoria
            try { ps.close(); } catch (Exception e) { /* Ignorar */ }
            try { con.close(); } catch (Exception e) { /* Ignorar */ }
        }
    }
}
