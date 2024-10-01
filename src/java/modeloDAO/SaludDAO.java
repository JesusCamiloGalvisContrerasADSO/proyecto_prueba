
package modeloDAO;

import config.conexion;
import interfaces.salud;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import modelo.Salud;

//    aqui se manejan todos los metodos CRUD necesarios para poder manejar al la salud
//de momento solo lista ya que es un dato fijo pero se deja abierto para poder permitir la escalabilidad
public class SaludDAO implements salud{

    conexion cn = new conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    //listar me trae todos los datos necesarios para mandar a la vista
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
