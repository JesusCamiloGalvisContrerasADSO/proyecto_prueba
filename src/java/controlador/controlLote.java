package controlador;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import modelo.LoteM;
import modeloDAO.LoteDAO;

public class controlLote extends HttpServlet {

    String listar = "html/lote/listar.jsp";
    String add = "html/lote/add.jsp";
    String edit = "html/lote/edit.jsp";
    LoteM lot = new LoteM();
    LoteDAO dao = new LoteDAO();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet controlLote</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet controlLote at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String acceso = "";
        String action = request.getParameter("accion");

        if (action.equalsIgnoreCase("listar")) {
            
            acceso = listar;
        } else if (action.equalsIgnoreCase("add")) {
            acceso = add;
        } else if (action.equalsIgnoreCase("editar")) {
            request.setAttribute("idLote", request.getParameter("id"));
            acceso = edit;
        } else if (action.equalsIgnoreCase("cambiarFalse")) {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            lot.setId(id);
            lot.setEst(0);
            dao.cambiarFalse(lot);
            
        } catch (NumberFormatException e) {
            request.setAttribute("error", "ID de lote inválido.");
        }
        acceso = listar;
        }
        
//        else if (action.equalsIgnoreCase("eliminar")) {
//        try {
//            int id = Integer.parseInt(request.getParameter("id"));
//            lot.setId(id);
//            boolean eliminado = dao.eliminar(id);
//            if (!eliminado) {
//                // Manejar el caso en que no se pudo eliminar, quizás debido a la conexión
//                request.setAttribute("error", "No se pudo eliminar el lote con ID: " + id);
//            }
//        } catch (NumberFormatException e) {
//            request.setAttribute("error", "ID de lote inválido.");
//        }
//        acceso = listar;
//        }

        RequestDispatcher vista = request.getRequestDispatcher(acceso);
        vista.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String acceso = "";
        String action = request.getParameter("accion");
    
         if (action.equalsIgnoreCase("Actualizar")) {
            int id = Integer.parseInt(request.getParameter("txtid"));
            int est = Integer.parseInt(request.getParameter("txtEst"));
            int num = Integer.parseInt(request.getParameter("txtNum"));
            lot.setId(id);
            lot.setEst(est);
            lot.setNum(num);
            dao.edit(lot);
            acceso = listar;
        }else if (action.equalsIgnoreCase("Agregar")) {
            int num = Integer.parseInt(request.getParameter("txtNum"));
            int estado = 1;
            lot.setNum(num);
            lot.setEst(estado);
            dao.add(lot);
            acceso = listar;
        }
        
        
        RequestDispatcher vista = request.getRequestDispatcher(acceso);
        vista.forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
