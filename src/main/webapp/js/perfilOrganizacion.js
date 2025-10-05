document.getElementById('profileForm').addEventListener('submit', async function (e) {
    e.preventDefault();

    // Obtener los valores del formulario
    const idOrganizacion = document.getElementById('idOrganizacion').value;
    const nombreOrganizacion = document.getElementById('nombreOrganizacion').value;
    const nombreUsuario = document.getElementById('nombreUsuario').value;
    const correo = document.getElementById('correo').value;
    const telefono = document.getElementById('telefono').value;
    const tipo = document.getElementById('tipo').value;
    const descripcion = document.getElementById('descripcion').value;
    const clave = document.getElementById('clave').value;

    // Validaciones básicas
    if (!nombreOrganizacion || !nombreUsuario || !correo || !telefono || !tipo) {
        alert("Por favor completa todos los campos obligatorios.");
        return;
    }

    // Crear los parámetros del formulario
    const params = new URLSearchParams();
    params.append("idOrganizacion",idOrganizacion);
    params.append("action", "update");
    params.append("nombreOrganizacion", nombreOrganizacion);
    params.append("nombreUsuario", nombreUsuario);
    params.append("correo", correo);
    params.append("telefono", telefono);
    params.append("tipo", tipo);
    params.append("descripcion", descripcion);

    if (clave) {
        params.append("clave", clave);
    }

    try {
        // Enviar los datos al backend
        const response = await fetch("http://localhost:8181/voluntariApp/organizaciones", {
            method: "POST",
            headers: {
                "Content-Type": "application/x-www-form-urlencoded"
            },
            body: params.toString()
        });

        if (!response.ok) {
            throw new Error("Error en la petición: " + response.status);
        }

        const data = await response.text();
        console.log("✅ Respuesta del servidor:", data);
        alert("Perfil actualizado exitosamente.");

        // Puedes redirigir o recargar
        // window.location.href = "dashboardOrganizacion.jsp";
    } catch (error) {
        console.error("❌ Error al actualizar la organización:", error);
        alert("Error al actualizar la organización. Por favor intenta nuevamente.");
    }
});

function cancelar() {
    if (confirm('¿Estás seguro de que deseas cancelar los cambios?')) {
        window.history.back();
    }
}
