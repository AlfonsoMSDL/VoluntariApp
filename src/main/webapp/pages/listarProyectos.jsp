
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.proyecto.v2.model.TipoOrganizacion , com.proyecto.v2.model.Organizacion" %>
<%@ page import="com.proyecto.v2.model.Usuario" %>
<%@ page import="com.proyecto.v2.service.OrganizacionService" %>
<%@ page import="java.util.List" %>
<%@ page import="com.proyecto.v2.service.TipoOrganizacionService" %>
<%@ page import="com.proyecto.v2.service.ProyectoService" %>
<%@ page import="com.proyecto.v2.model.Proyecto" %>
<%
    Usuario usuario = (Usuario) session.getAttribute("usuarioLogin");
    Organizacion organizacion = (new OrganizacionService()).findById(usuario.getId()).get();

    ProyectoService proyectoService = new ProyectoService();
    List<Proyecto> proyectos = proyectoService.findAllProyectosByOrganizacion(organizacion.getId());

%>
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
            <a href="publicarProyecto.jsp" class="btn-crear" >+ Crear Proyecto</a>
        </div>

        <div class="content-box">
            <h2 class="section-title">MIS PROYECTOS</h2>
            <div class="projects-list" id="projectsList">

                <c:forEach var="proyecto" items="<%=proyectos%>" >
                    <div class="project-item">
                        <a href="class=project-link"> ${proyecto.nombre} </a>
                    </div>

                </c:forEach>

            </div>
        </div>
    </div>
</body>
</html>
