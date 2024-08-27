package controlador;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.TipoDocum;
import modelo.TipoSangre;
import modelo.Usuario;
import modeloDAO.UsuarioDAO;


public class ControlUsuario extends HttpServlet {

//    aqui se declaran las varibles que van a guardar las rutas, tambien estaran las instancias 
//    de los modelos los cuales se van a acceder para poder realizar el proceso del crud
    String listar = "html/Usuarios/listar.jsp";
    String add = "html/Usuarios/add.jsp";
    String edit = "html/Usuarios/edit.jsp";
    String index = "index.jsp";
    String confir = "html/Usuarios/ConfirRegistro.jsp";
    Usuario user = new Usuario();
    UsuarioDAO dao = new UsuarioDAO();


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet controlador</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet controlador at " + request.getContextPath() + "</h1>");
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
        System.out.println("Accion: " + action);

//        aqui se realizan los ciclos en los cuales se determina la accion para poder 
//        hacer el envio o solicitud de datos, se manda ya sea al controlador o al 
//        modelo dao
        if (action.equalsIgnoreCase("listar")) {
            acceso = listar;
        } 
        else if (action.equalsIgnoreCase("add")) {
            acceso = add;
        } 
        
        else if (action.equalsIgnoreCase("editar")) {
            request.setAttribute("idTipo", request.getParameter("id"));
            acceso = edit;
        } 
//          else if (action.equalsIgnoreCase("eliminar")) {
//            int id = Integer.parseInt(request.getParameter("id"));
//            Tip.setId(id);
//            dao.eliminar(id);
//            acceso = listar;
//        } 
        


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
        System.out.println("Accion: " + action);
    
//        aqui se realizan los ciclos en los cuales se determina la accion para poder 
//        hacer el envio o solicitud de datos, se manda ya sea al controlador o al 
//        modelo dao
        if (action.equalsIgnoreCase("Agregar")) {
            String nom = request.getParameter("txtNom");
            String apell = request.getParameter("txtApell");

            // Obtener el valor correcto de 'txtNumDoc' pasando el string a long
            String numDocStr = request.getParameter("txtNumDoc");
            Long numDoc = Long.parseLong(numDocStr);  // Aquí convertimos el string a Long

            int tipoDoc = Integer.parseInt(request.getParameter("txtTipDoc"));
            int tipoSan = Integer.parseInt(request.getParameter("txtTipSang"));

            // Obtener el valor correcto de 'txtTel'
            String telStr = request.getParameter("txtTel");
            Long tel = Long.parseLong(telStr);  // Aquí convertimos el string a Long

            String contra = request.getParameter("txtContra");
            String correo = request.getParameter("txtCorreo");

            user.setDocumento(numDoc);
            user.setContrasena(contra);
            user.setNombre(nom);
            user.setApellido(apell);
            user.setTelefono(tel);
            user.setEmail(correo);
//            user.setRol(0);

            Date date = new Date(); 
            user.setFechaContra(date);

            // Mapeo del TipoDocumento
            user.setDocid(tipoDoc);

            // Mapeo del TipoSangre
            user.setSanid(tipoSan);

            dao.add(user);
            acceso = confir;
        } else if (action.equalsIgnoreCase("index")){
            acceso = index;
        } else if (action.equalsIgnoreCase("Ingresar")) {
            String documentoStr = request.getParameter("txtDocum");
            Long numDoc = Long.parseLong(documentoStr);
            String contrasena = request.getParameter("txtContra");

            user.setDocumento(numDoc);
            user.setContrasena(contrasena);

            boolean isValid = dao.VerificarLogin(user);

            if (isValid) {
                // Redirigir a la página de inicio o dashboard
                request.getSession().setAttribute("user", user);
                acceso = "html/lote/listar.jsp";
//                acceso = listar;
//                response.sendRedirect("home.jsp");
            } else {
                // Redirigir a la página de login con un mensaje de error
                request.setAttribute("error", "Credenciales inválidas");
                acceso = "index.jsp";
            }
        }else if (action.equalsIgnoreCase("Actualizar")) {
            
            int idUser = Integer.parseInt(request.getParameter("txtid"));
            String nom = request.getParameter("txtNom");
            String apell = request.getParameter("txtApell");
            String contra = request.getParameter("txtContra");
            int rol = Integer.parseInt(request.getParameter("txtRol"));

            // Obtener el valor correcto de 'txtNumDoc' pasando el string a long
            String numDocStr = request.getParameter("txtNumDoc");
            Long numDoc = Long.parseLong(numDocStr);  // Aquí convertimos el string a Long

            int tipoDoc = Integer.parseInt(request.getParameter("txtTipDoc"));
            int tipoSan = Integer.parseInt(request.getParameter("txtTipSang"));

            // Obtener el valor correcto de 'txtTel'
            String telStr = request.getParameter("txtTel");
            Long tel = Long.parseLong(telStr);  // Aquí convertimos el string a Long
            
            String correo = request.getParameter("txtCorreo");
            
            user.setIdUsuario(idUser);
            user.setDocumento(numDoc);
            user.setContrasena(contra);
            user.setNombre(nom);
            user.setApellido(apell);
            user.setTelefono(tel);
            user.setEmail(correo);
            user.setRol(rol);
//            user.setRol(0);

            // Mapeo del TipoDocumento
            user.setDocid(tipoDoc);

            // Mapeo del TipoSangre
            user.setSanid(tipoSan);
            dao.edit(user);
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
    }
}
