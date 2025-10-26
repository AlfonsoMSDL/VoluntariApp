package com.proyecto.v2.presentation;

import com.proyecto.v2.model.Proyecto;
import com.proyecto.v2.service.ProyectoService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.sql.Date;


@WebServlet("/proyectos")
public class ProyectoController extends HttpServlet {
    private final ProyectoService proyectoService = new ProyectoService();
    Logger log = Logger.getLogger(ProyectoController.class);
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        resp.setContentType("application/json");
        String accion = req.getParameter("action");

        if(accion == null) accion = "default";

        switch (accion){
            case "save":
                guardarProyecto(req,resp);
                break;
            case "update":

                break;
            default:
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
                break;
        }
    }

    private void guardarProyecto(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        String nombre = req.getParameter("nombre");
        String descripcion = req.getParameter("descripcion");
        String ubicacion = req.getParameter("ubicacion");
        String requisitos = req.getParameter("requisitos");
        Date fechaInicio = Date.valueOf(req.getParameter("fechaInicio"));
        Date fechaFin = Date.valueOf(req.getParameter("fechaFin"));
        Integer voluntarios_requeridos = Integer.parseInt(req.getParameter("voluntariosRequeridos"));
        Long idCategoria = Long.parseLong(req.getParameter("idCategoria"));
        Long idOrganizacion = Long.parseLong(req.getParameter("idOrganizacion"));

        Proyecto resultado = proyectoService.save(nombre,descripcion,ubicacion,requisitos,fechaInicio,fechaFin, voluntarios_requeridos,idCategoria,idOrganizacion);
        if(resultado != null){
            resp.getWriter().println("{\"status\": \"success\"}");
        }else{
            resp.getWriter().println("{\"status\": \"error\"}");
        }
    }

    private void actualizarProyecto(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        String nombre = req.getParameter("nombre");
        String descripcion = req.getParameter("descripcion");
        String ubicacion = req.getParameter("ubicacion");
        String requisitos = req.getParameter("requisitos");
        Date fechaInicio = Date.valueOf("fechaInicio");
        Date fechaFin = Date.valueOf("fechaFin");
        Integer voluntariosRequeridos = Integer.parseInt("voluntariosRequeridos");
        Long idCategoria = Long.parseLong(req.getParameter("idCategoria"));
        Long idProyecto = Long.parseLong(req.getParameter("idProyecto"));

        Proyecto actualizado = proyectoService.update(idProyecto,nombre,descripcion,ubicacion,requisitos,fechaInicio,fechaFin,voluntariosRequeridos,idCategoria);

        if(actualizado != null){
            resp.getWriter().println("{\"mensaje\":\"Actualizado correctamente\"}");
        }else {
            resp.getWriter().println("{\"mensaje\":\"Hubo un error actualizando\"}");
        }
    }

}
