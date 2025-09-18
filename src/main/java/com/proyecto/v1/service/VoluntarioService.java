package com.proyecto.v1.service;

import com.proyecto.v1.model.Voluntario;
import com.proyecto.v1.repository.VoluntarioDao;

import java.util.List;

public class VoluntarioService {
    private final VoluntarioDao voluntarioDao = new VoluntarioDao();



    public Voluntario save(String nombre, String apellido , String nombreUsuario, String correo, String clave) {

        Voluntario nuevoVoluntario = new Voluntario(nombre,apellido,nombreUsuario,correo,clave);
        return voluntarioDao.saveVoluntario(nuevoVoluntario);
    }

    public List<Voluntario> findAllVoluntarios(){
        return  voluntarioDao.findAll();
    }
}
