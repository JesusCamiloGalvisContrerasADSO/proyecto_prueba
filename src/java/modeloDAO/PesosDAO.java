
package modeloDAO;

import config.conexion;
import interfaces.pesos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import modelo.Pesos;


public class PesosDAO implements pesos{

    conexion cn = new conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    Pesos pesos = new Pesos();
    PesosDAO dao = new PesosDAO();
    
    @Override
    public List<Pesos> listar(int animal_id) {
        List<Pesos> lista = new ArrayList<>();
        String sql= "SELECT id AS id_peso, peso AS pesoAnimal, fecha AS fechaPeso, animal_id FROM pesos WHERE animal_id=?";
        
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, animal_id);
            rs = ps.executeQuery();
            
            while (rs.next()) {

            Pesos pesos = new Pesos(); // Crear un nuevo objeto animal en cada iteración
            pesos.setIdPesos(rs.getInt("id_peso"));
            pesos.setPeso(rs.getFloat("pesoAnimal"));
            pesos.setFechaPeso(rs.getDate("fechaPeso"));
            pesos.setAnimal_id(rs.getInt("animal_id"));
            

            lista.add(pesos); // Añadir el objeto animal a la lista
        }
        } catch (Exception e) {
        }
        
        return lista;
    }

    @Override
    public Pesos list(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean add(Pesos pes) {
       return false;
    }

    @Override
    public boolean edit(Pesos pes) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean eliminar(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
