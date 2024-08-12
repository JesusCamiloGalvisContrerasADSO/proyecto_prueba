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
        } 
        else if (action.equalsIgnoreCase("eliminar")) {
            int id = Integer.parseInt(request.getParameter("id"));
            lot.setId(id);
            dao.eliminar(id);
            acceso = listar;
        }

        RequestDispatcher vista = request.getRequestDispatcher(acceso);
        vista.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String acceso = "";
        String action = request.getParameter("accion");
    
if (action != null && action.equalsIgnoreCase("Actualizar")) {
        try {
            String idStr = request.getParameter("txtid");
            String numStr = request.getParameter("txtNum");

            if (idStr != null && numStr != null) {
                int id = Integer.parseInt(idStr);
                int num = Integer.parseInt(numStr);

                lot.setId(id);
                lot.setNum(num);

                dao.edit(lot);

                response.sendRedirect("controlLote?accion=listar");
            } else {
                // Manejo de error si los parámetros son nulos
                response.sendRedirect("html/lote/edit.jsp"); // Redirige a la página de edición en caso de error
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();  // Imprime el error en los logs para depuración
            response.sendRedirect("html/lote/edit.jsp");  // Redirige a la página de edición en caso de error
        }
    }else if (action.equalsIgnoreCase("Actualizar")) {
            
            int id = Integer.parseInt(request.getParameter("txtid"));
            int num = Integer.parseInt(request.getParameter("txtNum"));
            lot.setId(id);
            lot.setNum(num);
            dao.edit(lot);
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
