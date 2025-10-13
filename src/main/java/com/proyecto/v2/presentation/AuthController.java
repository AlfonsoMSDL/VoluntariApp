package com.proyecto.v2.presentation;

import com.proyecto.v2.model.Usuario;
import com.proyecto.v2.service.AuthService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.log4j.Logger;

import java.io.IOException;

@WebServlet("/auth")
public class AuthController extends HttpServlet {
    private final AuthService authService = new AuthService();
    private static final Logger log = Logger.getLogger(AuthController.class);


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");

        String correo = req.getParameter("correo");
        String clave = req.getParameter("clave");

        Usuario usuarioLogin = authService.Login(correo, clave);

        if (usuarioLogin != null) {
            log.info("Usuario logueado: " + usuarioLogin.getCorreo() + " | Rol: " + usuarioLogin.getRol());
            HttpSession session = req.getSession();
            session.setAttribute("usuarioLogin", usuarioLogin);

            switch (usuarioLogin.getRol().getNombre()) {
                case "Voluntario" : resp.sendRedirect("pages/inicioVoluntario.jsp");

                break;
                case "Organizacion" :
                    log.info("Entro a la organizacion");
                    resp.sendRedirect("pages/inicioOrganizacion.jsp");

                    break;

                case "Administrador": req.getRequestDispatcher("pages/inicioAdmin.jsp").forward(req, resp);break;
                default : {
                    log.warn("Rol desconocido: " + usuarioLogin.getRol().getNombre());
                    resp.sendError(HttpServletResponse.SC_FORBIDDEN, "Rol no reconocido");
                }
            }
        } else {
            log.warn("Intento de login con credenciales inválidas: " + correo);
            resp.setContentType("application/json");
            resp.getWriter().println("{\"status\": \"not_found\"}");
        }
    }


}
