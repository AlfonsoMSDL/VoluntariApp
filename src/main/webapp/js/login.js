// Lógica para procesar las credenciales del usuario
const loginBtn = document.querySelector('.login-btn');

loginBtn.addEventListener('click', async function (e) {
    e.preventDefault(); // Evita que el formulario recargue la página

    const correo = document.getElementById('email').value;   // <- CORREGIDO
    const clave = document.getElementById('contrasena').value; // <- CORREGIDO

    // Creamos el formulario en formato application/x-www-form-urlencoded
    const params = new URLSearchParams();
    params.append("correo", correo);
    params.append("clave", clave);

    try {
        const response = await fetch("http://localhost:8181/voluntariApp/auth", {
            method: "POST",
            headers: {
                "Content-Type": "application/x-www-form-urlencoded"
            },
            body: params.toString()
        });

        if (!response.ok) {
            throw new Error("Error en la petición: " + response.status);
        }

        const data = await response.json(); // <-- Ahora podemos leer JSON válido

        console.log("Respuesta del servidor:", data);

        if (data.status === "success") {
            alert("Bienvenido ✅");
            window.location.href = "/voluntariApp/pages/inicioVoluntario.jsp";
        } else if (data.status === "error") {
            alert("⚠️ Contraseña incorrecta");
        } else if (data.status === "not_found") {
            alert("❌ Usuario no encontrado");
        }

    } catch (error) {
        console.error("Hubo un error en el login:", error);
        alert("Error en el servidor. Intenta más tarde.");
    }
});
