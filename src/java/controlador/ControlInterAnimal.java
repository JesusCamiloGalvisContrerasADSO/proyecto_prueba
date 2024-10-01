
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
    String edit = "html/InterAnimal/edit.jsp";
    Pesos pesos = new Pesos();
    PesosDAO dao = new PesosDAO();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
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
        
        //esas son condiciones las cuales evaluan la accion que se manda desde la vista
        //y dependiendo de esta realiza la accion
        if (action.equalsIgnoreCase("listar")) {
            acceso = listar;
        } else if (action.equalsIgnoreCase("add")) {
            acceso = add;
        }else if (action.equalsIgnoreCase("AgregarPeso")) {
            try {
                int animalId = Integer.parseInt(request.getParameter("animal_id"));
                String numAnimal = request.getParameter("numAnimal");
                int numLote = Integer.parseInt(request.getParameter("numLote"));
                int idLote = Integer.parseInt(request.getParameter("idLote"));
                float peso = Float.parseFloat(request.getParameter("peso"));

                // Set the dynamic values to the pesos object
                pesos.setAnimal_id(animalId);
                pesos.setPeso(peso);

                // Obtener la fecha actual
                Date date = new Date(); 
                pesos.setFechaPeso(date);

                //obtenemos la descripcion
                pesos.setDescripcion("");

                // Agregar el peso a la base de datos
                dao.add(pesos);

                // Redirigir a la acción 'listar' con los valores dinámicos
                response.sendRedirect("ControlAnimal?accion=listar&id=" + idLote + "&num=" + numLote);

            } catch (NumberFormatException e) {
                System.err.println("Error al parsear los parámetros: " + e.getMessage());
                // Manejo de error, redirigir a una página de error o mostrar un mensaje de error
            }
            return;
        }else if (action.equalsIgnoreCase("ModifiDesc")) {
            request.setAttribute("id_peso", request.getParameter("id_peso"));
            acceso = edit;
        }

        
//        con el requestDispatcher permite que se pueda viajer entre paginas y encuentre las
//        rutas de manera correcta, se llama al request y response
        RequestDispatcher vista = request.getRequestDispatcher(acceso);
        vista.forward(request, response);
        
    }

//        El método POST introduce los parámetros en la solicitud HTTP para el servidor.
//        Por ello, no quedan visibles para el usuario. Además, la capacidad del
//        método POST es ilimitada
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        se crean las variables de acceso y a action se le asigna la accion 
//       que le manda el boton o enlace al que estamos dando click  
        String acceso = "";
        String action = request.getParameter("accion");
        
        if (action.equalsIgnoreCase("Agregar")) {
            
            int numLote = Integer.parseInt(request.getParameter("txtNumLote"));
            int idLote = Integer.parseInt(request.getParameter("txtIdLote"));
            
            String numAnimal = request.getParameter("txtNumAnimal");
            
            int idAnimal = Integer.parseInt(request.getParameter("txtIdAnimal"));
            float peso = Float.parseFloat(request.getParameter("txtPeso"));
            String desc = request.getParameter("descripcion");

            // Set the dynamic values to the pesos object
            pesos.setAnimal_id(idAnimal);
            pesos.setPeso(peso);

            // Obtener la fecha actual
            Date date = new Date(); 
            pesos.setFechaPeso(date);

            //obtenemos la descripcion
            pesos.setDescripcion(desc);

            // Agregar el peso a la base de datos
            dao.add(pesos);
            
            
// Después de agregar el animal, redirigir a la acción 'listar' con el id del lote
            response.sendRedirect("ControlInterAnimal?accion=listar&id="+ idLote +"&num="+ numLote +"&animal_id="+idAnimal+"&numAnimal="+numAnimal);
            return; 
            
        }else if (action.equalsIgnoreCase("ActualizarDesc")) {
            request.setCharacterEncoding("UTF-8");  // Asegura que el request use UTF-8
            
            int idAnimal = Integer.parseInt(request.getParameter("txtIdAnimal"));
            String numAnimal = request.getParameter("txtNumAnimal");
            int numLote = Integer.parseInt(request.getParameter("txtNumLote"));
            int idLote = Integer.parseInt(request.getParameter("txtIdLote"));
            int idPeso = Integer.parseInt(request.getParameter("txtidPeso"));
            String desc = request.getParameter("descripcion");
                
            // Set the dynamic values to the pesos object
            pesos.setIdPesos(idPeso);

            //obtenemos la descripcion
            pesos.setDescripcion(desc);
            
            System.out.println("esta monda no prende "+idPeso+" "+desc);
            
            dao.edit(pesos);
                
            // Redirigir a la acción 'listar' con los valores dinámicos
            response.sendRedirect("ControlInterAnimal?accion=listar&id="+ idLote +"&num="+ numLote +"&animal_id="+idAnimal+"&numAnimal="+numAnimal);
            return;
        }

    }
    

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
