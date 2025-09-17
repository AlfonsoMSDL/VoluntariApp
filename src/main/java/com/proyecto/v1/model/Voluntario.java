package com.proyecto.v1.model;

import com.proyecto.v1.model.util.Rol;

public class Voluntario extends Usuario {
    private String apellido;
    private String habilidades;
    private String experiencia;
    private String disponibilidad;
    private String areas_interes;

    public Voluntario(Long id, String nombre, String apellido, String correo, String telefono, String clave, Rol rol, String habilidades, String experiencia, String disponibilidad, String areas_interes, String nombreUsuario) {
        super(id, nombre, correo, telefono, clave, rol,nombreUsuario);
        this.habilidades = habilidades;
        this.experiencia = experiencia;
        this.disponibilidad = disponibilidad;
        this.areas_interes = areas_interes;
        this.apellido = apellido;
    }

    public Voluntario(String nombre, String apellido, String nombreUsuario, String correo, String clave) {
        super(nombre,nombreUsuario,correo,clave);
        this.apellido = apellido;
    }

    public String getHabilidades() {
        return habilidades;
    }

    public void setHabilidades(String habilidades) {
        this.habilidades = habilidades;
    }

    public String getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(String experiencia) {
        this.experiencia = experiencia;
    }

    public String getDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(String disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public String getAreas_interes() {
        return areas_interes;
    }

    public void setAreas_interes(String areas_interes) {
        this.areas_interes = areas_interes;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
}
