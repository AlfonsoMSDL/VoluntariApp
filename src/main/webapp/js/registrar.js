const radios = document.querySelectorAll('input[name="rol"]');
const seccionVoluntario = document.getElementById('seccion-voluntario');
const seccionOrganizacion = document.getElementById('seccion-organizacion');
const camposForm = document.querySelector('.campos-form');
const btnLogin = document.querySelector(".login-btn");

function animarFormulario() {
    camposForm.classList.add('hiddenAnim');
    setTimeout(() => {
        camposForm.classList.remove('hiddenAnim');
    }, 600); // mismo tiempo que el transition en CSS
}

// Cambiar secciones según el rol
radios.forEach(radio => {
    radio.addEventListener('change', async () => {
        animarFormulario();
        await new Promise(resolve => setTimeout(resolve, 500))
        if (radio.value === "voluntario") {
            seccionVoluntario.classList.remove('hidden');
            seccionOrganizacion.classList.add('hidden');
        } else {
            seccionOrganizacion.classList.remove('hidden');
            seccionVoluntario.classList.add('hidden');
        }
    });
});

// Validación de contraseñas
const claveInput = document.getElementById('clave');
const confirmarClaveInput = document.getElementById('confirmar_clave');

function validarClave() {
    if (claveInput.value !== confirmarClaveInput.value) {
        confirmarClaveInput.setCustomValidity('Las contraseñas no coinciden');
    } else {
        confirmarClaveInput.setCustomValidity('');
    }
}

claveInput.addEventListener('change', validarClave);
confirmarClaveInput.addEventListener('keyup', validarClave);

// Evento de registro
btnLogin.addEventListener("click", async (event) => {
    event.preventDefault();

    // Re-validar antes de enviar
    validarClave();


    const rol = document.querySelector('input[name="rol"]:checked').value;

    if (rol === "voluntario") {
        const nombre = document.getElementById("nombreVoluntario").value;
        const apellido = document.getElementById("apellido").value;
        const nombreUsuario = document.getElementById("usuario").value;
        const correo = document.getElementById("email").value;
        const claveValue = claveInput.value;

        const params = new URLSearchParams();
        params.append("action", "save");
        params.append("nombre", nombre);
        params.append("apellido", apellido);
        params.append("nombreUsuario", nombreUsuario);
        params.append("correo", correo);
        params.append("clave", claveValue);

        try {
            const response = await fetch("http://localhost:8181/voluntariApp/voluntarios", {
                method: "POST",
                headers: { "Content-Type": "application/x-www-form-urlencoded" },
                body: params.toString()
            });

            if (!response.ok) throw new Error("Error en la petición: " + response.status);

            const data = await response.text();
            console.log("Respuesta del servidor:", data);
            alert("Voluntario agregado");
        } catch (error) {
            console.error("Hubo un error al guardar el voluntario:", error);
        }

    } else if (rol === "organizacion") {
        const nombre = document.getElementById("nombreOrganizacion").value;
        const nombreUsuario = document.getElementById("usuario").value;
        const correo = document.getElementById("emailOrganizacion").value;
        const claveValue = claveInput.value;

        const params = new URLSearchParams();
        params.append("action", "save");
        params.append("nombreOrganizacion", nombre);
        params.append("nombreUsuario", nombreUsuario);
        params.append("emailOrganizacion", correo);
        params.append("clave", claveValue);

        try {
            const response = await fetch("http://localhost:8181/voluntariApp/organizaciones", {
                method: "POST",
                headers: { "Content-Type": "application/x-www-form-urlencoded" },
                body: params.toString()
            });

            if (!response.ok) throw new Error("Error en la petición: " + response.status);

            const data = await response.text();
            console.log("Respuesta del servidor:", data);
            alert("Organización agregada");
        } catch (error) {
            console.error("Hubo un error al registrar la organización:", error);
        }
    }
});
