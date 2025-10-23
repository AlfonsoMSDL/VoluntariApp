
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestión de Proyectos - VoluntariApp</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/listarProyectos.css">
</head>
<body>
    <div class="container">
        <div class="header">
            <h1>Gestión de Proyectos</h1>
            <button class="btn-crear" onclick="crearProyecto()">+ Crear Proyecto</button>
        </div>

        <div class="content-box">
            <h2 class="section-title">MIS PROYECTOS</h2>
            <div class="projects-list" id="projectsList">
                <!-- Los proyectos se cargarán aquí -->
            </div>
        </div>
    </div>
    <script src="../js/listarProyectos.js"></script>
</body>
</html>
