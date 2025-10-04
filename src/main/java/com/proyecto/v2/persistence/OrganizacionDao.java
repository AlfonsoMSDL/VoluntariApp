package com.proyecto.v2.persistence;

import com.proyecto.v2.model.Organizacion;
import com.proyecto.v2.model.util.Rol;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrganizacionDao {
    private static final String INSERT = "INSERT INTO organizaciones (nombre, nombre_usuario, correo, contrasena, rol) VALUES(?,?,?,?,?)";
    private static final String SELECT = "SELECT * FROM organizaciones";
    private static final String FIND_BY_CORREO = "SELECT * FROM organizaciones WHERE correo = ?";
    private static final String FIND_BY_ID = "SELECT * FROM organizaciones WHERE id_organizacion = ?";
    private static final String UPDATE = "UPDATE organizaciones SET nombre = ?, nombre_usuario = ?, correo = ?, contrasena = ?, rol = ? WHERE id_organizacion = ?";
    private static final String DELETE = "DELETE FROM organizaciones WHERE id_organizacion = ?";

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
                Long idOrganizacion = rs.getLong("id_organizacion");
                String nombre = rs.getString("nombre");
                String nombreUsuario = rs.getString("nombre_usuario");
                String correo = rs.getString("correo");
                String clave = rs.getString("contrasena");
                Rol rol = Rol.obtenerRol(rs.getString("rol"));

                organizacion = new Organizacion(idOrganizacion,nombre,correo,clave,nombreUsuario,rol);
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

            if(rs.next()){
                Long idOrganizacion = rs.getLong("id_organizacion");
                String nombre = rs.getString("nombre");
                String nombreUsuario = rs.getString("nombre_usuario");
                String correo = rs.getString("correo");
                String clave = rs.getString("contrasena");
                Rol rol = Rol.obtenerRol(rs.getString("rol"));

                organizacion = new Organizacion(idOrganizacion,nombre,correo,clave,nombreUsuario,rol);
            }

            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);

            return Optional.ofNullable(organizacion);
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    // 🔹 Buscar por ID
    public Optional<Organizacion> findById(Long idBuscar){
        Connection conn;
        PreparedStatement stmt;
        Organizacion organizacion = null;

        try{
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(FIND_BY_ID);
            stmt.setLong(1, idBuscar);
            ResultSet rs = stmt.executeQuery();

            if(rs.next()){
                Long idOrganizacion = rs.getLong("id_organizacion");
                String nombre = rs.getString("nombre");
                String nombreUsuario = rs.getString("nombre_usuario");
                String correo = rs.getString("correo");
                String clave = rs.getString("contrasena");
                Rol rol = Rol.obtenerRol(rs.getString("rol"));

                organizacion = new Organizacion(idOrganizacion,nombre,correo,clave,nombreUsuario,rol);
            }

            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);

            return Optional.ofNullable(organizacion);
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    // 🔹 Modificar
    public boolean update(Organizacion organizacion){
        Connection conn;
        PreparedStatement stmt;

        try{
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(UPDATE);

            stmt.setString(1, organizacion.getNombre());
            stmt.setString(2, organizacion.getNombreUsuario());
            stmt.setString(3, organizacion.getCorreo());
            stmt.setString(4, organizacion.getClave());
            stmt.setString(5, organizacion.getRol().name());
            stmt.setLong(6, organizacion.getId());

            int registrosAfectados = stmt.executeUpdate();

            Conexion.close(stmt);
            Conexion.close(conn);

            return registrosAfectados > 0;
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    // 🔹 Eliminar por ID
    public boolean deleteById(Long id){
        Connection conn;
        PreparedStatement stmt;

        try{
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(DELETE);
            stmt.setLong(1, id);

            int registrosAfectados = stmt.executeUpdate();

            Conexion.close(stmt);
            Conexion.close(conn);

            return registrosAfectados > 0;
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

}
