

    // Función para inicializar los menús desplegables
    document.addEventListener('DOMContentLoaded', function() {
        const menuLinks = document.querySelectorAll('.menu-link');
  
        menuLinks.forEach(link => {
            link.addEventListener('click', function(event) {
                event.preventDefault(); // Evita la navegación predeterminada
  
                // Obtén el submenú del elemento de menú clicado
                const submenu = this.nextElementSibling;
  
                // Si el submenú existe, alterna su visibilidad
                if (submenu && submenu.classList.contains('submenu')) {
                    const isDisplayed = submenu.style.display === 'block';
  
                    // Oculta todos los submenús
                    document.querySelectorAll('.submenu').forEach(sub => sub.style.display = 'none');
  
                    // Muestra u oculta el submenú clicado
                    submenu.style.display = isDisplayed ? 'none' : 'block';
                }
            });
        });
  
        // Ocultar submenús cuando se hace clic fuera del menú
        window.addEventListener('click', function(event) {
            if (!event.target.closest('.menu-item')) {
                document.querySelectorAll('.submenu').forEach(submenu => submenu.style.display = 'none');
            }
        });
    });