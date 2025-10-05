package com.proyecto.v2.presentation;

import com.proyecto.v2.model.Voluntario;
import com.proyecto.v2.service.VoluntarioService;
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

                guardarVoluntario(req,resp);

                break;

            case "update":
                actualizarVoluntario(req,resp);
                break;
            default:
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
                break;

        }


    }



    private void guardarVoluntario(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String nombre = req.getParameter("nombre");
        String apellido = req.getParameter("apellido");
        String correo = req.getParameter("correo");
        String clave = req.getParameter("clave");
        String telefono = req.getParameter("telefono");
        String nombreUsuario = req.getParameter("nombreUsuario");

        Voluntario resultado = voluntarioService.save(nombre, apellido,nombreUsuario, correo, clave,telefono);

        if(resultado != null){ // Si se guardo
            //req.setAttribute("resultado", resultado);
            log.info(resultado);
            log.info("Voluntario guardado correctamente\n");
            log.info(voluntarioService.findAllVoluntarios().toString());

            resp.getWriter().println(resultado);
        }else{
            resp.getWriter().println("{\"error\": \"Acción no válida\"}");
            //req.setAttribute("resultado", "No guardado");
        }
        //req.getRequestDispatcher("pages/registro.jsp").forward(req,resp);
    }

    private void actualizarVoluntario(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Long id = Long.parseLong( req.getParameter("idVoluntario"));
        String nombre = req.getParameter("nombre");
        String apellido = req.getParameter("apellido");
        String correo = req.getParameter("correo");
        String clave = req.getParameter("clave");
        String telefono = req.getParameter("telefono");
        String nombreUsuario = req.getParameter("nombreUsuario");
        String hablidades = req.getParameter("habilidades");
        String experiencia = req.getParameter("experiencia");
        String disponibilidad = req.getParameter("disponibilidad");
        String areas_interes = req.getParameter("areas_interes");

        Voluntario actualizado = voluntarioService.update(
                id,
                nombre,
                apellido,
                correo,
                telefono,
                clave,
                hablidades,
                experiencia,
                disponibilidad,
                areas_interes,
                nombreUsuario);

        if(actualizado != null){
            //Si se actualizo
            resp.getWriter().println("{\"mensaje\":\"Actualizado correctamente\"}");
            log.info("Actualizado correctamente");
            req.getSession().setAttribute("usuarioLogin",actualizado);
        }else{
            resp.getWriter().println("{\"mensaje\":\"No se pudo actualizar\"}");
            log.info("No se pudo actualizar");
        }


    }

}
