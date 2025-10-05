<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>VoluntariApp - Panel de Control</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/styles.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/inicioOrg.css">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
</head>
<body>
<header class="encabezado">
    <div class="logo-titulo">
        <img src="<%=request.getContextPath()%>/assets/images/Logo.png" alt="Logo VoluntariApp" class="logo-app">
        <h1>VoluntariApp</h1>
    </div>
</header>

<div class="distribucion-panel">
    <!-- Menú lateral -->
    <aside class="menu-lateral">
        <h2 class="titulo-menu">MENÚ</h2>
        <ul class="lista-opciones">
            <li><a href="perfilOrganizacion.jsp"><span class="material-icons">account_circle</span> Perfil</a></li>
            <li><a href="#"><span class="material-icons">work</span> Proyectos</a></li>
            <li><a href="#"><span class="material-icons">notifications</span> Notificaciones</a></li>
            <li><a href="#"><span class="material-icons">assignment</span> Inscripciones</a></li>
            <li><a href="#"><span class="material-icons">report</span> Reportes</a></li>
        </ul>
    </aside>

    <!-- Contenido principal -->
    <main class="contenido-principal">
        <div class="caja-bienvenida">
            <h2>Bienvenid@ ${usuarioLogin.nombreUsuario} 👋</h2>
            <p>Gracias por hacer parte de esta comunidad. Aquí podrás gestionar tus proyectos, revisar notificaciones y explorar nuevas oportunidades.</p>
        </div>

        <div class="caja-contenido">
            <p>Área de contenido principal (gráficos, tarjetas, proyectos recientes, etc.).</p>
        </div>
    </main>
</div>
</body>
</html>