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

//    aqui se declaran las varibles que van a guardar las rutas, tambien estaran las instancias 
//    de los modelos los cuales se van a acceder para poder realizar el proceso del crud
    String listar = "html/lote/listar.jsp";
    String add = "html/lote/add.jsp";
    String edit = "html/lote/edit.jsp";
    String papelera = "html/Papelera/Lotes/listar.jsp";
    String acciones = "AccionesAdmin.jsp";
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
        if (action.equalsIgnoreCase("listar")) {
            
            acceso = listar;
        } else if (action.equalsIgnoreCase("add")) {
            acceso = add;
        } else if (action.equalsIgnoreCase("editar")) {
            request.setAttribute("idLote", request.getParameter("id"));
            acceso = edit;
        } else if (action.equalsIgnoreCase("acciones")) {
            acceso = acciones;
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
         if (action.equalsIgnoreCase("Actualizar")) {
            int id = Integer.parseInt(request.getParameter("txtid"));
            int est = Integer.parseInt(request.getParameter("txtEst"));
            int num = Integer.parseInt(request.getParameter("txtNum"));
            lot.setId(id);
            lot.setEst(est);
            lot.setNum(num);
            boolean respuesta = dao.edit(lot);
            
            if(respuesta){
                acceso = listar;
            }else{
                // Redirigir a la página de agregar lote con un mensaje de error
                request.setAttribute("error", "No se pudo modificar el lote. Verifique los datos.");
                acceso = listar;
            }
            
        }else if (action.equalsIgnoreCase("Agregar")) {
            int num = Integer.parseInt(request.getParameter("txtNum"));
            int estado = 1;
            lot.setNum(num);
            lot.setEst(estado);
            boolean respuesta = dao.add(lot);
            
            if(respuesta){
                acceso = listar;
            }else{
                // Redirigir a la página de agregar lote con un mensaje de error
                request.setAttribute("error", "No se pudo agregar el lote. Verifique los datos.");
                acceso = add;  // Vuelve a la página de agregar lote
            }
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
