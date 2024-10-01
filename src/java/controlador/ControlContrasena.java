/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Usuario;
import modeloDAO.UsuarioDAO;
import servicios.Utils;

/**
 *
 * @author Aprendiz
 */
public class ControlContrasena extends HttpServlet {

//    aqui se declaran las varibles que van a guardar las rutas, tambien estaran las instancias 
//    de los modelos los cuales se van a acceder para poder realizar el proceso del crud
    String listar = "html/Usuarios/listar.jsp";
    String edit = "html/contrasena/edit.jsp";

    Usuario user = new Usuario();
    UsuarioDAO dao = new UsuarioDAO();
    
    //    método generado por defecto en un servlet de Java llamado processRequest. 
//    El método maneja tanto solicitudes HTTP GET como POST y genera una 
//    respuesta HTML simple
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ControlContrasena</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ControlContrasena at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //        se crean las variables de acceso y a action se le asigna la accion 
        //       que le manda el boton o enlace al que estamos dando click  
        String acceso = "";
        String action = request.getParameter("accion");
        System.out.println("Accion: " + action);
        
        //        aqui se realizan los ciclos en los cuales se determina la accion para poder 
        //        hacer el envio o solicitud de datos, se manda ya sea al controlador o al 
        //        modelo dao
        if (action.equalsIgnoreCase("editar")) {
            acceso = edit;
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
        
        if (action.equalsIgnoreCase("Actualizar")) {

        // Obtener el valor correcto de 'txtNumDoc' pasando el string a long
        int id_user = Integer.parseInt(request.getParameter("id"));

        // Encriptar la contraseña antes de guardarla
        String contra = request.getParameter("txtContra");
        String contraEncriptada = Utils.encriptarBCrypt(contra);

        //se guarda en los metodos publicos setters, se almcena en el modelo usuario 
        //y se manda al dao este modelo y con su respectiva accion
        
        user.setIdUsuario(id_user);  
        user.setContrasena(contraEncriptada); // Guarda la contraseña encriptada

        //aqui es donde se manda el dao, primero el nombre de la funcion y luego el modelo
        dao.actualizarContra(user);
        
        
        //se redirige a la pagina 
        acceso = listar;
    
    }
        
        RequestDispatcher vista = request.getRequestDispatcher(acceso);
        vista.forward(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
