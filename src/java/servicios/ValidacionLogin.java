//
//package servicios;
//
//import modelo.Usuario;
//import modeloDAO.UsuarioDAO;
//
//
//public class ValidacionLogin {
//    
//    public Usuario validateLogin(long documento, String contrasena) {
//        UsuarioDAO usuarioDAO = new UsuarioDAO();
//        Usuario user = usuarioDAO.VerificarLogin(documento, contrasena);
//
//        if (user != null) {
//            return user; // Login successful
//        }
//        return null; // Invalid credentials
//    }
//    
//}
