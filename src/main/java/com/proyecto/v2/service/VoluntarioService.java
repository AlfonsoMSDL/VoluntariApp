package com.proyecto.v2.service;

import com.proyecto.v2.model.Organizacion;
import com.proyecto.v2.model.Voluntario;
import com.proyecto.v2.persistence.VoluntarioDao;

import java.util.List;
import java.util.Optional;

public class VoluntarioService {
    private final VoluntarioDao voluntarioDao = new VoluntarioDao();

    public Voluntario save(String nombre, String apellido , String nombreUsuario, String correo, String clave,String telefono) {

        Voluntario nuevoVoluntario = new Voluntario(nombre,apellido,nombreUsuario,correo,clave);
        nuevoVoluntario.setTelefono(telefono);
        Voluntario insertado = voluntarioDao.save(nuevoVoluntario);
        return insertado;
    }

    public List<Voluntario> findAllVoluntarios(){
        return voluntarioDao.findAll();
    }
    public Optional<Voluntario> findByCorreo(String correo){
        return voluntarioDao.findByCorreo(correo);
    }

    public Voluntario update(Long id, String nombre, String apellido, String correo, String telefono, String clave, String habilidades, String experiencia, String disponibilidad, String areas_interes, String nombreUsuario){

        if(clave == null || clave.trim().isEmpty()){
            //No se proporciono una clave, por lo tanto, se deja la misma de antes
            Voluntario voluntarioActual =  voluntarioDao.findById(id).get();
            clave = voluntarioActual.getClave();
        }

        Voluntario voluntarioActualizar = new Voluntario(id,nombre,apellido,correo,telefono,clave,habilidades,experiencia,disponibilidad,areas_interes,nombreUsuario);

        return  voluntarioDao.update(voluntarioActualizar);
    }
}
