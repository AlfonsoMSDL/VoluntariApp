package com.proyecto.v1.controller.authController;

import com.proyecto.v1.model.Organizacion;
import com.proyecto.v1.model.Voluntario;
import com.proyecto.v1.service.OrganizacionService;
import com.proyecto.v1.service.VoluntarioService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@WebServlet("/auth")
public class authController extends HttpServlet {
    private final VoluntarioService voluntarioService = new VoluntarioService();
    private final OrganizacionService organizacionService = new OrganizacionService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");

        String correo = req.getParameter("correo"); // <-- Ahora coincide con el fetch
        String clave = req.getParameter("clave");

        Map<String, String> map = new HashMap<>();

        List<Voluntario> voluntarios = voluntarioService.findAllVoluntarios();
        List<Organizacion> organizaciones = organizacionService.findAllOrganizaciones();

        // --- Buscar voluntario ---
        Optional<Voluntario> voluntarioOptional = voluntarios.stream()
                .filter(voluntario -> voluntario.getCorreo().equalsIgnoreCase(correo))
                .findFirst();

        if (voluntarioOptional.isPresent()) {
            Voluntario voluntario = voluntarioOptional.get();
            map.put("status", clave.equalsIgnoreCase(voluntario.getClave()) ? "success" : "error");
            resp.getWriter().println("{\"status\": \"" + map.get("status") + "\"}");
            return; // <-- Evita que siga ejecutando el resto del método
        }

        // --- Buscar organización ---
        Optional<Organizacion> organizacionOptional = organizaciones.stream()
                .filter(organizacion -> organizacion.getCorreo().equalsIgnoreCase(correo))
                .findFirst();

        if (organizacionOptional.isPresent()) {
            Organizacion organizacion = organizacionOptional.get();
            map.put("status", clave.equalsIgnoreCase(organizacion.getClave()) ? "success" : "error");
            resp.getWriter().println("{\"status\": \"" + map.get("status") + "\"}");
            return;
        }

        // --- Si no se encontró ningún usuario ---
        resp.getWriter().println("{\"status\": \"not_found\"}");
    }
}
