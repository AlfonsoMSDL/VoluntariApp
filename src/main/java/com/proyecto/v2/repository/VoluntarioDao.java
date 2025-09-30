package com.proyecto.v2.repository;

import com.proyecto.v2.model.Voluntario;
import com.proyecto.v2.model.util.Rol;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class VoluntarioDao {

    private final String INSERT = "INSERT INTO voluntarios (nombre,apellido,nombre_usuario,correo,contrasena,rol) VALUES (?,?,?,?,?,?)";
    private final String SELECT = "SELECT * FROM voluntarios";
    private final String FIND_BY_CORREO = "SELECT * FROM voluntarios WHERE correo = ?";

    public Voluntario save(Voluntario voluntario) {

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
            stmt.setString(6, voluntario.getRol().name());

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
                Rol rol =  Rol.obtenerRol(rs.getString("rol"));

                voluntario = new Voluntario(idVoluntario,nombre,apellido,correo,clave,nombreUsuario,rol);

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

    public Optional<Voluntario> findByCorreo(String correoBuscar){
        Connection conn;
        PreparedStatement stmt;
        Voluntario voluntario = null;

        try{
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(FIND_BY_CORREO);
            stmt.setString(1, correoBuscar);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                Long idVoluntario = rs.getLong("id_voluntario");
                String nombre =  rs.getString("nombre");
                String apellido =  rs.getString("apellido");
                String correo = rs.getString("correo");
                String contrasena =  rs.getString("contrasena");
                String nombreUsuario =  rs.getString("nombre_usuario");

                Rol rol =  Rol.obtenerRol(rs.getString("rol"));

                voluntario = new Voluntario(idVoluntario,nombre,apellido,correo,contrasena,nombreUsuario,rol);

            }

            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);



            return voluntario != null? Optional.of(voluntario):null; //En caso de que no se encuentre, retorna nulo
        }catch(SQLException e){
            throw new RuntimeException(e);
        }

    }
}