package com.proyecto.v2.service;

import com.proyecto.v2.model.Organizacion;
import com.proyecto.v2.model.Proyecto;
import com.proyecto.v2.persistence.ProyectoDao;

import java.util.List;

public class ProyectoService {
    private final ProyectoDao proyectoDao= new ProyectoDao();

    public List<Proyecto> findAllProyectosByOrganizacion(Long idOrganizacion){
        return proyectoDao.findAllProyectosByOrganizacion(idOrganizacion);
    }
}
