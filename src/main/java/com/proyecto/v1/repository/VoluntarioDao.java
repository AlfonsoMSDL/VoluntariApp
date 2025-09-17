package com.proyecto.v1.repository;

import com.proyecto.v1.model.Voluntario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class VoluntarioDao {

    private final String INSERT = "INSERT INTO voluntarios (nombre,apellido,nombre_usuario,correo,contrasena) VALUES (?,?,?,?,?)";

    public Voluntario saveVoluntario(Voluntario voluntario) {

        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(INSERT);

            stmt.setString(1, voluntario.getNombre());
            stmt.setString(2, voluntario.getApellido());
            stmt.setString(3, voluntario.getNombreUsuario());
            stmt.setString(4, voluntario.getCorreo());
            stmt.setString(5, voluntario.getClave());
            int registrosAfectados = stmt.executeUpdate();
            return registrosAfectados != 0 ?voluntario:null;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}