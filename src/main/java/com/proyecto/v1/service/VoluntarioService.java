package com.proyecto.v1.service;

import com.proyecto.v1.dto.response.GetVoluntario;
import com.proyecto.v1.mapper.GenericMapper;
import com.proyecto.v1.model.Voluntario;
import com.proyecto.v1.repository.VoluntarioDao;

import java.util.List;
import java.util.Optional;

public class VoluntarioService {
    private final VoluntarioDao voluntarioDao = new VoluntarioDao();
    private final GenericMapper<Voluntario, GetVoluntario> genericMapper = new GenericMapper();

    public GetVoluntario save(String nombre, String apellido , String nombreUsuario, String correo, String clave) {

        Voluntario nuevoVoluntario = new Voluntario(nombre,apellido,nombreUsuario,correo,clave);
        Voluntario insertado = voluntarioDao.save(nuevoVoluntario);
        return genericMapper.toDto(insertado, GetVoluntario.class);
    }

    public List<Voluntario> findAllVoluntarios(){
        return  voluntarioDao.findAll();
    }
    public Optional<Voluntario> findByCorreo(String correo){
        return voluntarioDao.findByCorreo(correo);
    }
}
