
package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Pesos;
import modeloDAO.PesosDAO;


public class ControlInterAnimal extends HttpServlet {

    //    aqui se declaran las varibles que van a guardar las rutas, tambien estaran las instancias 
//    de los modelos los cuales se van a acceder para poder realizar el proceso del crud
    String listar = "html/InterAnimal/listar.jsp";
    String add = "html/InterAnimal/add.jsp";
    Pesos pesos = new Pesos();
    PesosDAO dao = new PesosDAO();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ControlInterAnimal</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ControlInterAnimal at " + request.getContextPath() + "</h1>");
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
        
        if (action.equalsIgnoreCase("listar")) {
            acceso = listar;
        } else if (action.equalsIgnoreCase("add")) {
            acceso = add;
        }
        
//        con el requestDispatcher permite que se pueda viajer entre paginas y encuentre las
//        rutas de manera correcta, se llama al request y response
        RequestDispatcher vista = request.getRequestDispatcher(acceso);
        vista.forward(request, response);
        
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        se crean las variables de acceso y a action se le asigna la accion 
//       que le manda el boton o enlace al que estamos dando click  
        String acceso = "";
        String action = request.getParameter("accion");
        
        if (action.equalsIgnoreCase("Agregar")) {
            
            String numAnimal = request.getParameter("txtNumAnimal");
            
            int idAnimal = Integer.parseInt(request.getParameter("txtIdAnimal"));
            float peso = Float.parseFloat(request.getParameter("txtPeso"));

            pesos.setAnimal_id(idAnimal);
            pesos.setPeso(peso);
            
            Date date = new Date(); 
            pesos.setFechaPeso(date);
            
            dao.add(pesos);
            
            
// Después de agregar el animal, redirigir a la acción 'listar' con el id del lote
            response.sendRedirect("ControlInterAnimal?accion=listar&animal_id="+ idAnimal +"&numAnimal="+ numAnimal);
            return; 
        }

    }
    

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
