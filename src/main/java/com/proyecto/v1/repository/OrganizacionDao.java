package com.proyecto.v1.repository;

import com.proyecto.v1.model.Organizacion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrganizacionDao {
    private final String INSERT = "INSERT INTO organizaciones (nombre, nombre_usuario, correo, contrasena) VALUES(?,?,?,?)";

    public Organizacion saveOrganizacion(Organizacion organizacion){
        Connection conn = null;
        PreparedStatement stmt = null;

        try{
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(INSERT);

            stmt.setString(1,organizacion.getNombre());
            stmt.setString(2,organizacion.getNombreUsuario());
            stmt.setString(3,organizacion.getCorreo());
            stmt.setString(4, organizacion.getClave());
            int registrosAfectados = stmt.executeUpdate();
            return registrosAfectados !=0 ? organizacion:null;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
