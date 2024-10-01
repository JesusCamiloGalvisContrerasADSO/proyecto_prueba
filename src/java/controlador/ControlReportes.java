
package controlador;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Animal;
import modelo.LoteM;
import modeloDAO.ReportesDAO;


public class ControlReportes extends HttpServlet {

    //    aqui se declaran las varibles que van a guardar las rutas, tambien estaran las instancias 
//    de los modelos los cuales se van a acceder para poder realizar el proceso del crud
    String listar = "html/reportes/listar.jsp";
    
    LoteM lot = new LoteM();
    ReportesDAO dao = new ReportesDAO();
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
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
        if(action.equalsIgnoreCase("listars")){
            acceso = listar;
            RequestDispatcher vista = request.getRequestDispatcher(acceso);
            vista.forward(request, response);
        }else if (action.equalsIgnoreCase("listarLotes")) {
        try {
            // Llamar al método DAO que lista los lotes
            List<LoteM> lotes = dao.listarLotes();

            // Convertir la lista de lotes a JSON usando Gson
            Gson gson = new Gson();
            String lotesJson = gson.toJson(lotes);

//            System.out.println("JSON generado: " + lotesJson);
            
            // Configurar la respuesta para devolver JSON
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            // Enviar el JSON como respuesta
            response.getWriter().write(lotesJson);
        } catch (Exception e) {
            e.printStackTrace(); // Manejar cualquier error que ocurra
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR); // Enviar un error 500 si algo falla
        }
        
        }else if (action.equalsIgnoreCase("ConsultaLote")){
             int idLote = Integer.parseInt(request.getParameter("id"));
            List<Animal> Animal = dao.ConsultaLote(idLote);
            
            Gson gson = new Gson();
            String animalJson = gson.toJson(Animal);
            
            // Configurar la respuesta para devolver JSON
            response.setContentType("aplication/json");
            response.setCharacterEncoding("UTF-8");
            
            // Enviar el JSON como respuesta
            response.getWriter().write(animalJson);
            
            
        }
    
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
