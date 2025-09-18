package com.proyecto.v1.service;

import com.proyecto.v1.model.Organizacion;
import com.proyecto.v1.repository.OrganizacionDao;

public class OrganizacionService {
    private final OrganizacionDao organizacionDao = new OrganizacionDao();

    public Organizacion save(String nombre, String nombreUsuario, String correo, String clave){
        Organizacion nuevaOrganizacion = new Organizacion(nombre,nombreUsuario,correo,clave);
        return organizacionDao.saveOrganizacion(nuevaOrganizacion);
    }
}
