package com.proyecto.v1.service;

import com.proyecto.v1.dto.response.GetOrganizacion;
import com.proyecto.v1.dto.response.GetOrganizacion;
import com.proyecto.v1.mapper.GenericMapper;
import com.proyecto.v1.model.Organizacion;
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

    public List<GetOrganizacion> findAllOrganizaciones(){
        List<Organizacion> organizacionesList = organizacionDao.findAll();

        List<GetOrganizacion> organizacionesDto= organizacionesList.stream()
                .map(o -> genericMapper.toDto(o,GetOrganizacion.class))
                .toList();

        return organizacionesDto;
    }
    public Optional<Organizacion> findByCorreo(String correo){return  organizacionDao.findByCorreo(correo);}
}
