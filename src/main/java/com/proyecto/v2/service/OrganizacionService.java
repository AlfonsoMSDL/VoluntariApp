package com.proyecto.v2.service;

import com.proyecto.v2.model.Organizacion;
import com.proyecto.v2.repository.OrganizacionDao;

import java.util.List;
import java.util.Optional;

public class OrganizacionService {
    private final OrganizacionDao organizacionDao = new OrganizacionDao();

    public Organizacion save(String nombre, String nombreUsuario, String correo, String clave){
        Organizacion nuevaOrganizacion = new Organizacion(nombre,nombreUsuario,correo,clave);
        Organizacion insertada = organizacionDao.save(nuevaOrganizacion);
        return insertada;
    }

    public List<Organizacion> findAllOrganizaciones(){

        return organizacionDao.findAll();
    }
    public Optional<Organizacion> findByCorreo(String correo){return  organizacionDao.findByCorreo(correo);}
}
