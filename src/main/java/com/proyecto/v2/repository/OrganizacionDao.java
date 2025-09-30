package com.proyecto.v2.repository;

import com.proyecto.v2.model.Organizacion;
import com.proyecto.v2.model.util.Rol;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrganizacionDao {
    private static final String INSERT = "INSERT INTO organizaciones (nombre, nombre_usuario, correo, contrasena,rol) VALUES(?,?,?,?,?)";
    private static final String SELECT = "SELECT * FROM organizaciones";
    private static final String FIND_BY_CORREO = "SELECT * FROM organizaciones WHERE correo = ?";

    public Organizacion save(Organizacion organizacion){
        Connection conn = null;
        PreparedStatement stmt = null;

        try{
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);

            stmt.setString(1,organizacion.getNombre());
            stmt.setString(2,organizacion.getNombreUsuario());
            stmt.setString(3,organizacion.getCorreo());
            stmt.setString(4, organizacion.getClave());
            stmt.setString(5,organizacion.getRol().name());
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
                Rol rol = Rol.obtenerRol(rs.getString("rol"));

                organizacion = new Organizacion(idORganizacion,nombre,correo,clave,nombreUsuario,rol);

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

    public Optional<Organizacion> findByCorreo(String correoBuscar){
        Connection conn;
        PreparedStatement stmt;
        Organizacion organizacion = null;

        try{
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(FIND_BY_CORREO);
            stmt.setString(1, correoBuscar);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                Long idORganizacion = rs.getLong("id_organizacion");
                String nombre = rs.getString("nombre");
                String nombreUsuario = rs.getString("nombre_usuario");
                String correo = rs.getString("correo");
                String clave = rs.getString("contrasena");
                Rol rol = Rol.obtenerRol(rs.getString("rol"));

                organizacion = new Organizacion(idORganizacion,nombre,correo,clave,nombreUsuario,rol);

            }

            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);


            return organizacion != null? Optional.of(organizacion): null; //En caso de que no se encuentre, retorna nulo
        }catch(SQLException e){
            throw new RuntimeException(e);
        }

    }


}
