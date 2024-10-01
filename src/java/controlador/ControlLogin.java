
package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.Usuario;
import modeloDAO.LoginDAO;
import modeloDAO.UsuarioDAO;


public class ControlLogin extends HttpServlet {

//    aqui se declaran las varibles que van a guardar las rutas, tambien estaran las instancias 
//    de los modelos los cuales se van a acceder para poder realizar el proceso del crud
    String listar = "html/Usuarios/listar.jsp";
    String acciones = "AccionesAdmin.jsp";
    String index = "index.jsp";
    Usuario user = new Usuario();
    LoginDAO dao = new LoginDAO();

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ControlLogin</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ControlLogin at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        //        se crean las variables de acceso y a action se le asigna la accion 
//       que le manda el boton o enlace al que estamos dando click  
        String acceso = "";
        String action = request.getParameter("accion");
        
        if (action.equalsIgnoreCase("cerrarSecion")){
        // Obtener o crear la sesión
        HttpSession session = request.getSession(false);
        
        // Guardar el rol en la sesión
            session.setAttribute("rol", 0);
            session.setAttribute("user", user);
            
        acceso = index;
        }else if (action.equalsIgnoreCase("acciones")){
                acceso = acciones;
        }
        
        //esto es necesario para que pueda enviar a la vista que queremos
        //es para el manejo de la navegacion dentro del setvelt
        RequestDispatcher vista = request.getRequestDispatcher(acceso);
        vista.forward(request, response);
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
//        se crean las variables de acceso y a action se le asigna la accion 
//       que le manda el boton o enlace al que estamos dando click  
        String acceso = "";
        String action = request.getParameter("accion");
        
        // Código para manejar el inicio de sesión
        if (action.equalsIgnoreCase("Ingresar")) {
            String documentoStr = request.getParameter("txtDocum");
            Long numDoc = Long.parseLong(documentoStr);
            String contrasena = request.getParameter("txtContra");

            user.setDocumento(numDoc);
            user.setContrasena(contrasena);

            boolean isValid = dao.VerificarLogin(user);

            if (isValid) {
                    int rol = user.getRol(); // obtener el rol del usuario autenticado
                    int idPerfil = user.getIdUsuario(); 

                    // Obtener o crear la sesión
                    HttpSession session = request.getSession(true);

                    // Guardar el rol en la sesión
                    session.setAttribute("rol", rol);
                    session.setAttribute("idPerfil", idPerfil);
                    session.setAttribute("user", user);

                    // Redirigir según el rol
                    if (rol == 1) {
                        acceso = acciones; // Redirigir al dashboard del administrador
                    } else if (rol == 2) {
                        acceso = "html/lote/listar.jsp"; // Redirigir al dashboard de un usuario estándar
                    } else {
                        acceso = "index.jsp"; // Redirigir a la página de inicio de sesión si el rol no es reconocido
                    }
                } else {
                    // Redirigir a la página de login con un mensaje de error
                    request.setAttribute("error", "Credenciales inválidas");
                    acceso = "index.jsp";
                }

            }

        
        RequestDispatcher vista = request.getRequestDispatcher(acceso);
        vista.forward(request, response);
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
