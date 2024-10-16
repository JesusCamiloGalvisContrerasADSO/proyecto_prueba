
package controlador;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
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
        }else if (action.equalsIgnoreCase("listarPapeLote")) {
            try {
                // Llamar al método DAO que lista los lotes
                List<LoteM> lotes = dao.listarPapelera();

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
        }if (action.equalsIgnoreCase("eliminar")) {
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
        if(!action.equalsIgnoreCase("listarPapeLote")){
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

    @Override
protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // Configurar la respuesta para JSON
    response.setContentType("application/json");
    response.setCharacterEncoding("UTF-8");
    
    // Crear un objeto JSON para la respuesta
    JsonObject jsonResponse = new JsonObject();

    try {
        // Leer el cuerpo de la solicitud
        BufferedReader reader = request.getReader();
        StringBuilder jsonBuilder = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            jsonBuilder.append(line);
        }

        // Convertir el cuerpo de la solicitud a un JSON
        String json = jsonBuilder.toString();
        JsonObject jsonObject = JsonParser.parseString(json).getAsJsonObject();
        JsonArray idsArray = jsonObject.getAsJsonArray("ids");

        // Iterar sobre los IDs de los lotes y eliminarlos
        LoteDAO dao = new LoteDAO();
        boolean eliminacionExitosa = true;
        for (JsonElement idElement : idsArray) {
            try {
                int loteId = idElement.getAsInt();
                boolean eliminado = dao.eliminar(loteId);
                if (!eliminado) {
                    eliminacionExitosa = false;
                }
            } catch (NumberFormatException e) {
                eliminacionExitosa = false;
                request.setAttribute("error", "ID de lote inválido: " + e.getMessage());
            }
        }

        // Responder según el resultado de la eliminación
        if (eliminacionExitosa) {
            jsonResponse.addProperty("success", true);
            jsonResponse.addProperty("message", "Lotes eliminados con éxito.");
        } else {
            jsonResponse.addProperty("success", false);
            jsonResponse.addProperty("message", "Hubo un problema al eliminar uno o más lotes.");
        }
    } catch (Exception e) {
        jsonResponse.addProperty("success", false);
        jsonResponse.addProperty("message", "Error al procesar la solicitud: " + e.getMessage());
    }

    // Enviar la respuesta JSON al cliente
    response.getWriter().write(jsonResponse.toString());
}


    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        Gson gson = new Gson();
        LoteDAO dao = new LoteDAO();

        try {
            // Leer el cuerpo de la solicitud JSON
            BufferedReader reader = request.getReader();
            JsonObject json = gson.fromJson(reader, JsonObject.class);

            // Obtener el ID del lote desde el JSON
            int id = json.get("id").getAsInt();

            // Crear una instancia de LoteM y establecer los valores
            LoteM lot = new LoteM();
            lot.setId(id);
            lot.setEst(1); // Cambiar el estado a 1 para restaurar

            // Actualizar el estado del lote usando el DAO
            boolean respuesta = dao.cambiarFalse(lot); // Asegúrate de que este método actualice el estado correctamente

            // Crear la respuesta JSON
            JsonObject jsonResponse = new JsonObject();
            jsonResponse.addProperty("success", respuesta);
            jsonResponse.addProperty("message", respuesta ? "Lote restaurado con éxito." : "No se pudo restaurar el lote. Verifique los datos.");

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
