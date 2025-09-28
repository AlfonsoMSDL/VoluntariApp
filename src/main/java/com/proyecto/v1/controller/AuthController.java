package com.proyecto.v1.controller;

import com.proyecto.v1.dto.response.GetUsuario;
import com.proyecto.v1.service.AuthService;
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

        GetUsuario usuarioLogin = authService.Login(correo, clave);

        if (usuarioLogin != null) {
            log.info("Usuario logueado: " + usuarioLogin.correo() + " | Rol: " + usuarioLogin.rol());
            HttpSession session = req.getSession();
            session.setAttribute("usuarioLogin", usuarioLogin);

            switch (usuarioLogin.rol()) {
                case "VOLUNTARIO" : req.getRequestDispatcher("pages/inicioVoluntario.jsp").forward(req, resp); break;
                case "ORGANIZACION" :
                    log.info("Entro a la organizacion");
                    req.getRequestDispatcher("pages/inicioOrganizacion.jsp").forward(req, resp); break;

                case "ADMIN": req.getRequestDispatcher("pages/inicioAdmin.jsp").forward(req, resp);break;
                default : {
                    log.warn("Rol desconocido: " + usuarioLogin.rol());
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
