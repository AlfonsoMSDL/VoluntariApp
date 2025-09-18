const radios = document.querySelectorAll('input[name="rol"]');
const seccionVoluntario = document.getElementById('seccion-voluntario');
const seccionOrganizacion = document.getElementById('seccion-organizacion');
const camposForm = document.querySelector('.campos-form');

function animarFormulario() {
  camposForm.classList.add('hiddenAnim');
  setTimeout(() => {
    camposForm.classList.remove('hiddenAnim');
  }, 600); // mismo tiempo que el transition en CSS
}

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

// Validación de confirmación de contraseña
const clave = document.getElementById('clave');
const confirmarClave = document.getElementById('confirmar_clave');


function validarClave() {
    if (clave.value !== confirmarClave.value) {
        confirmarClave.setCustomValidity('Las contraseñas no coinciden');
    } else {
        confirmarClave.setCustomValidity('');
    }
}

clave.addEventListener('change', validarClave);
confirmarClave.addEventListener('keyup', validarClave);



const btnLogin = document.querySelector(".login-btn");

btnLogin.addEventListener("click", async function () {
    // obtener el rol seleccionado
    const rol = document.querySelector('input[name="rol"]:checked').value;

    if (rol === "voluntario") {

        // Capturar valores del formulario
        const nombre = document.getElementById("nombre").value;
        const apellido = document.getElementById("apellido").value;
        const nombreUsuario = document.getElementById("usuario").value;
        const correo = document.getElementById("email").value;
        const clave = document.getElementById("clave").value;

        // Crear objeto URLSearchParams para enviar como formulario
        const params = new URLSearchParams();
        params.append("action", "save");  // Muy importante para tu switch en el servlet
        params.append("nombre", nombre);
        params.append("apellido", apellido);
        params.append("nombreUsuario", nombreUsuario);
        params.append("correo", correo);
        params.append("clave", clave);

        try {
            const response = await fetch("http://localhost:8181/voluntariApp/voluntarios", {
                method: "POST",
                headers: {
                    "Content-Type": "application/x-www-form-urlencoded"
                },
                body: params.toString()
            });

            if (!response.ok) {
                throw new Error("Error en la petición: " + response.status);
            }

            // Tu servlet responde texto (o JSON si quieres)
            const data = await response.text();
            console.log("Respuesta del servidor:", data);
            alert("Voluntario agregado");

        } catch (error) {
            console.error("Hubo un error al guardar el voluntario:", error);
        }

    } else if (rol === "organizacion") {
        console.log("Se registrará una ORGANIZACIÓN");

        const nombre = document.getElementById("nombreOrganizacion").value;
        const nombreUsuario = document.getElementById("usuario").value;
        const correo = document.getElementById("emailOrganizacion").value;
        const clave = document.getElementById("clave").value;

        // Creamos el formulario en formato application/x-www-form-urlencoded
        const params = new URLSearchParams();
        params.append("action", "save");  // Si tu servlet de organización también espera "save"
        params.append("nombre", nombre);
        params.append("nombreUsuario", nombreUsuario);
        params.append("correo", correo);
        params.append("clave", clave);

        try {
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
            console.log("Respuesta del servidor:", data);
            alert("Organizacion agregada");



        } catch (error) {
            console.error("Hubo un error al registrar la organización:", error);
        }
    }

});

