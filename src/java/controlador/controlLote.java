package controlador;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.io.BufferedReader;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import modelo.LoteM;
import modeloDAO.LoteDAO;

public class controlLote extends HttpServlet {

//    aqui se declaran las varibles que van a guardar las rutas, tambien estaran las instancias 
//    de los modelos los cuales se van a acceder para poder realizar el proceso del crud
    String listar = "html/lote/listar.jsp";
    String listar2 = "html/lotes_1/papus.jsp";
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
            
        } else if (action.equalsIgnoreCase("listarLotes")){
            try {
                // Llamar al método DAO que lista los lotes
                List<LoteM> lotes = dao.listar();

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
        }else if (action.equalsIgnoreCase("add")) {
            acceso = add;
        } else if (action.equalsIgnoreCase("editar")) {
            request.setAttribute("idLote", request.getParameter("id"));
            acceso = edit;
        } else if (action.equalsIgnoreCase("listarLote")) {
            request.setAttribute("idLote", request.getParameter("id"));
            int id = Integer.parseInt((String) request.getAttribute("idLote"));
            LoteM lote = dao.list(id);
            
            // Convertir la lista de lotes a JSON usando Gson
                Gson gson = new Gson();
                String lotesJson = gson.toJson(lote);
                
                // Configurar la respuesta para devolver JSON
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                
                // Enviar el JSON como respuesta
                response.getWriter().write(lotesJson);
            
        }else if (action.equalsIgnoreCase("acciones")) {
            acceso = acciones;
        } else if (action.equalsIgnoreCase("lotes_1")){
            acceso = listar2;
        }
        
//        con el requestDispatcher permite que se pueda viajer entre paginas y encuentre las
//        rutas de manera correcta, se llama al request y response
        if(!action.equalsIgnoreCase("listarLotes") && !action.equalsIgnoreCase("listarLote")){
            RequestDispatcher vista = request.getRequestDispatcher(acceso);
            vista.forward(request, response);
        }
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

            response.setContentType("application/json");
            PrintWriter out = response.getWriter();

            if (respuesta) {
                out.print("{\"success\": true, \"message\": \"Lote actualizado con éxito.\"}");
            } else {
                out.print("{\"success\": false, \"message\": \"No se pudo modificar el lote. Verifique los datos.\"}");
            }
            out.flush();
        }else if (action.equalsIgnoreCase("Agregar")) {
            // Leer los datos enviados en la solicitud como JSON
            BufferedReader reader = request.getReader();
            Gson gson = new Gson();

            // Convertir la entrada JSON a un objeto Lote
            LoteM lot = gson.fromJson(reader, LoteM.class);

            // Establecer el estado inicial del lote
            lot.setEst(1);

            // Llamar al método para agregar el lote en el DAO
            boolean respuesta = dao.add(lot);

            // Enviar la respuesta según el resultado de la operación
            if (respuesta) {
                response.setStatus(HttpServletResponse.SC_OK);
                response.getWriter().write("{\"message\": \"Lote agregado con éxito\"}");
            } else {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().write("{\"error\": \"No se pudo agregar el lote. Verifique los datos.\"}");
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
        if (!action.equalsIgnoreCase("Agregar") && !action.equalsIgnoreCase("Actualizar")) {
            RequestDispatcher vista = request.getRequestDispatcher(acceso);
            vista.forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
    

@Override
protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setContentType("application/json");
    PrintWriter out = response.getWriter();
    Gson gson = new Gson();
    LoteDAO dao = new LoteDAO();
    
    //        se crean las variables de acceso y a action se le asigna la accion 
//       que le manda el boton o enlace al que estamos dando click  
        String acceso = "";
        String action = request.getParameter("accion");

        if(action.equalsIgnoreCase("actualizar")){
            
            try {
                // Leer el cuerpo de la solicitud JSON
                BufferedReader reader = request.getReader();
                LoteM lot = gson.fromJson(reader, LoteM.class);

                // Actualizar el lote usando el DAO
                boolean respuesta = dao.edit(lot);

                // Crear la respuesta JSON
                JsonObject jsonResponse = new JsonObject();
                jsonResponse.addProperty("success", respuesta);
                jsonResponse.addProperty("message", respuesta ? "Lote actualizado con éxito." : "No se pudo modificar el lote. Verifique los datos.");

                // Enviar la respuesta JSON
                out.print(gson.toJson(jsonResponse));
            } catch (Exception e) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                JsonObject errorResponse = new JsonObject();
                errorResponse.addProperty("success", false);
                errorResponse.addProperty("message", "Error al procesar la solicitud: " + e.getMessage());
                out.print(gson.toJson(errorResponse));
            } finally {
                out.flush();
            }
        }else if(action.equalsIgnoreCase("cambiarFalse")){
             try {
                // Leer el cuerpo de la solicitud JSON
                BufferedReader reader = request.getReader();
                JsonObject json = gson.fromJson(reader, JsonObject.class);

                // Obtener la lista de IDs desde el JSON
                JsonArray idsArray = json.getAsJsonArray("selectedLotes");
                List<Integer> ids = new ArrayList<>();

                for (JsonElement element : idsArray) {
                    ids.add(element.getAsInt());
                }

                // Actualizar el estado de cada lote usando el DAO
                boolean allUpdated = true;
                for (int id : ids) {
                    LoteM lot = new LoteM();
                    lot.setId(id);
                    lot.setEst(0); // Cambiar el estado a 0 para mover a la papelera
                    boolean updated = dao.cambiarFalse(lot);
                    if (!updated) {
                        allUpdated = false;
                        break;
                    }
                }

                // Crear la respuesta JSON
                JsonObject jsonResponse = new JsonObject();
                jsonResponse.addProperty("success", allUpdated);
                jsonResponse.addProperty("message", allUpdated ? "Lotes enviados a la papelera con éxito." : "No se pudo mover algunos lotes a la papelera.");

                // Enviar la respuesta JSON
                out.print(gson.toJson(jsonResponse));
            } catch (Exception e) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                JsonObject errorResponse = new JsonObject();
                errorResponse.addProperty("success", false);
                errorResponse.addProperty("message", "Error al procesar la solicitud: " + e.getMessage());
                out.print(gson.toJson(errorResponse));
            } finally {
                out.flush();
            }
        }
            
}
}
