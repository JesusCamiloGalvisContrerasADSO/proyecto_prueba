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
    String papelera = "html/Papelera/Lotes/listar.jsp";
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
        } 
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
        }else if (action.equalsIgnoreCase("cambiarFalse")) {
            String[] selectedLotes = request.getParameterValues("selectedLotes");

            if (selectedLotes != null) {
                for (String id : selectedLotes) {
                    int loteId = Integer.parseInt(id);
                    lot.setId(loteId);
                    lot.setEst(0); // Cambiar el estado a false (0)
                    dao.cambiarFalse(lot);
                }
            }

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
