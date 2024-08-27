
package modeloDAO;

import config.conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import interfaces.roles;
import java.util.ArrayList;
import java.util.List;
import modelo.Roles;


public class RolesDAO implements roles{

    conexion cn = new conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    @Override
    public List<Roles> listar() {
        List<Roles> list = new ArrayList<>();
//        asignamos a una variable la consulta
        String sql = "SELECT * FROM tb_roles";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Roles rol = new Roles();
                rol.setId(rs.getInt("id"));
                rol.setNom(rs.getString("nombre"));
//                agregamos los datos de los seter y geter a list(instancia de modelo)
                list.add(rol);
            }
        } catch (Exception e) {
            System.err.println("Error al listar lotes: " + e);
        } 
        return list;
    }

    @Override
    public Roles list(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean add(Roles rol) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean edit(Roles rol) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean eliminar(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
