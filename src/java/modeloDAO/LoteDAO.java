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
        String sql = "SELECT * FROM lote WHERE estado = 1";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                LoteM lote = new LoteM();
                lote.setId(rs.getInt("id"));
                lote.setNum(rs.getInt("num"));
                lote.setEst(rs.getInt("estado"));
                list.add(lote);
            }
        } catch (Exception e) {
            System.err.println("Error al listar lotes: " + e);
        } 
        return list;
    }
    
    @Override
    public List<LoteM> listarPapelera() {
        List<LoteM> list = new ArrayList<>();
        String sql = "SELECT * FROM lote WHERE estado = 0";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                LoteM lote = new LoteM();
                lote.setId(rs.getInt("id"));
                lote.setNum(rs.getInt("num"));
                lote.setEst(rs.getInt("estado"));
                list.add(lote);
            }
        } catch (Exception e) {
            System.err.println("Error al listar lotes: " + e);
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
                lote.setEst(rs.getInt("estado"));
            }
        } catch (Exception e) {
            System.err.println("Error al listar el lote: " + e);
        }
        return lote;
    }

    @Override
    public boolean add(LoteM lote) {
        String sql = "INSERT INTO lote(num, estado) VALUES(?, ?)";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, lote.getNum());
            ps.setInt(2, lote.getEst());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            System.err.println("Error al agregar el lote: " + e);
        }
            return false;
    }

    @Override
    public boolean edit(LoteM lote) {
        String sql = "UPDATE lote SET num=?, estado=? WHERE id=?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, lote.getNum());
            ps.setInt(2, lote.getEst());
            ps.setInt(3, lote.getId());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            System.err.println("Error al editar el lote: " + e);
        } 
            return false;
        }

    @Override
    public boolean cambiarFalse(LoteM lote) {
        String sql = "UPDATE lote SET estado=? WHERE id=?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, lote.getEst());
            ps.setInt(2, lote.getId());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            System.err.println("Error al editar el lote: " + e);
        } 
            return false;
    }
    
    @Override
    public boolean cambiarVerdad(LoteM lote) {
        String sql = "UPDATE lote SET estado=? WHERE id=?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, lote.getEst());
            ps.setInt(2, lote.getId());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            System.err.println("Error al editar el lote: " + e);
        } 
            return false;
    }

    @Override
    public boolean eliminar(int id) {
        String sql = "DELETE FROM lote WHERE id=" + id;
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
