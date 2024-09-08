
package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.TipoDocum;
import modelo.TipoSangre;
import modelo.Usuario;
import modeloDAO.UsuarioDAO;


public class ControlPerfil extends HttpServlet {

    String listar = "html/Perfil/listar.jsp";
    String edit = "html/Perfil/edit.jsp";
    
    Usuario user = new Usuario();
    UsuarioDAO dao = new UsuarioDAO();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ControlPerfil</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ControlPerfil at " + request.getContextPath() + "</h1>");
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
        
//        aqui se realizan los ciclos en los cuales se determina la accion para poder 
//        hacer el envio o solicitud de datos, se manda ya sea al controlador o al 
//        modelo dao
        if (action.equalsIgnoreCase("listar")) {
            request.setAttribute("idUser", request.getParameter("id"));
            acceso = listar;
        } else if (action.equalsIgnoreCase("editar")) {
            request.setAttribute("idUser", request.getParameter("id"));
            acceso = edit;
        }
        
//        con el requestDispatcher permite que se pueda viajer entre paginas y encuentre las
//        rutas de manera correcta, se llama al request y response
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
        
        if (action.equalsIgnoreCase("Actualizar")) {
            // Obtener el idPerfil de la sesión
            HttpSession session = request.getSession();
            Usuario user = (Usuario) session.getAttribute("user");
            
            int idUser = user.getIdUsuario();
            
            
            String contra = request.getParameter("txtContra");

            int tipoDoc = Integer.parseInt(request.getParameter("txtTipDoc"));

            String telStr = request.getParameter("txtTel");
            Long tel = Long.parseLong(telStr);

            String correo = request.getParameter("txtCorreo");

            // Inicializar y asignar TipoDocum y TipoSangre al usuario
            TipoDocum tipoDocum = new TipoDocum();
            tipoDocum.setId(tipoDoc);
            user.setTipoDocum(tipoDocum);

            TipoSangre tipoSangre = new TipoSangre();
            user.setTipoSangre(tipoSangre);

            user.setIdUsuario(idUser);
            user.setContrasena(contra);
            user.setTelefono(tel);
            user.setEmail(correo);

            dao.editPerfil(user);
            response.sendRedirect("ControlPerfil?accion=listar&id=" + idUser);
            return; // Asegúrate de no ejecutar más código después de la redirección
            
        }
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
