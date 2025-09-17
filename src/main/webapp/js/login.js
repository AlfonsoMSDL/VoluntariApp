//Logica para procesar las credenciales del usuario

const loginBtn = document.querySelector('.login-btn');

loginBtn.addEventListener('click', function (e) {
    e.preventDefault();
    const usuario = document.querySelector('#usuario');
    const clave = document.querySelector('#clave');

    //En esta parte vamos a simular que haremos una consulta a la base de datos
    // para comprobar las credenciales del usuario

    if (usuario.value === 'admin' && clave.value === '1234') {
        //Si las credenciales son correctas, redirigimos al usuario a la página principal
        window.location.href = '../index.html';
    } else {
        //Si las credenciales son incorrectas, mostramos un mensaje de error
        alert('Usuario o contraseña incorrectos');
    }
});