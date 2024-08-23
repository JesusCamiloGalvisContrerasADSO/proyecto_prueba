
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

//    aqui se declaran las varibles que van a guardar las rutas, tambien estaran las instancias 
//    de los modelos los cuales se van a acceder para poder realizar el proceso del crud
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

    
//aqui en el metodo doGet se estan manejando las peticiones de volver, retorna a 
//  una pagina exclusivamente de administrador

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
        if (action.equalsIgnoreCase("listarPapelera")) {
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
//        con el requestDispatcher permite que se pueda viajer entre paginas y encuentre las
//        rutas de manera correcta, se llama al request y response
        RequestDispatcher vista = request.getRequestDispatcher(acceso);
        vista.forward(request, response);
    }

//    este es el metodo doPost donde se van a realizar los envios de informacion 
//    a la base de datos, con este metodo no se muestra en la url la accion ni los datos
//    que se estan realizando en la accion

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        se crean las variables de acceso y a action se le asigna la accion 
//       que le manda el boton o enlace al que estamos dando click   
        String acceso = "";
        String action = request.getParameter("accion");
        
//        aqui se realizan los ciclos en los cuales se determina la accion para poder 
//        hacer el envio o solicitud de datos, se manda ya sea al controlador o al 
//        modelo dao
        if (action.equalsIgnoreCase("eliminar")) {
            String[] selectedLotes = request.getParameterValues("selectedLotes");
            if (selectedLotes != null && selectedLotes.length > 0) {
            for (String id : selectedLotes) {
                try {
                    int loteId = Integer.parseInt(id);
                    lot.setId(loteId);
                    boolean eliminado = dao.eliminar(loteId);
                } catch (Exception e) {
                    request.setAttribute("error", "ID de lote inválido: " + e);
                }
            }
            } 
        acceso = listar;
        }
        
//        con el requestDispatcher permite que se pueda viajer entre paginas y encuentre las
//        rutas de manera correcta, se llama al request y response
            RequestDispatcher vista = request.getRequestDispatcher(acceso);
            vista.forward(request, response);
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
