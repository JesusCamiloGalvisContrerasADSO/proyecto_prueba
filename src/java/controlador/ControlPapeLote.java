
package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.LoteM;
import modeloDAO.LoteDAO;


public class ControlPapeLote extends HttpServlet {

    String listar = "html/Papelera/lote/listar.jsp";
    LoteM lot = new LoteM();
    LoteDAO dao = new LoteDAO();
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ControlPapeLote</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ControlPapeLote at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String acceso = "";
        String action = request.getParameter("accion");
        
        if (action.equalsIgnoreCase("listarPapelera")) {
            acceso = listar;
        }else if (action.equalsIgnoreCase("eliminar")) {
            try {
                int id = Integer.parseInt(request.getParameter("id"));
                lot.setId(id);
                boolean eliminado = dao.eliminar(id);
                if (!eliminado) {
                    // Manejar el caso en que no se pudo eliminar, quizás debido a la conexión
                    request.setAttribute("error", "No se pudo eliminar el lote con ID: " + id);
                }
            } catch (NumberFormatException e) {
                request.setAttribute("error", "ID de lote inválido.");
            }
            acceso = listar;
            
        }else if (action.equalsIgnoreCase("cambiarVerdad")) {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            lot.setId(id);
            lot.setEst(1);
            dao.cambiarFalse(lot);
            
        } catch (NumberFormatException e) {
            request.setAttribute("error", "ID de lote inválido.");
        }
        acceso = listar;
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
    }// </editor-fold>

}
