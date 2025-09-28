package com.proyecto.v1.service;

import com.proyecto.v1.dto.response.GetOrganizacion;
import com.proyecto.v1.mapper.GenericMapper;
import com.proyecto.v1.model.Organizacion;
import com.proyecto.v1.repository.OrganizacionDao;

import java.util.List;
import java.util.Optional;

public class OrganizacionService {
    private final OrganizacionDao organizacionDao = new OrganizacionDao();
    private final GenericMapper<Organizacion, GetOrganizacion> genericMapper = new GenericMapper();

    public GetOrganizacion save(String nombre, String nombreUsuario, String correo, String clave){
        Organizacion nuevaOrganizacion = new Organizacion(nombre,nombreUsuario,correo,clave);
        Organizacion insertada = organizacionDao.save(nuevaOrganizacion);
        return genericMapper.toDto(insertada,GetOrganizacion.class);
    }

    public List<Organizacion> findAllOrganizaciones(){
        return organizacionDao.findAll();
    }
    public Optional<Organizacion> findByCorreo(String correo){return  organizacionDao.findByCorreo(correo);}
}
