package com.proyecto.v2.presentation;

import com.proyecto.v2.model.Organizacion;
import com.proyecto.v2.service.OrganizacionService;
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
    /*
    Logger log = Logger.getLogger(OrganizacionController.class);
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        resp.setContentType("application/json");

        String accion = req.getParameter("action");

        if(accion == null) accion = "default";
        switch (accion){
            case "save":
                guardarOrganizacion(req,resp);
                break;

            case "update":
                actualizarOrganizacion(req,resp);
                break;

            default:
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
                break;
        }
    }


    private void guardarOrganizacion(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String nombre = req.getParameter("nombreOrganizacion");
        String nombreUsuario = req.getParameter("nombreUsuario");
        String correo = req.getParameter("emailOrganizacion");
        String clave = req.getParameter("clave");
        String telefono = req.getParameter("telefono");
        String tipo =  req.getParameter("tipo");



        Organizacion resultado = organizacionService.save(nombre,nombreUsuario,correo,clave,telefono,tipo);

        if(resultado != null){
            log.info(resultado);
            log.info("Organizacion guardada correctamente\n");
            log.info(organizacionService.findAllOrganizaciones().toString());
            resp.getWriter().println(resultado);
        }else{
            resp.getWriter().println("{\"error\": \"Acción no válida\"}");
        }
    }

    private void actualizarOrganizacion(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String nombre = req.getParameter("nombreOrganizacion");
        String nombreUsuario = req.getParameter("nombreUsuario");
        String correo = req.getParameter("correo");
        String clave = req.getParameter("clave");
        String telefono = req.getParameter("telefono");
        String tipo =  req.getParameter("tipo");
        Long idOrganizacion = Long.parseLong(req.getParameter("idOrganizacion"));
        String descripcion = req.getParameter("descripcion");

        Organizacion actualizado = organizacionService.update(idOrganizacion,nombre,correo,telefono,clave, tipo,descripcion,nombreUsuario);

        if(actualizado != null){
            resp.getWriter().println("{\"mensaje\":\"Actualizado correctamente\"}");
            log.info("Actualizado correctamente");
            req.getSession().setAttribute("usuarioLogin",actualizado);
        }else{
            resp.getWriter().println("{\"mensaje\":\"Hubo un error actualizando\"}");
            log.info("Actualizado correctamente");
        }
    }

     */


}
