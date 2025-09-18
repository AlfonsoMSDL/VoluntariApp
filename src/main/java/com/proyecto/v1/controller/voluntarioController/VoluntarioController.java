package com.proyecto.v1.controller.voluntarioController;

import com.proyecto.v1.model.Voluntario;
import com.proyecto.v1.service.VoluntarioService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/voluntarios")
public class VoluntarioController extends HttpServlet {
    private final VoluntarioService voluntarioService = new VoluntarioService();
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

                Voluntario resultado = voluntarioService.save(nombre, apellido,nombreUsuario, correo, clave);

                if(resultado != null){ // Si se guardo
                    //req.setAttribute("resultado", resultado);
                    resp.getWriter().println(resultado);
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
