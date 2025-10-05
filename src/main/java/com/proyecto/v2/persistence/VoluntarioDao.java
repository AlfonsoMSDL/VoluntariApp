package com.proyecto.v2.persistence;

import com.proyecto.v2.model.Voluntario;
import com.proyecto.v2.model.util.Rol;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class VoluntarioDao {

    private final String INSERT = "INSERT INTO voluntarios (nombre, apellido, nombre_usuario, correo, contrasena, rol, telefono) VALUES (?,?,?,?,?,?,?)";
    private final String SELECT_ALL = "SELECT * FROM voluntarios";
    private final String SELECT_BY_ID = "SELECT * FROM voluntarios WHERE id_voluntario = ?";
    private final String FIND_BY_CORREO = "SELECT * FROM voluntarios WHERE correo = ?";
    private final String UPDATE = "UPDATE voluntarios SET nombre=?, apellido=?, nombre_usuario=?, correo=?, contrasena=?, telefono = ?, habilidades = ?, experiencia = ?, areas_interes = ?, disponibilidad = ? WHERE id_voluntario=?";

    private final String DELETE = "DELETE FROM voluntarios WHERE id_voluntario=?";

    // CREATE
    public Voluntario save(Voluntario voluntario) {
        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, voluntario.getNombre());
            stmt.setString(2, voluntario.getApellido());
            stmt.setString(3, voluntario.getNombreUsuario());
            stmt.setString(4, voluntario.getCorreo());
            stmt.setString(5, voluntario.getClave());
            stmt.setString(6, voluntario.getRol().name());
            stmt.setString(7, voluntario.getTelefono());

            int registrosAfectados = stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                voluntario.setId(rs.getLong(1));
            }

            Conexion.close(rs);
            return registrosAfectados != 0 ? voluntario : null;

        } catch (SQLException e) {
            throw new RuntimeException("Error al guardar el voluntario", e);
        }
    }

    // READ ALL
    public List<Voluntario> findAll() {
        List<Voluntario> voluntarios = new ArrayList<>();

        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SELECT_ALL);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Long idVoluntario = rs.getLong("id_voluntario");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                String correo = rs.getString("correo");
                String clave = rs.getString("contrasena");
                String nombreUsuario = rs.getString("nombre_usuario");
                Rol rol = Rol.obtenerRol(rs.getString("rol"));
                String telefono = rs.getString("telefono");

                Voluntario voluntario = new Voluntario(idVoluntario, nombre, apellido, correo, clave, nombreUsuario, rol);
                voluntario.setTelefono(telefono);
                voluntarios.add(voluntario);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al listar los voluntarios", e);
        }

        return voluntarios;
    }

    // READ BY ID
    public Optional<Voluntario> findById(Long idVoluntario) {
        Voluntario voluntario = null;

        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SELECT_BY_ID)) {

            stmt.setLong(1, idVoluntario);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                voluntario = new Voluntario(
                        rs.getLong("id_voluntario"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("correo"),
                        rs.getString("contrasena"),
                        rs.getString("nombre_usuario"),
                        Rol.obtenerRol(rs.getString("rol"))
                );
                voluntario.setTelefono(rs.getString("telefono"));
            }

            Conexion.close(rs);

        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar voluntario por ID", e);
        }

        return Optional.ofNullable(voluntario);
    }

    // READ BY EMAIL
    public Optional<Voluntario> findByCorreo(String correoBuscar) {
        Voluntario voluntario = null;

        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(FIND_BY_CORREO)) {

            stmt.setString(1, correoBuscar);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                voluntario = new Voluntario(
                        rs.getLong("id_voluntario"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("correo"),
                        rs.getString("contrasena"),
                        rs.getString("nombre_usuario"),
                        Rol.obtenerRol(rs.getString("rol"))
                );
                voluntario.setTelefono(rs.getString("telefono"));
            }

            Conexion.close(rs);

        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar voluntario por correo", e);
        }

        return Optional.ofNullable(voluntario);
    }

    // UPDATE
    public Voluntario update(Voluntario voluntario) {
        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(UPDATE)) {

            stmt.setString(1, voluntario.getNombre());
            stmt.setString(2, voluntario.getApellido());
            stmt.setString(3, voluntario.getNombreUsuario());
            stmt.setString(4, voluntario.getCorreo());
            stmt.setString(5, voluntario.getClave());
            stmt.setString(6, voluntario.getTelefono());
            stmt.setString(7, voluntario.getHabilidades());
            stmt.setString(8, voluntario.getExperiencia());
            stmt.setString(9, voluntario.getAreas_interes());
            stmt.setString(10, voluntario.getDisponibilidad());


            stmt.setLong(11, voluntario.getId());

            return stmt.executeUpdate() > 0 ? voluntario: null;

        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar voluntario", e);
        }
    }

    // DELETE
    public boolean delete(Long idVoluntario) {
        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(DELETE)) {

            stmt.setLong(1, idVoluntario);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar voluntario", e);
        }
    }
}
