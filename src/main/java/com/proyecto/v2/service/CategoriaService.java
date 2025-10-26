package com.proyecto.v2.service;

import com.proyecto.v2.model.Categoria;
import com.proyecto.v2.persistence.CategoriaDao;

import java.util.List;

public class CategoriaService {
    private CategoriaDao categoriaDao;
    public CategoriaService() {
        categoriaDao = new CategoriaDao();
    }

    public List<Categoria> findAll() {
        return categoriaDao.findAll();
    }
}
