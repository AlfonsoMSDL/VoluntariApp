package com.proyecto.v1.controller;

import com.proyecto.v1.dto.response.GetOrganizacion;
import com.proyecto.v1.mapper.JsonMapper;
import com.proyecto.v1.service.OrganizacionService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

import java.io.IOException;


@WebServlet("/organizaciones")
public class OrganizacionController extends HttpServlet {
    private final OrganizacionService organizacionService = new OrganizacionService();
    Logger log = Logger.getLogger(OrganizacionController.class);
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        resp.setContentType("application/json");

        String accion = req.getParameter("action");

        if(accion == null) accion = "default";
        switch (accion){
            case "save":
                String nombre = req.getParameter("nombreOrganizacion");
                String nombreUsuario = req.getParameter("nombreUsuario");
                String correo = req.getParameter("emailOrganizacion");
                String clave = req.getParameter("clave");



                GetOrganizacion resultado = organizacionService.save(nombre,nombreUsuario,correo,clave);

                if(resultado != null){
                    log.info(resultado);
                    log.info("Organizacion guardada correctamente\n");
                    log.info(organizacionService.findAllOrganizaciones().toString());
                    resp.getWriter().println((new JsonMapper<GetOrganizacion>()).toJson(resultado));
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
