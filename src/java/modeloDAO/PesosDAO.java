
package modeloDAO;

import config.conexion;
import interfaces.pesos;
import java.sql.Connection;
import java.sql.Date;
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
    
    @Override
    public List<Pesos> listar(int animal_id) {
        List<Pesos> lista = new ArrayList<>();
        String sql= "SELECT id AS id_peso, peso AS pesoAnimal, fecha AS fechaPeso, animal_id, descripcion FROM pesos WHERE animal_id=?";
        
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
            pesos.setDescripcion(rs.getString("descripcion"));
            

            lista.add(pesos); // Añadir el objeto animal a la lista
        }
        } catch (Exception e) {
        }
        
        return lista;
    }

    @Override
    public Pesos list(int id) {
        String sql = "SELECT descripcion FROM pesos WHERE id=" + id;
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                pesos.setDescripcion(rs.getString("descripcion")); // Asegúrate de que el nombre de columna es correcto
            }
        } catch (Exception e) {
            System.err.println("Error al listar tipos de documento: " + e);
        }
        return pesos;
    }

    @Override
    public boolean add(Pesos pes) {
       String sql = "INSERT INTO pesos(peso, fecha, animal_id, descripcion) VALUES(?, ?, ?,?);";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setFloat(1, pes.getPeso());
            
            // Convierte java.util.Date a java.sql.Date
            java.sql.Date sqlDate = new java.sql.Date(pes.getFechaPeso().getTime());
            ps.setDate(2, sqlDate);
            
            ps.setInt(3, pes.getAnimal_id());
            ps.setString(4, pes.getDescripcion());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            System.err.println("Error al agregar el lote: " + e);
        }
            return false;
    }

    @Override
    public boolean edit(Pesos pes) {
        String sql = "UPDATE pesos SET descripcion = ? WHERE id=?;";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            
            ps.setString(1, pes.getDescripcion());
            ps.setInt(2, pes.getIdPesos());
            System.out.println("esta monda no prende "+pes.getDescripcion()+" "+pes.getIdPesos());
            
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            System.err.println("Error al agregar el lote: " + e);
        }
            return false;
    }

    @Override
    public boolean eliminar(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
