
<% 
  // Verifica si los parámetros están presentes antes de intentar convertirlos
        int animal_id = Integer.parseInt(request.getParameter("animal_id"));
        request.setAttribute("animal_id", animal_id);
        String numAnimal = request.getParameter("numAnimal");
%>
