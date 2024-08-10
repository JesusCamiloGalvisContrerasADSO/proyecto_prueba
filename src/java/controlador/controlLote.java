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
        } else if (action.equalsIgnoreCase("Agregar")) {
            // Obtener el valor del parámetro y convertirlo a entero
            String numStr = request.getParameter("txtNum");
            int num = Integer.parseInt(numStr);
            lot.setNum(num);
            dao.add(lot);
            acceso = listar;  // Redirige a la vista de listar después de agregar
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
        doGet(request, response);  // Redirige las solicitudes POST al manejo de GET
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
