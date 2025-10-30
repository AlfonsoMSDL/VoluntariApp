package com.proyecto.v2.service;

import com.proyecto.v2.dto.response.GetOrganizacion;
import com.proyecto.v2.dto.response.GetTipoOrganizacion;
import com.proyecto.v2.mapper.GenericMapper;
import com.proyecto.v2.model.Organizacion;
import com.proyecto.v2.model.TipoOrganizacion;
import com.proyecto.v2.persistence.TipoOrganizacionDao;

import java.util.List;

public class TipoOrganizacionService {
    private TipoOrganizacionDao tipoOrganizacionDao = new TipoOrganizacionDao();
    private final GenericMapper<GetTipoOrganizacion, TipoOrganizacion> genericMapper = new  GenericMapper<>();
    public List<GetTipoOrganizacion> findAll(){
        List<TipoOrganizacion> tiposOrganizaciones =  tipoOrganizacionDao.findAll();


        return tiposOrganizaciones.stream()
                .map(t -> genericMapper.toDto(t,GetTipoOrganizacion.class))
                .toList();
    }
}
