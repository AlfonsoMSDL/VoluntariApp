package com.proyecto.v2.service;

import com.proyecto.v2.persistence.OrganizacionDao;

public class OrganizacionService {
    private final OrganizacionDao organizacionDao = new OrganizacionDao();

    /*
    public Organizacion save(String nombre, String nombreUsuario, String correo, String clave,String telefono, String tipoString){
        Organizacion nuevaOrganizacion = new Organizacion(nombre,nombreUsuario,correo,clave);

        TipoOrganizacion tipoOrganizacion = TipoOrganizacion.fromString(tipoString);//Traigo un enum dado un string
        nuevaOrganizacion.setTipo(tipoOrganizacion);
        nuevaOrganizacion.setTelefono(telefono);

        Organizacion insertada = organizacionDao.save(nuevaOrganizacion);

        return insertada;
    }

    public List<Organizacion> findAllOrganizaciones(){

        return organizacionDao.findAll();
    }
    public Optional<Organizacion> findByCorreo(String correo){return  organizacionDao.findByCorreo(correo);}

    public Organizacion update(Long id, String nombre, String correo, String telefono, String clave, String tipo, String descripcion, String nombreUsuario){

        if(clave == null || clave.trim().isEmpty()){
            //No se proporciono una clave, por lo tanto, se deja la misma de antes
            Organizacion organizacionActual =  organizacionDao.findById(id).get();
            clave = organizacionActual.getClave();
        }

        Organizacion organizacionUpdate = new Organizacion(id,nombre,correo,telefono,clave,nombreUsuario, TipoOrganizacion.fromString(tipo),descripcion);
        return organizacionDao.update(organizacionUpdate);
    }

     */
}
