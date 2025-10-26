package com.proyecto.v2.service;

import com.proyecto.v2.model.Categoria;
import com.proyecto.v2.model.Organizacion;
import com.proyecto.v2.model.Proyecto;
import com.proyecto.v2.persistence.CategoriaDao;
import com.proyecto.v2.persistence.OrganizacionDao;
import com.proyecto.v2.persistence.ProyectoDao;

import java.sql.Date;
import java.util.List;

public class ProyectoService {
    private final ProyectoDao proyectoDao= new ProyectoDao();
    private final CategoriaDao categoriaDao = new CategoriaDao();
    private final OrganizacionDao organizacionDao = new OrganizacionDao();

    public Proyecto save(String nombre, String descripcion, String ubicacion, String requisitos, Date fechaInicio, Date fechaFin, Integer voluntarios_requeridos, Long idCategoria, Long idOrganizacion){
        Categoria categoria = categoriaDao.findById(idCategoria).get();
        Organizacion organizacion = organizacionDao.findById(idOrganizacion).get();

        Proyecto nuevoProyecto = proyectoDao.save(new Proyecto(nombre,descripcion,ubicacion,requisitos,fechaInicio,fechaFin,voluntarios_requeridos,categoria,organizacion)) ;

        return nuevoProyecto;
    }

    public List<Proyecto> findAllProyectosByOrganizacion(Long idOrganizacion){
        return proyectoDao.findAllProyectosByOrganizacion(idOrganizacion);
    }

    public Proyecto update(Long id, String nombre, String descripcion, String ubicacion, String requisitos, Date fechaInicio, Date fechaFin, Integer voluntarios_requeridos, Long idCategoria){
        Categoria categoria = (new CategoriaDao()).findById(idCategoria).get();
        Proyecto proyectoUpdate = new Proyecto(id,nombre,descripcion,ubicacion,requisitos,fechaInicio,fechaFin,voluntarios_requeridos,categoria);
        return proyectoDao.update(proyectoUpdate);
    }
}
