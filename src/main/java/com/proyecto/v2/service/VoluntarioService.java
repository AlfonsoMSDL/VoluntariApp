package com.proyecto.v2.service;

import com.proyecto.v2.model.Organizacion;
import com.proyecto.v2.model.Rol;
import com.proyecto.v2.model.Usuario;
import com.proyecto.v2.model.Voluntario;
import com.proyecto.v2.persistence.RolDao;
import com.proyecto.v2.persistence.UsuarioDao;
import com.proyecto.v2.persistence.VoluntarioDao;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class VoluntarioService {
    private final VoluntarioDao voluntarioDao = new VoluntarioDao();
    private final UsuarioDao usuarioDao = new UsuarioDao();

    public Voluntario save(String nombre, String apellido , String nombreUsuario, String correo, String clave,String telefono) {

        Rol rolInsertar = (new RolDao()).findByNombre("Voluntario").get();

        //Se guarda primero el usuario
        Usuario usuarioGuardado = usuarioDao.save(new Usuario(nombre,correo,telefono,clave,rolInsertar,nombreUsuario));

        Voluntario  voluntarioGuardado= voluntarioDao.save(new Voluntario(usuarioGuardado.getId(),apellido));

        voluntarioGuardado.establecerValoresUsuario(usuarioGuardado);
        return voluntarioGuardado;
    }


    public List<Voluntario> findAllVoluntarios(){
        return voluntarioDao.findAll();
    }

    public Optional<Voluntario> findByCorreo(String correo){
        return voluntarioDao.findByCorreo(correo);
    }
    public Optional<Voluntario> findById(Long id) {
        return voluntarioDao.findById(id);
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
