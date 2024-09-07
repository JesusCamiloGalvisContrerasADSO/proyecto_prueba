
package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ControlPerfil extends HttpServlet {

    String listar = "html/Perfil/listar.jsp";
    String edit = "html/Perfil/edit.jsp";

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
        processRequest(request, response);
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
