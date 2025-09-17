package com.proyecto.v1.model;

import com.proyecto.v1.model.util.Rol;
import com.proyecto.v1.model.util.Tipo;

public class Organizacion extends Usuario{
    private Tipo tipo;
    private String descripcion;

    public Organizacion(Long id, String nombre, String correo, String telefono, String clave, Rol rol, Tipo tipo, String descripcion, String nombreUsuario) {
        super(id, nombre, correo, telefono, clave, rol, nombreUsuario);
        this.tipo = tipo;
        this.descripcion = descripcion;

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
}
