
package modeloDAO;

import config.conexion;
import interfaces.animal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import modelo.Animal;
import modelo.LoteM;
import modelo.Pesos;
import modelo.Raza;
import modelo.Salud;


public class AnimalDAO implements animal{

    conexion cn = new conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    Animal Anim = new Animal();
    
    @Override
    public List<Animal> listar(int idLote) {
        List<Animal> lista = new ArrayList<>();
        String sql = "SELECT " +
             "a.id AS idAnimal, " +
             "a.num, " +
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
             "WHERE a.lote_id = ? AND a.estado = 1;";
        

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

    @Override
    public List<Animal> listarPapelera(int idLote) {
        List<Animal> lista = new ArrayList<>();
        String sql = "SELECT " +
             "a.id AS idAnimal, " +
             "a.num, " +
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
             "WHERE a.lote_id = ? AND a.estado = 0;";
        
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

    @Override
    public Animal list(int id) {
        List<Animal> lista = new ArrayList<>();
        String sql = "SELECT a.id AS idAnimal, a.num, r.id AS idRaza, r.nombre AS raza, s.id AS idSexo, " +
                       "s.nombre AS sexo, lot.id AS idLote, lot.num AS numLote, p.peso, "+
                       "p.fecha AS fechaPeso, p.id AS idPeso, es.id AS idSalud, es.nombre AS nombreSalud " +
                       "FROM animal a " +
                       "INNER JOIN lote lot ON a.lote_id = lot.id " +
                       "INNER JOIN tipo_raza r ON a.raza_id = r.id " +
                       "INNER JOIN tipo_sexo s ON a.tipo_sexo_id = s.id " +
                       "INNER JOIN pesos p ON a.id = p.animal_id " +
                       "INNER JOIN estado_salud es ON a.saludId = es.id " +
                       "INNER JOIN ( " +
                       "    SELECT animal_id, fecha, peso " +
                       "    FROM ( " +
                       "        SELECT animal_id, fecha, peso, " +
                       "               ROW_NUMBER() OVER (PARTITION BY animal_id ORDER BY fecha DESC) AS row_num " +
                       "        FROM pesos " +
                       "    ) sub " +
                       "    WHERE row_num = 1 " +
                       ") last_p ON p.animal_id = last_p.animal_id AND p.fecha = last_p.fecha " +
                       "WHERE a.estado = 1 AND a.id = ?; ";
        Animal animal = new Animal();
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                
                animal.setId(rs.getInt("idAnimal"));
                animal.setNum(rs.getString("num"));
                animal.setTipo_sexo(rs.getInt("idSexo"));
                animal.setNomTipoSex(rs.getString("sexo"));

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
            }
        } catch (Exception e) {
            System.err.println("Error al listar el lote: " + e);
        }
        return animal;
    }

    @Override
    public boolean add(Animal Anim) {
        // Inserción en la tabla 'animal'
        String sqlAnimal = "INSERT INTO animal (lote_id, num, raza_id, tipo_sexo_id, fecha, estado, saludId) VALUES (?, ?, ?, ?, CURDATE(), ?, ?)";

        // Inserción en la tabla 'pesos'
        String sqlPesos = "INSERT INTO pesos (peso, fecha, animal_id) VALUES (?, ?, ?)";

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            // Conexión y preparación de la inserción en 'animal'
            con = cn.getConnection();
            
            ps = con.prepareStatement(sqlAnimal, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setInt(1, Anim.getLote_id());
            ps.setString(2, Anim.getNum());
            ps.setInt(3, Anim.getRaza_id());
            ps.setInt(4, Anim.getTipo_sexo());
            ps.setInt(5, 1);
            ps.setInt(6, Anim.getSalud_id());

            // Ejecutar la inserción en 'animal'
            ps.executeUpdate();

            // Obtener el ID generado automáticamente del nuevo animal
            rs = ps.getGeneratedKeys();
            int idAnimal = 0;
            if (rs.next()) {
                idAnimal = rs.getInt(1);
            } else {
                throw new Exception("No se pudo obtener el ID del nuevo animal.");
            }

            // Preparar y ejecutar la inserción en 'pesos' con el ID obtenido
            ps = con.prepareStatement(sqlPesos);
            ps.setFloat(1, Anim.getPesos().getPeso());
            ps.setDate(2, new java.sql.Date(Anim.getPesos().getFechaPeso().getTime()));
            ps.setInt(3, idAnimal);

            // Ejecutar la inserción en 'pesos'
            ps.executeUpdate();


            return true;

        } catch (Exception e) {
        } 
    return false;
}

    @Override
    public boolean edit(Animal Ani) {
        String sql="UPDATE animal SET saludId = ?, lote_id = ? WHERE id = ?;";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, Ani.getSalud_id());
            ps.setInt(2, Ani.getLote_id());
            ps.setInt(3, Ani.getId());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            System.err.println("Error al editar el lote: " + e);
        } 
            return false;
    }

    @Override
    public boolean cambiarFalse(Animal Anim) {
        String sql = "UPDATE animal SET estado=? WHERE id=?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, Anim.getEstado());
            ps.setInt(2, Anim.getId());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            System.err.println("Error al editar el lote: " + e);
        } 
            return false;
    }

//    @Override
//    public boolean cambiarVerdad(Animal Anim) {
//        String sql = "UPDATE animal SET estado=? WHERE id=?";
//        try {
//            con = cn.getConnection();
//            ps = con.prepareStatement(sql);
//            ps.setInt(1, Anim.getEstado());
//            ps.setInt(2, Anim.getId());
//            ps.executeUpdate();
//            return true;
//        } catch (Exception e) {
//            System.err.println("Error al editar el lote: " + e);
//        } 
//            return false;
//    }

    @Override
    public boolean eliminar(int AnimalId) {
        String sql = "DELETE FROM animal WHERE id="+AnimalId;
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
