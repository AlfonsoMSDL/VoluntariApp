package com.proyecto.v2.service;

import com.proyecto.v2.model.Voluntario;
import com.proyecto.v2.persistence.VoluntarioDao;

import java.util.List;
import java.util.Optional;

public class VoluntarioService {
    private final VoluntarioDao voluntarioDao = new VoluntarioDao();

    public Voluntario save(String nombre, String apellido , String nombreUsuario, String correo, String clave) {

        Voluntario nuevoVoluntario = new Voluntario(nombre,apellido,nombreUsuario,correo,clave);
        Voluntario insertado = voluntarioDao.save(nuevoVoluntario);
        return insertado;
    }

    public List<Voluntario> findAllVoluntarios(){
        return voluntarioDao.findAll();
    }
    public Optional<Voluntario> findByCorreo(String correo){
        return voluntarioDao.findByCorreo(correo);
    }
}
