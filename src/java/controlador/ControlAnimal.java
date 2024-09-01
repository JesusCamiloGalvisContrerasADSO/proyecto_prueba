
package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Animal;
import modeloDAO.AnimalDAO;


public class ControlAnimal extends HttpServlet {

//    aqui se declaran las varibles que van a guardar las rutas, tambien estaran las instancias 
//    de los modelos los cuales se van a acceder para poder realizar el proceso del crud
    String listar = "html/Animales/listar.jsp";
    String add = "html/Animales/add.jsp";
    String edit = "html/Animales/edit.jsp";
    String papelera = "html/Papelera/animal/listar.jsp";
    Animal Anim = new Animal();
    AnimalDAO dao = new AnimalDAO();
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ControlAnimal</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ControlAnimal at " + request.getContextPath() + "</h1>");
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
                int idLote = Integer.parseInt(request.getParameter("id"));
                request.setAttribute("idLote", idLote);

                acceso = listar;
            } 
//        else if (action.equalsIgnoreCase("add")) {
//            acceso = add;
//        } else if (action.equalsIgnoreCase("editar")) {
//            request.setAttribute("idLote", request.getParameter("id"));
//            acceso = edit;
//        } 
        
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
