<%@ page import="com.proyecto.v2.model.Voluntario" %>
<%@ page import="com.proyecto.v2.model.Usuario" %>
<%@ page import="com.proyecto.v2.service.VoluntarioService" %>
<%@ page import="com.proyecto.v2.dto.response.GetUsuario" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    GetUsuario usuario = (GetUsuario) session.getAttribute("usuarioLogin");
    Voluntario voluntario = (new VoluntarioService()).findById(usuario.id()).get();

%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Perfil de Voluntario - VoluntariApp</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/perfilVoluntario.css">
</head>
<body>
<div class="container">
    <div class="header">
        <h1>👤 Mi Perfil</h1>
        <p>Actualiza tu información para encontrar mejores oportunidades de voluntariado</p>
    </div>

    <div class="profile-content">
        <div class="sidebar">
            <div class="profile-avatar">
                👨‍💼
            </div>

            <div class="sidebar-info">
                <h3>💡 Consejo</h3>
                <p>Completa tu perfil con tus habilidades y experiencia para que las organizaciones puedan encontrarte más fácilmente.</p>
            </div>
        </div>

        <div class="form-section">
            <h2 class="section-title">Información Personal</h2>

            <form id="profileForm">
                <input type="hidden" value="<%=voluntario.getId()%>" id="idVoluntario" name="idVoluntario">
                <div class="form-grid">
                    <div class="form-group">
                        <label for="nombre">Nombre *</label>
                        <input type="text" id="nombre" name="nombre" value="<%=voluntario.getNombre()%>" required>
                    </div>

                    <div class="form-group">
                        <label for="apellido">Apellido *</label>
                        <input type="text" id="apellido" name="apellido" value="<%=voluntario.getApellido()%>" required>
                    </div>

                    <div class="form-group">
                        <label for="nombreUsuario">Nombre de Usuario *</label>
                        <input type="text" id="nombreUsuario" name="nombreUsuario" value="<%=voluntario.getNombreUsuario()%>" required>
                    </div>

                    <div class="form-group">
                        <label for="correo">Correo Electrónico *</label>
                        <input type="email" id="correo" name="correo" value="<%=voluntario.getCorreo()%>" required>
                    </div>

                    <div class="form-group">
                        <label for="telefono">Teléfono *</label>
                        <input type="tel" id="telefono" name="telefono" value="<%=voluntario.getTelefono()%>" required>
                    </div>

                    <div class="form-group">
                        <label for="clave">Cambiar Contraseña</label>
                        <input type="password" id="clave" name="clave" placeholder="Dejar en blanco para mantener la actual">
                        <p class="helper-text">Solo completa si deseas cambiar tu contraseña</p>
                    </div>

                    <div class="form-group full-width">
                        <label for="habilidades">Habilidades</label>
                        <textarea id="habilidades" name="habilidades" placeholder="Ejemplo: Comunicación efectiva, trabajo en equipo, liderazgo, informática...">Trabajo en equipo, comunicación efectiva, resolución de problemas</textarea>
                        <p class="helper-text">Separa tus habilidades con comas</p>
                    </div>

                    <div class="form-group full-width">
                        <label for="experiencia">Experiencia Previa</label>
                        <textarea id="experiencia" name="experiencia" placeholder="Describe tu experiencia como voluntario o en actividades comunitarias...">Voluntario en jornadas de limpieza de playas (2023), apoyo en comedores comunitarios (2022-2023)</textarea>
                    </div>

                    <div class="form-group">
                        <label for="disponibilidad">Disponibilidad</label>
                        <select id="disponibilidad" name="disponibilidad">
                            <option value="">Selecciona una opción</option>
                            <option value="FINES_SEMANA" selected>Fines de semana</option>
                            <option value="ENTRE_SEMANA">Entre semana</option>
                            <option value="HORARIO_FLEXIBLE">Horario flexible</option>
                            <option value="TIEMPO_COMPLETO">Tiempo completo</option>
                            <option value="MEDIO_TIEMPO">Medio tiempo</option>
                            <option value="OCASIONAL">Ocasional</option>
                        </select>
                    </div>

                    <div class="form-group full-width">
                        <label for="areas_interes">Áreas de Interés</label>
                        <textarea id="areas_interes" name="areas_interes" placeholder="Ejemplo: Medio Ambiente, Educación, Protección Animal...">Medio Ambiente, Educación, Asistencia Social</textarea>
                        <p class="helper-text">Separa tus áreas de interés con comas</p>
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

<script src="<%=request.getContextPath()%>/js/perfilVoluntario.js"></script>
</body>
</html>