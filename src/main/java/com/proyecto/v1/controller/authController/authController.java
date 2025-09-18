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
        Map<String,String> map = new HashMap<>();
        String correo =  req.getParameter("email");
        String clave = req.getParameter("clave");

        List<Voluntario> voluntarios = voluntarioService.findAllVoluntarios();
        List<Organizacion> organizaciones = organizacionService.findAllOrganizaciones();

        Optional<Voluntario> voluntarioOptional = voluntarios.stream().
                filter(voluntario -> voluntario.getCorreo().equalsIgnoreCase(correo))
                .findFirst();

        if(voluntarioOptional.isPresent()){
            Voluntario voluntario = voluntarioOptional.get();
            if(clave.equalsIgnoreCase(voluntario.getClave())){ //Credenciales correctas
                map.put("status","success");
                resp.getWriter().println(map);
            }else{
                map.put("status","error");
                resp.getWriter().println(map);
            }
        }

        Optional<Organizacion> organizacionOptional = organizaciones.stream().
                filter(organizacion -> organizacion.getCorreo().equalsIgnoreCase(correo))
                .findFirst();

        if(organizacionOptional.isPresent()){
            Organizacion organizacion = organizacionOptional.get();
            if(clave.equalsIgnoreCase(organizacion.getClave())){ //Credenciales correctas
                map.put("status","success");
                resp.getWriter().println(map);
            }else{
                map.put("status","error");
                resp.getWriter().println(map);
            }
        }

    }
}
