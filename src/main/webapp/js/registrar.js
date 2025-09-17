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
