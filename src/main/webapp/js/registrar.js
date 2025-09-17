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

// Manejo del envío del formulario
const form = document.querySelector('form');
form.addEventListener('submit', function (e) {
    e.preventDefault();

    // Validar que las contraseñas coincidan
    if (clave.value !== confirmarClave.value) {
        alert('Las contraseñas no coinciden');
        return;
    }

    // Aquí se agrega la lógica para enviar los datos
    alert('Registro exitoso!');
});
