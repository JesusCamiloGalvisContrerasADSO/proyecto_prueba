package modeloDAO;

import config.conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import interfaces.Lote;
import java.util.ArrayList;
import java.util.List;
import modelo.LoteM;

//se le coloca el implements para que tome las acciones de interfaces 
//y las podamos aplicar en nuestras peticiones a la base de datos
public class LoteDAO implements Lote {

    // Instancias de conexión y manejo de la base de datos
    // Se instancia la clase de conexcion para poder mandar los datos o 
//    solicitarlos de la base de datos
    conexion cn = new conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

//    creamos el metodo listar en publico, en el vamos a instanciar el modelo para 
//    traer los geters y los seters y poder solicitarla la informacion a la 
//    base de datos, trae los lotes que se encuentren en estado 1
    @Override
    public List<LoteM> listar() {
        List<LoteM> list = new ArrayList<>();
//        asignamos a una variable la consulta
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
//                agregamos los datos de los seter y geter a list(instancia de modelo)
                list.add(lote);
            }
        } catch (Exception e) {
            System.err.println("Error al listar lotes: " + e);
        } 
        return list;
    }
    
//    tambien manejo el listar papelera en el cual solo va a mostrar a los lotes que se encuentren
//    en estado 0, se realiza lo mismo que en el anterior, solicitud de datos mediante los set y get
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

//    este metodo publico de list se usa para poder capturar el id y a su ves los 
//    datos para poder editar los campos, se realiza el prosedimiento de manera similar, 
//    se instancia la clase y se mustra la informacion por medio de los set y get
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

//    este metodo publico es el de agregar, donde se le pasan los valores y el id 
//    es colocado automaticamente por la base de datos ya que esta en autoincremet,
//      en este si se le pasan los datos directamente como atributos, el modelo y su instancia
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
