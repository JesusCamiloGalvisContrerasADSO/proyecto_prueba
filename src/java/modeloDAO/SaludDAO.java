
package modeloDAO;

import config.conexion;
import interfaces.salud;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import modelo.Salud;


public class SaludDAO implements salud{

    conexion cn = new conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    @Override
    public List<Salud> listar() {
        List<Salud> list = new ArrayList<>();
        String sql = "SELECT * FROM estado_salud";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Salud tipo = new Salud();
                tipo.setId(rs.getInt("id"));
                tipo.setNombre(rs.getString("nombre"));
                list.add(tipo);
            }
        } catch (Exception e) {
            System.err.println("Error al listar tipos de documento: " + e);
        }
        return list;
    }
    
}
