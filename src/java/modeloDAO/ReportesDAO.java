
package modeloDAO;

import config.conexion;
import interfaces.reportes;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import modelo.Animal;
import modelo.LoteM;
import modelo.Pesos;
import modelo.Raza;
import modelo.Salud;


public class ReportesDAO implements reportes{
    conexion cn = new conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    @Override
    public List<LoteM> listarLotes() {
        List<LoteM> list = new ArrayList<>();
        
        //        asignamos a una variable la consulta
        String sql = "SELECT num, id, estado FROM lote;";
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

    @Override
    public List<Animal> ConsultaLote(int idLote) {
        List<Animal> lista = new ArrayList<>();
        
        String sql = "SELECT " +
             "a.id AS idAnimal, " +
             "a.num, " +
             "a.estado, " +
             "r.id AS idRaza, " +
             "r.nombre AS raza, " +
             "s.id AS idSexo, " +
             "s.nombre AS sexo, " +
             "lot.id AS idLote, " +
             "lot.num AS numLote, " +
             "p.peso, " +
             "p.fecha AS fechaPeso, " +
             "p.id AS idPeso, " +
             "es.id AS idSalud, " +
             "es.nombre AS nombreSalud " +
             "FROM animal a " +
             "INNER JOIN lote lot ON a.lote_id = lot.id " +
             "INNER JOIN tipo_raza r ON a.raza_id = r.id " +
             "INNER JOIN tipo_sexo s ON a.tipo_sexo_id = s.id " +
             "INNER JOIN estado_salud es ON a.saludId = es.id " +
             "INNER JOIN ( " +
             "    SELECT " +
             "        animal_id, " +
             "        peso, " +
             "        fecha, " +
             "        id AS idPeso " +
             "    FROM ( " +
             "        SELECT " +
             "            animal_id, " +
             "            peso, " +
             "            fecha, " +
             "            id, " +
             "            ROW_NUMBER() OVER (PARTITION BY animal_id ORDER BY fecha DESC, id DESC) AS row_num " +  // Ordena por fecha y luego por id
             "        FROM pesos " +
             "    ) sub " +
             "    WHERE row_num = 1 " +  // Selecciona solo el último registro por fecha y id más alto
             ") last_p ON a.id = last_p.animal_id " +  // Relación con animal para traer solo el último registro
             "INNER JOIN pesos p ON p.id = last_p.idPeso " +  // Relación con pesos para traer los detalles del peso
             "WHERE a.lote_id = ?;";
        
        try {
        con = cn.getConnection();
        ps = con.prepareStatement(sql);
        ps.setInt(1, idLote);
        rs = ps.executeQuery();
        while (rs.next()) {
            System.err.println("Animal enviado");

            Animal animal = new Animal(); // Crear un nuevo objeto animal en cada iteración
            animal.setId(rs.getInt("idAnimal"));
            animal.setNum(rs.getString("num"));
            animal.setTipo_sexo(rs.getInt("idSexo"));
            animal.setNomTipoSex(rs.getString("sexo"));
            animal.setEstado(rs.getInt("estado"));

            // Mapeo del tipo de raza
            animal.setRaza_id(rs.getInt("idRaza"));
            Raza raza = new Raza();
            raza.setNombre(rs.getString("raza"));
            animal.setRaza(raza); // Asignar la raza al animal

            // Mapeo del lote
            animal.setLote_id(rs.getInt("idLote"));
            LoteM lote = new LoteM();
            lote.setNum(rs.getInt("numLote"));
            animal.setLote(lote); // Asignar el lote al animal

            // Mapeo de los pesos
            animal.setPesos_id(rs.getInt("idPeso"));
            Pesos peso = new Pesos();
            peso.setPeso(rs.getFloat("peso"));
            peso.setFechaPeso(rs.getDate("fechaPeso"));
            animal.setPesos(peso); // Asignar el objeto Pesos al animal
            
            //Mapeo de el estado de salud
            animal.setSalud_id(rs.getInt("idSalud"));
            Salud salud = new Salud();
            salud.setNombre(rs.getString("nombreSalud"));
            animal.setSalud(salud);

            lista.add(animal); // Añadir el objeto animal a la lista
        }

    } catch (Exception e) {
        lista = null;
        System.err.println("Error al listar usuarios: " + e);
    }
    return lista;
    }
}
    
    

