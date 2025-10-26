<%@ page import="com.proyecto.v2.service.CategoriaService" %>
<%@ page import="com.proyecto.v2.model.Categoria" %>
<%@ page import="java.util.List" %>
<%@ page import="com.proyecto.v2.model.Organizacion" %>
<%@ page import="com.proyecto.v2.model.Usuario" %>
<%@ page import="com.proyecto.v2.service.OrganizacionService" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
    Usuario usuario = (Usuario) session.getAttribute("usuarioLogin");
    Organizacion organizacion = (new OrganizacionService()).findById(usuario.getId()).get();

    CategoriaService categoriaService = new CategoriaService();
    List<Categoria> categorias = categoriaService.findAll();


%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Publicar Proyecto - VoluntariApp</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/publicarProyecto.css">
</head>
<body>
<div class="container">
    <div class="header">
        <button class="btn-back" onclick="volver()">←</button>
        <h1 class="page-title">Publicar Proyecto</h1>
    </div>

    <div class="form-container">
        <div class="success-message" id="successMessage">
            ✓ Proyecto publicado exitosamente
        </div>

        <form id="proyectoForm" onsubmit="publicarProyecto(event)">

            <input type="hidden" id="idOrganizacion" value="<%=organizacion.getId()%>">
            <div class="form-row">
                <div class="form-group">
                    <label for="nombre" class="required">Nombre del Proyecto</label>
                    <input
                            type="text"
                            id="nombre"
                            name="nombre"
                            placeholder="Ej: Reforestación comunitaria"
                            required
                    >
                </div>

                <div class="form-group">
                    <label for="ubicacion" class="required">Ubicación</label>
                    <input
                            type="text"
                            id="ubicacion"
                            name="ubicacion"
                            placeholder="Ciudad, país"
                            required
                    >
                </div>

                <div class="form-group">
                    <label for="voluntarios" class="required">N° Voluntarios</label>
                    <input
                            type="number"
                            id="voluntarios"
                            name="voluntarios"
                            placeholder="Cantidad requerida"
                            min="1"
                            required
                    >
                </div>

                <div class="form-group">
                    <label for="categoria" class="required">Categoría</label>


                    <select id="categoria" name="categoria" required>
                        <option value="">Seleccionar categoría</option>
                        <c:forEach var="categoria" items="<%=categorias%>">
                            <option value="${categoria.id}">${categoria.nombre}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>

            <div class="form-group form-group-full">
                <label for="descripcion" class="required">Descripción del Proyecto</label>
                <textarea
                        id="descripcion"
                        name="descripcion"
                        placeholder="Describe el proyecto, sus objetivos y el impacto que generará..."
                        required
                ></textarea>
                <span class="helper-text">Mínimo 50 caracteres</span>
            </div>

            <div class="form-group form-group-full">
                <label for="requisitos" class="required">Requisitos para Voluntarios</label>
                <textarea
                        id="requisitos"
                        name="requisitos"
                        placeholder="Especifica las habilidades, experiencia o requisitos necesarios..."
                        required
                ></textarea>
                <span class="helper-text">Describe qué se espera de los voluntarios</span>
            </div>

            <div class="date-row">
                <div class="form-group">
                    <label for="fechaInicio" class="required">Fecha de Inicio</label>
                    <input
                            type="date"
                            id="fechaInicio"
                            name="fechaInicio"
                            required
                    >
                </div>

                <div class="form-group">
                    <label for="fechaFin" class="required">Fecha de Fin</label>
                    <input
                            type="date"
                            id="fechaFin"
                            name="fechaFin"
                            required
                    >
                </div>
            </div>

            <div class="form-actions">
                <button type="submit" class="btn-submit">Publicar Proyecto</button>
            </div>
        </form>
    </div>
</div>

<script src="<%=request.getContextPath()%>/js/publicarProyecto.js"></script>
</body>
</html>
