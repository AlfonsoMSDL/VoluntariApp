package com.proyecto.v2.service;

import com.proyecto.v2.model.Organizacion;
import com.proyecto.v2.model.Rol;
import com.proyecto.v2.model.TipoOrganizacion;
import com.proyecto.v2.model.Usuario;
import com.proyecto.v2.persistence.OrganizacionDao;
import com.proyecto.v2.persistence.RolDao;
import com.proyecto.v2.persistence.TipoOrganizacionDao;
import com.proyecto.v2.persistence.UsuarioDao;

import java.util.List;
import java.util.Optional;

public class OrganizacionService {
    private final OrganizacionDao organizacionDao = new OrganizacionDao();
    private final UsuarioDao  usuarioDao = new UsuarioDao();
    private final TipoOrganizacionDao tipoOrganizacionDao = new TipoOrganizacionDao();
    private final RolDao rolDao = new RolDao();


    public Organizacion save(String nombre, String nombreUsuario, String correo, String clave, String telefono, Long idTipo){

        //Primero busco el rol en la BD para insertarlo en el usuario nuevo
        Rol rolInsertar = (new RolDao()).findByNombre("Organizacion").get();
        //Se guarda primero el usuario

        Usuario usuarioGuardado = usuarioDao.save(new Usuario(nombre,correo,telefono,clave,rolInsertar,nombreUsuario));

        //Luego busco el tipo de organizacion en la BD para para agregarselo a la organizacion
        TipoOrganizacion tipoOrganizacion = tipoOrganizacionDao.findById(idTipo).get();

        Organizacion nuevaOrganizacion = new Organizacion(usuarioGuardado.getId(),tipoOrganizacion);


        Organizacion insertada = organizacionDao.save(nuevaOrganizacion);
        insertada.establecerValoresUsuario(usuarioGuardado);

        return insertada;
    }



    public List<Organizacion> findAllOrganizaciones(){

        return organizacionDao.findAll();
    }

    public Optional<Organizacion> findByCorreo(String correo){
        return  organizacionDao.findByCorreo(correo);
    }

    public Optional<Organizacion> findById(Long id){
        return  organizacionDao.findById(id);
    }


    public Organizacion update(Long id, String nombre, String correo, String telefono, String clave, Long idTipo, String descripcion, String nombreUsuario){

        if(clave == null || clave.trim().isEmpty()){
            //No se proporciono una clave, por lo tanto, se deja la misma de antes
            Organizacion organizacionActual =  organizacionDao.findById(id).get();
            clave = organizacionActual.getClave();
        }

        TipoOrganizacion tipoOrganizacion  = tipoOrganizacionDao.findById(idTipo).get();

        Organizacion organizacionUpdate = new Organizacion(id,nombre,correo,telefono,clave,nombreUsuario, tipoOrganizacion,descripcion);
        return organizacionDao.update(organizacionUpdate);
    }


}
