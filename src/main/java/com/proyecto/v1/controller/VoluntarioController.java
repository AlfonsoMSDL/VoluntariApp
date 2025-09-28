package com.proyecto.v1.controller;

import com.proyecto.v1.dto.response.GetVoluntario;
import com.proyecto.v1.mapper.JsonMapper;
import com.proyecto.v1.service.VoluntarioService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

import java.io.IOException;

@WebServlet("/voluntarios")
public class VoluntarioController extends HttpServlet {
    private final VoluntarioService voluntarioService = new VoluntarioService();
    Logger log = Logger.getLogger(VoluntarioController.class);
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("application/json");

        String accion = req.getParameter("action");

        if(accion == null) accion = "default";
        switch (accion) {
            case "save":
                String nombre = req.getParameter("nombre");
                String apellido = req.getParameter("apellido");
                String correo = req.getParameter("correo");
                String clave = req.getParameter("clave");
                String nombreUsuario = req.getParameter("nombreUsuario");

                GetVoluntario resultado = voluntarioService.save(nombre, apellido,nombreUsuario, correo, clave);

                if(resultado != null){ // Si se guardo
                    //req.setAttribute("resultado", resultado);
                    log.info(resultado);
                    log.info("Voluntario guardado correctamente\n");
                    log.info(voluntarioService.findAllVoluntarios().toString());

                    resp.getWriter().println((new JsonMapper<GetVoluntario>()).toJson(resultado));
                }else{
                    resp.getWriter().println("{\"error\": \"Acción no válida\"}");
                    //req.setAttribute("resultado", "No guardado");
                }
                //req.getRequestDispatcher("pages/registro.jsp").forward(req,resp);
                break;
            default:
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
                break;

        }


    }

}
