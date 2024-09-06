<% 
    int idLote = Integer.parseInt(request.getParameter("id"));
    request.setAttribute("idLote", idLote);
                            
    int numLote = Integer.parseInt(request.getParameter("num"));
    request.setAttribute("numLote", numLote);
                            
%>
