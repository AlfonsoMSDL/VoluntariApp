package com.proyecto.v1.repository;

import com.proyecto.v1.model.Voluntario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VoluntarioDao {

    private final String INSERT = "INSERT INTO voluntarios (nombre,apellido,nombre_usuario,correo,contrasena) VALUES (?,?,?,?,?)";
    private final String SELECT = "SELECT * FROM voluntarios";

    public Voluntario saveVoluntario(Voluntario voluntario) {

        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);

            stmt.setString(1, voluntario.getNombre());
            stmt.setString(2, voluntario.getApellido());
            stmt.setString(3, voluntario.getNombreUsuario());
            stmt.setString(4, voluntario.getCorreo());
            stmt.setString(5, voluntario.getClave());
            int registrosAfectados = stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                voluntario.setId(rs.getLong(1));
            }


            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
            return registrosAfectados != 0 ?voluntario:null;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public List<Voluntario> findAll(){
        Connection conn = null;
        PreparedStatement stmt = null;

        List<Voluntario> voluntarios = new ArrayList<>();
        Voluntario voluntario = null;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SELECT);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                Long idVoluntario = rs.getLong("id_voluntario");
                String nombre =  rs.getString("nombre");
                String apellido =  rs.getString("apellido");
                String correo =  rs.getString("correo");
                String clave =  rs.getString("contrasena");
                String nombreUsuario =  rs.getString("nombre_usuario");

                voluntario = new Voluntario(idVoluntario,nombre,apellido,correo,clave,nombreUsuario);

                voluntarios.add(voluntario);
            }

            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);

            return voluntarios;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}