package com.proyecto.v1.controller.authController;

import com.proyecto.v1.model.Voluntario;
import com.proyecto.v1.service.VoluntarioService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/auth")
public class authController extends HttpServlet {
    private final VoluntarioService voluntarioService = new VoluntarioService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String correo =  req.getParameter("email");
        String clave = req.getParameter("clave");

        List<Voluntario> voluntarios = voluntarioService.findAllVoluntarios();


    }
}
