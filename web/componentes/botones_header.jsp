<div class="encabezado">
                <ul class="encabezado__lista">
                    <li><a class="encabezado__lista--texto" href="controlLote?accion=listar">Lotes</a></li>
                    <%
                        if (rol == 1) {
                    %>
                    <li ><a class="encabezado__lista--texto" href="controlLote?accion=acciones" >Acciones</a></li>

                    <li ><a class="encabezado__lista--texto" href="ControlUsuario?accion=listar" >Usuarios</a></li>
                    <% } %>
                    <li class="menu-item">
                      <a href="#" class="menu-link icon-person texto--blanco"><i class="bi bi-person-circle"></i></a>
                      <ul class="submenu submenu--header">
                          <li class="submenu-item"><a href="ControlPerfil?accion=listar&id=<%= idPerfil %>" class="submenu-link submenu-link--header">Pefil</a></li>
                          <li class="submenu-item"><a href="ControlLogin?accion=cerrarSecion" class="submenu-link submenu-link--header">Cerrar secion</a></li>
                      </ul>
                    </li>
                    <%
                        if (rol == 1) {
                    %>
                    <li class="menu-item">
                        <a href="#" class="menu-link icon-person texto--blanco"><i class="bi bi-gear-fill"></i></a>
                        <ul class="submenu submenu--header">
                            <li class="submenu-item"><a href="ControlTipSan?accion=listar" class="submenu-link submenu-link--header">Tipos de Sangre</a></li>
                            <li class="submenu-item"><a href="ControlTipoDoc?accion=listar" class="submenu-link submenu-link--header ">Tipos de Documentos</a></li>
                            <li class="submenu-item "><a href="ControlRaza?accion=listar" class="submenu-link submenu-link--header ">Tipos de Razas</a></li>
                            <li class="submenu-item "><a href="ControlReportes?accion=listars" class="submenu-link submenu-link--header ">Reportes</a></li>
                        </ul>
                    </li>
                    <% } %>
                  </ul>
              </div>