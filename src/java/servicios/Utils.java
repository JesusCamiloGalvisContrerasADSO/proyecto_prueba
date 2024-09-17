package servicios; // Reemplaza esto con el paquete correcto de tu proyecto

import org.mindrot.jbcrypt.BCrypt;

public class Utils {
    // Método para encriptar la contraseña
    public static String encriptarBCrypt(String contra) {
        // Genera el hash de la contraseña con un salt automático
        return BCrypt.hashpw(contra, BCrypt.gensalt());
    }

    // Método para verificar la contraseña
    public static boolean verificarBCrypt(String contra, String contraEncriptada) {
        return BCrypt.checkpw(contra, contraEncriptada);
    }
}
