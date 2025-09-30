package com.proyecto.v2.model;

import com.proyecto.v2.model.util.Rol;
import com.proyecto.v2.model.util.Tipo;

public class Organizacion extends Usuario{
    private Tipo tipo;
    private String descripcion;

    public Organizacion(Long id, String nombre, String correo, String telefono, String clave, Rol rol, Tipo tipo, String descripcion, String nombreUsuario) {
        super(id, nombre, correo, telefono, clave, rol, nombreUsuario);
        this.tipo = tipo;
        this.descripcion = descripcion;
        super.setRol(Rol.ORGANIZACION);

    }

    //Este constructor es para guardar las organizaciones en la bd
    public Organizacion(String nombre, String nombreUsuario, String correo, String clave) {
        super(nombre, nombreUsuario, correo, clave);
        super.setRol(Rol.ORGANIZACION);
    }

    //Este constructor es para obtener las organizaciones de la bd
    public Organizacion(Long idORganizacion, String nombre, String correo, String clave,String nombreUsuario,Rol rol) {
        super(idORganizacion,nombre,correo,clave,nombreUsuario,rol);

    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "\nOrganizacion:\n" +
                "Id: " + getId() + "\n" +
                "Nombre: " + super.getNombre() + "\n"+
                "Nombre Usuario: " + super.getNombreUsuario()+"\n"+
                "Correo: "+super.getCorreo()+"\n"+
                "Clave: "+ super.getClave()+"\n"+
                "Rol: "+super.getRol().name()+"\n";

    }
}
