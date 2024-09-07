
package modeloDAO;

import config.conexion;
import interfaces.datosAdmin;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import modelo.DatosAdmin;


public class DatosAdminDAO implements datosAdmin{

    // Instancias de conexión y manejo de la base de datos
    // Se instancia la clase de conexcion para poder mandar los datos o 
//    solicitarlos de la base de datos
    conexion cn = new conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    @Override
    public DatosAdmin listar() {
        String sql = "SELECT " +
                     "COUNT(DISTINCT animal.id) AS total_animales, " +
                     "COUNT(DISTINCT usuarios.id) AS total_usuarios, " +
                     "COUNT(DISTINCT CASE WHEN lote.estado = 1 THEN lote.id END) AS total_lotes_estado_1, " +
                     "COUNT(DISTINCT CASE WHEN lote.estado = 0 THEN lote.id END) AS total_lotes_estado_0 " +
                     "FROM animal " +
                     "INNER JOIN lote ON animal.lote_id = lote.id " +
                     "INNER JOIN usuarios " +  // Condición de unión (Asegúrate de que esté correcto)
                     "WHERE animal.estado = 1 " +
                     "AND usuarios.rol_id IN (1, 2);";
        
        DatosAdmin datos = new DatosAdmin();
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                datos.setCantAnim(rs.getInt("total_animales"));
                datos.setCantUsu(rs.getInt("total_usuarios"));
                datos.setCantLot(rs.getInt("total_lotes_estado_1"));
                datos.setCantLotVent(rs.getInt("total_lotes_estado_0"));
            }
        } catch (Exception e) {
            System.err.println("Error al listar el lote: " + e);
        }
        return datos;
    }
    
}
