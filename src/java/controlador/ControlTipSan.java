
package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.TipoSangre;
import modeloDAO.TipoSangreDAO;


public class ControlTipSan extends HttpServlet {

    String listar = "html/TipoSangre/listar.jsp";
    String add = "html/TipoSangre/add.jsp";
    String edit = "html/TipoSangre/edit.jsp";
    TipoSangre Tip = new TipoSangre();
    TipoSangreDAO dao = new TipoSangreDAO();

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
        } else if (action.equalsIgnoreCase("add")) {
            acceso = add;
        }  else if (action.equalsIgnoreCase("editar")) {
            request.setAttribute("idTipo", request.getParameter("id"));
            acceso = edit;
        }  else if (action.equalsIgnoreCase("eliminar")) {
            int id = Integer.parseInt(request.getParameter("id"));
            Tip.setId(id);
            dao.eliminar(id);
            acceso = listar;
        }
        System.out.println("Acceso: " + acceso);
        RequestDispatcher vista = request.getRequestDispatcher(acceso);
        vista.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
        String acceso = "";
        String action = request.getParameter("accion");
        System.out.println("Accion: " + action);
        
        if (action.equalsIgnoreCase("Agregar")) {
            String nom = request.getParameter("txtNom");
            Tip.setNom(nom);
            dao.add(Tip);
            acceso = listar;
        }else if (action.equalsIgnoreCase("Actualizar")) {
            int id = Integer.parseInt(request.getParameter("txtid"));
            String nom = request.getParameter("txtNom");
            Tip.setId(id);
            Tip.setNom(nom);
            dao.edit(Tip);
            acceso = listar;
        }
        
        System.out.println("Acceso: " + acceso);
        RequestDispatcher vista = request.getRequestDispatcher(acceso);
        vista.forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
