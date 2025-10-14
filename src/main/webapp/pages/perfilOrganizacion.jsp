<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.proyecto.v2.model.TipoOrganizacion , com.proyecto.v2.model.Organizacion" %>
<%@ page import="com.proyecto.v2.model.Usuario" %>
<%@ page import="com.proyecto.v2.service.OrganizacionService" %>
<%@ page import="java.util.List" %>
<%@ page import="com.proyecto.v2.service.TipoOrganizacionService" %>

<%
    Usuario usuario = (Usuario) session.getAttribute("usuarioLogin");
    Organizacion organizacion = (new OrganizacionService()).findById(usuario.getId()).get();


    List<TipoOrganizacion> tipos = (new TipoOrganizacionService()).findAll();

%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Perfil de Organización - VoluntariApp</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/perfilOrganizacion.css">

</head>
<body>
<div class="container">
    <div class="header">
        <h1>🏢 Perfil de Organización</h1>
        <p>Gestiona la información de tu organización</p>
    </div>

    <div class="profile-content">
        <div class="sidebar">
            <div class="profile-avatar">
                🏛️
            </div>

            <div class="sidebar-info">
                <h3>💡 Consejo</h3>
                <p>Mantén actualizada la información de tu organización para que los voluntarios puedan conocerte mejor.</p>
                </br>

            </div>
        </div>

        <div class="form-section">
            <h2 class="section-title">Información General</h2>

            <form id="profileForm">
                <div class="form-grid">

                    <!-- Id para poder saber cual organizacion se va a actualizar-->
                    <input type="hidden" value="<%=organizacion.getId()%>" id="idOrganizacion" name="idOrganizacion">

                    <div class="form-group">
                        <label for="nombreOrganizacion">Nombre de la Organización *</label>
                        <input type="text" id="nombreOrganizacion" name="nombreOrganizacion" value="<%=organizacion.getNombre()%>" required>
                    </div>

                    <div class="form-group">
                        <label for="nombreUsuario">Nombre de Usuario *</label>
                        <input type="text" id="nombreUsuario" name="nombreUsuario" value="<%=organizacion.getNombreUsuario()%>" required>
                    </div>

                    <div class="form-group">
                        <label for="correo">Correo Electrónico *</label>
                        <input type="email" id="correo" name="correo" value="<%=organizacion.getCorreo()%>" required>
                    </div>

                    <div class="form-group">
                        <label for="telefono">Teléfono de Contacto *</label>
                        <input type="tel" id="telefono" name="telefono" value="<%=organizacion.getTelefono()%>" required>
                    </div>

                    <div class="form-group">
                        <label for="tipoOrganizacion">Tipo de Organización *</label>
                        <select id="tipoOrganizacion" name="tipoOrganizacion" required>
                            <option value="">Selecciona un tipoOrganizacion</option>
                            <c:forEach var="tipoOrganizacion" items="<%=tipos%>" >
                                <option value="${tipoOrganizacion.id}">${tipoOrganizacion.nombre}</option>
                            </c:forEach>

                        </select>
                    </div>

                    <div class="form-group">
                        <label for="clave">Cambiar Contraseña</label>
                        <input type="password" id="clave" name="clave" placeholder="Dejar en blanco para mantener la actual">
                    </div>

                    <div class="form-group full-width">
                        <label for="descripcion">Descripción de la Organización</label>
                        <textarea id="descripcion" name="descripcion" required><%=organizacion.getDescripcion()%></textarea>
                    </div>
                </div>

                <div class="form-actions">
                    <button type="button" class="btn btn-secondary" onclick="cancelar()">Cancelar</button>
                    <button type="submit" class="btn btn-primary">💾 Guardar Cambios</button>
                </div>
            </form>
        </div>
    </div>
</div>

<script src="<%=request.getContextPath()%>/js/perfilOrganizacion.js"></script>
</body>
</html>