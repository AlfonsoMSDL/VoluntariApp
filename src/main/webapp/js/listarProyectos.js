const proyectos = [
            { id: 1, nombre: 'Limpieza de Playas - Rodadero', url: 'proyecto-detalle.html?id=1' },
            { id: 2, nombre: 'Reforestación Sierra Nevada', url: 'proyecto-detalle.html?id=2' },
            { id: 3, nombre: 'Apoyo Escolar Zona Rural', url: 'proyecto-detalle.html?id=3' },
            { id: 4, nombre: 'Donación de Alimentos - Magdalena', url: 'proyecto-detalle.html?id=4' },
            { id: 5, nombre: 'Talleres Comunitarios', url: 'proyecto-detalle.html?id=5' },
            { id: 6, nombre: 'Campaña de Salud Preventiva', url: 'proyecto-detalle.html?id=6' },
            { id: 7, nombre: 'Rescate Animal Urbano', url: 'proyecto-detalle.html?id=7' },
            { id: 8, nombre: 'Programa de Lectura Infantil', url: 'proyecto-detalle.html?id=8' }
        ];

        function cargarProyectos() {
            const lista = document.getElementById('projectsList');

            if (proyectos.length === 0) {
                lista.innerHTML = `
                    <div class="empty-state">
                        <div class="empty-state-icon">📋</div>
                        <p>No tienes proyectos aún. ¡Crea tu primer proyecto!</p>
                    </div>
                `;
                return;
            }

            lista.innerHTML = proyectos.map(proyecto => `
                <div class="project-item" onclick="irAProyecto('${proyecto.url}')">
                    <a href="${proyecto.url}" class="project-link" onclick="event.preventDefault()">
                        ${proyecto.nombre}
                    </a>
                </div>
            `).join('');
        }

        function irAProyecto(url) {
            console.log('Navegando a:', url);
            // Aquí iría la navegación real: window.location.href = url;
            alert('Navegando a: ' + url);
        }

        function crearProyecto() {
            console.log('Crear nuevo proyecto');
            alert('Formulario de creación de proyecto');
        }

        cargarProyectos();