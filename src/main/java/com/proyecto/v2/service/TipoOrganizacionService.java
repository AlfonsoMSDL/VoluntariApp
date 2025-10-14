package com.proyecto.v2.service;

import com.proyecto.v2.model.TipoOrganizacion;
import com.proyecto.v2.persistence.TipoOrganizacionDao;

import java.util.List;

public class TipoOrganizacionService {
    private TipoOrganizacionDao tipoOrganizacionDao = new TipoOrganizacionDao();
    public List<TipoOrganizacion> findAll(){
        return tipoOrganizacionDao.findAll();
    }
}
