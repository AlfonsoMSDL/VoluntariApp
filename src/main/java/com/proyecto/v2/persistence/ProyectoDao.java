package com.proyecto.v2.persistence;

import com.proyecto.v2.model.Categoria;
import com.proyecto.v2.model.Organizacion;
import com.proyecto.v2.model.Proyecto;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;

public class ProyectoDao {

    private static final String SELECT_BY_ORGANIZACION = "SELECT * FROM proyectos WHERE id = ?";
    private static final String INSERT = "INSERT INTO productos (nombre, descripcion, ubicacion, requisitos, fecha_inicio, fecha_fin, voluntarios_requeridos, id_categoria, id_organizacion) VALUES (?,?,?,?,?,?,?,?,?)";


    public Proyecto save(Proyecto proyecto) {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = Conexion.getConnection();
            ps = conn.prepareStatement(INSERT,Statement.RETURN_GENERATED_KEYS);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public List<Proyecto> findAllProyectosByOrganizacion(Long idOrganizacion){
        Connection connection ;
        PreparedStatement preparedStatement;
        ResultSet resultSet;

        List<Proyecto> proyectos = new ArrayList<>();
        Proyecto proyecto;

        try {
            connection = Conexion.getConnection();
            preparedStatement = connection.prepareStatement(SELECT_BY_ORGANIZACION);
            preparedStatement.setLong(1, idOrganizacion);
            resultSet = preparedStatement.executeQuery();


            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String nombre = resultSet.getString("nombre");
                String descripcion = resultSet.getString("descripcion");
                String ubicacion = resultSet.getString("ubicacion");
                String requisitos = resultSet.getString("requisitos");
                Date fechaInicio = resultSet.getDate("fecha_inicio");
                Date fechaFin = resultSet.getDate("fecha_fin");
                int voluntariosRequeridos = resultSet.getInt("voluntarios_requeridos");


                Categoria categoria = (new CategoriaDao()).findById(resultSet.getLong("id_categoria")).get();

                Organizacion organizacion = (new OrganizacionDao()).findById(resultSet.getLong("organizacion_id")).get();

                proyecto = new Proyecto(id,nombre,descripcion,ubicacion,requisitos,fechaInicio,fechaFin,voluntariosRequeridos,categoria,organizacion);

                proyectos.add(proyecto);


            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return proyectos;

    }





}
