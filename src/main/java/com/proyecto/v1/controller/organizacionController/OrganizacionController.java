package com.proyecto.v1.controller.organizacionController;

import com.proyecto.v1.model.Organizacion;
import com.proyecto.v1.service.OrganizacionService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/organizaciones")
public class OrganizacionController extends HttpServlet {
    private final OrganizacionService organizacionService = new OrganizacionService();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        resp.setContentType("application/json");

        String accion = req.getParameter("action");

        if(accion == null) accion = "default";
        switch (accion){
            case "save":
                String nombre = req.getParameter("nombre");
                String nombreUsuario = req.getParameter("nombreUsuario");
                String correo = req.getParameter("correo");
                String clave = req.getParameter("clave");

                Organizacion resultado = organizacionService.save(nombre,nombreUsuario,correo,clave);

                if(resultado != null){
                    resp.getWriter().println(resultado);
                }else{
                    resp.getWriter().println("{\"error\": \"Acción no válida\"}");
                }

                break;
            default:
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
                break;
        }
    }
}
