
package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Animal;
import modelo.LoteM;
import modelo.Pesos;
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
                acceso = listar;
                
            } if (action.equalsIgnoreCase("listarPapelera")) {
                acceso = papelera;
            }else if (action.equalsIgnoreCase("add")) {
                acceso = add;
            }else if (action.equalsIgnoreCase("editar")) {
            request.setAttribute("idAnimal", request.getParameter("id"));
            acceso = edit;
            } else if (action.equalsIgnoreCase("cambiarVerdad")) {
            //aqui se captura el lote en el cual se encuentra el animal, es para poder 
            //enviarlo al lote donde esta el animal
            int numLote = Integer.parseInt(request.getParameter("numLoteidLote").trim());
            int idlote = Integer.parseInt(request.getParameter("idLote").trim());
            
            try {
                int AnimalId = Integer.parseInt(request.getParameter("id"));
                Anim.setId(AnimalId);
                Anim.setEstado(1); 
                dao.cambiarFalse(Anim);

            } catch (NumberFormatException e) {
                request.setAttribute("error", "ID de lote inválido.");
            }
        // Después de agregar el animal, redirigir a la acción 'listar' con el id del lote
            response.sendRedirect("ControlAnimal?accion=listarPapelera&id="+idlote+"&num="+numLote);
            return; 
            
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
            
            int idlote = Integer.parseInt(request.getParameter("txtid"));
            int numLote = Integer.parseInt(request.getParameter("txtnumLote"));
            String numero = request.getParameter("txtnum");
            int raza = Integer.parseInt(request.getParameter("txtRaza"));
            int sexo = Integer.parseInt(request.getParameter("txtSexo"));
            int salud = Integer.parseInt(request.getParameter("txtsalud"));
            float peso = Float.parseFloat(request.getParameter("txtpeso"));

            Anim.setLote_id(idlote);
            Anim.setNum(numero);
            Anim.setRaza_id(raza);
            Anim.setTipo_sexo(sexo);
            Anim.setSalud_id(salud);
            
            Date date = new Date(); 
            Anim.setFechaCompra(date);
            
            LoteM lote = new LoteM();
            lote.setNum(numLote);
            
            Anim.setLote(lote);
            
            Pesos pesos = new Pesos();
            pesos.setPeso(peso);
            pesos.setFechaPeso(date);
            
            Anim.setPesos(pesos);
            
            dao.add(Anim);
            
            // Después de agregar el animal, redirigir a la acción 'listar' con el id del lote
            response.sendRedirect("ControlAnimal?accion=listar&id=" + idlote +"&num=" + numLote);
            return; 
        } else if (action.equalsIgnoreCase("Actualizar")) {
            
            //aqui se captura el lote en el cual se encuentra el animal, es para poder 
            //enviarlo al lote donde esta el animal
            int numLote = Integer.parseInt(request.getParameter("txtnumLote"));
            int idlote = Integer.parseInt(request.getParameter("txtidLote"));
            
            //estos son los datos que se van a enviar para poder actualizar el animal
            int id = Integer.parseInt(request.getParameter("txtid"));
            int lote = Integer.parseInt(request.getParameter("txtLote"));
            int idsalud = Integer.parseInt(request.getParameter("txtsalud"));
            
            //aqui se almacenan en los set y posteriormente se envian al dao con el metodo
            //de edit
            Anim.setId(id);
            Anim.setLote_id(lote);
            Anim.setSalud_id(idsalud);
            dao.edit(Anim);
            
            // Después de agregar el animal, redirigir a la acción 'listar' con el id del lote
            response.sendRedirect("ControlAnimal?accion=listar&id=" + idlote +"&num=" + numLote);
            return;
        }else if (action.equalsIgnoreCase("cambiarFalse")) {
            //aqui se captura el lote en el cual se encuentra el animal, es para poder 
            //enviarlo al lote donde esta el animal
            int numLote = Integer.parseInt(request.getParameter("txtnumLote"));
            int idlote = Integer.parseInt(request.getParameter("txtidLote"));
            
            String[] selectedAnimales = request.getParameterValues("selectedAnimales");

            if (selectedAnimales != null) {
                for (String id : selectedAnimales) {
                    int AnimalId = Integer.parseInt(id);
                    Anim.setId(AnimalId);
                    Anim.setEstado(0);
                    dao.cambiarFalse(Anim);
                }
            }

            // Después de agregar el animal, redirigir a la acción 'listar' con el id del lote
            response.sendRedirect("ControlAnimal?accion=listar&id=" + idlote +"&num=" + numLote);
            return; 
            
        }else if (action.equalsIgnoreCase("eliminar")) {
            //aqui se captura el lote en el cual se encuentra el animal, es para poder 
            //enviarlo al lote donde esta el animal
            int numLote = Integer.parseInt(request.getParameter("txtnumLote"));
            int idlote = Integer.parseInt(request.getParameter("txtidLote"));
            
            String[] selectedAnimales = request.getParameterValues("selectedAnimales");

            if (selectedAnimales != null) {
                for (String id : selectedAnimales) {
                try {
                    int AnimalId = Integer.parseInt(id);
                    Anim.setId(AnimalId);
                    dao.eliminar(AnimalId);
                } catch (Exception e) {
                    request.setAttribute("error", "ID de lote inválido: " + e);
                }
            }
            }

            // Después de agregar el animal, redirigir a la acción 'listar' con el id del lote
            response.sendRedirect("ControlAnimal?accion=listarPapelera&id="+idlote+"&num="+numLote);
            return; 
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
