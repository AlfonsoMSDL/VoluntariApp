package com.proyecto.v1.repository;

import com.proyecto.v1.model.Organizacion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrganizacionDao {
    private final String INSERT = "INSERT INTO organizaciones (nombre, nombre_usuario, correo, contrasena) VALUES(?,?,?,?)";
    private final String SELECT = "SELECT * FROM organizaciones";

    public Organizacion saveOrganizacion(Organizacion organizacion){
        Connection conn = null;
        PreparedStatement stmt = null;

        try{
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);

            stmt.setString(1,organizacion.getNombre());
            stmt.setString(2,organizacion.getNombreUsuario());
            stmt.setString(3,organizacion.getCorreo());
            stmt.setString(4, organizacion.getClave());
            int registrosAfectados = stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if(rs.next()){
                organizacion.setId(rs.getLong(1));
            }

            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);

            return registrosAfectados !=0 ? organizacion:null;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public List<Organizacion> findAll(){
        Connection conn = null;
        PreparedStatement stmt = null;

        List<Organizacion> organizaciones =  new ArrayList<>();
        Organizacion organizacion = null;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SELECT);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                Long idORganizacion = rs.getLong("id_organizacion");
                String nombre = rs.getString("nombre");
                String nombreUsuario = rs.getString("nombre_usuario");
                String correo = rs.getString("correo");
                String clave = rs.getString("contrasena");

                organizacion = new Organizacion(idORganizacion,nombre,nombreUsuario,correo,clave);

                organizaciones.add(organizacion);
            }

            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
            return organizaciones;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
