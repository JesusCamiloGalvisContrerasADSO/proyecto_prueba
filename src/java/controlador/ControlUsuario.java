package controlador;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.TipoDocum;
import modelo.TipoSangre;
import modelo.Usuario;
import modeloDAO.UsuarioDAO;


public class ControlUsuario extends HttpServlet {

    String listar = "html/Usuarios/listar.jsp";
    String add = "html/Usuarios/add.jsp";
    String edit = "html/Usuarios/edit.jsp";
    Usuario user = new Usuario();
    UsuarioDAO dao = new UsuarioDAO();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet controlador</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet controlador at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String acceso = "";
        String action = request.getParameter("accion");
        System.out.println("Accion: " + action);

        if (action.equalsIgnoreCase("listar")) {
            acceso = listar;
        } 
        else if (action.equalsIgnoreCase("add")) {
            acceso = add;
        } 
        else if (action.equalsIgnoreCase("Agregar")) {
            String nom = request.getParameter("txtNom");
            String apell = request.getParameter("txtApell");

            // Obtener el valor correcto de 'txtNumDoc'
            String numDocStr = request.getParameter("txtNumDoc");
            Long numDoc = Long.parseLong(numDocStr);  // Aquí convertimos el string a Long

            int tipoDoc = Integer.parseInt(request.getParameter("txtTipDoc"));
            int tipoSan = Integer.parseInt(request.getParameter("txtTipSang"));

            // Obtener el valor correcto de 'txtTel'
            String telStr = request.getParameter("txtTel");
            Long tel = Long.parseLong(telStr);  // Aquí convertimos el string a Long

            String contra = request.getParameter("txtContra");
            String correo = request.getParameter("txtCorreo");

            user.setDocumento(numDoc);
            user.setContrasena(contra);
            user.setNombre(nom);
            user.setApellido(apell);
            user.setTelefono(tel);
            user.setEmail(correo);

            Date date = new Date(); 
            user.setFechaContra(date);

            // Mapeo del TipoDocumento
            user.setDocid(tipoDoc);

            // Mapeo del TipoSangre
            user.setSanid(tipoSan);

            dao.add(user);
            acceso = listar;
        }
//        else if (action.equalsIgnoreCase("editar")) {
//            request.setAttribute("idTipo", request.getParameter("id"));
//            acceso = edit;
//        } else if (action.equalsIgnoreCase("Actualizar")) {
//            int id = Integer.parseInt(request.getParameter("txtid"));
//            String nom = request.getParameter("txtNom");
//            Tip.setId(id);
//            Tip.setNom(nom);
//            dao.edit(Tip);
//            acceso = listar;
//        } else if (action.equalsIgnoreCase("eliminar")) {
//            int id = Integer.parseInt(request.getParameter("id"));
//            Tip.setId(id);
//            dao.eliminar(id);
//            acceso = listar;
//        } 
        else if (action.equalsIgnoreCase("Ingresar")) {
            String documentoStr = request.getParameter("txtDocum");
            Long numDoc = Long.parseLong(documentoStr);
            String contrasena = request.getParameter("txtContra");

            user.setDocumento(numDoc);
            user.setContrasena(contrasena);

            boolean isValid = dao.VerificarLogin(user);

            if (isValid) {
                // Redirigir a la página de inicio o dashboard
                request.getSession().setAttribute("user", user);
                acceso = listar;
//                response.sendRedirect("home.jsp");
            } else {
                // Redirigir a la página de login con un mensaje de error
                request.setAttribute("error", "Credenciales inválidas");
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
        }



        RequestDispatcher vista = request.getRequestDispatcher(acceso);
        vista.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
