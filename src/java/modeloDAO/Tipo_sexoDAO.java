
package modeloDAO;

import config.conexion;
import interfaces.tipo_sexo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import modelo.Tipo_sexo;


public class Tipo_sexoDAO implements tipo_sexo{
    
    conexion cn = new conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    @Override
    public List<Tipo_sexo> listar() {
        List<Tipo_sexo> list = new ArrayList<>();
//        asignamos a una variable la consulta
        String sql = "SELECT * FROM tipo_sexo";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Tipo_sexo TSexo = new Tipo_sexo();
                TSexo.setId(rs.getInt("id"));
                TSexo.setNombre(rs.getString("nombre"));
//                agregamos los datos de los seter y geter a list(instancia de modelo)
                list.add(TSexo);
            }
        } catch (Exception e) {
            System.err.println("Error al listar lotes: " + e);
        } 
        return list;
    }
    
}
