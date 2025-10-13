package com.proyecto.v2.model;

import com.proyecto.v2.model.EstadoInscripcion;

import java.util.Date;

public class Inscripcion {
    private Long id;
    private Voluntario voluntario;
    private Proyecto proyecto;
    private String motivacion;
    private Date fecha_inscripcion;
    private EstadoInscripcion estado;

    public Inscripcion(Long id, Voluntario voluntario, Proyecto proyecto, String motivacion, Date fecha_inscripcion, EstadoInscripcion estado) {
        this.id = id;
        this.voluntario = voluntario;
        this.proyecto = proyecto;
        this.motivacion = motivacion;
        this.fecha_inscripcion = fecha_inscripcion;
        this.estado = estado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Voluntario getVoluntario() {
        return voluntario;
    }

    public void setVoluntario(Voluntario voluntario) {
        this.voluntario = voluntario;
    }

    public Proyecto getProyecto() {
        return proyecto;
    }

    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
    }

    public String getMotivacion() {
        return motivacion;
    }

    public void setMotivacion(String motivacion) {
        this.motivacion = motivacion;
    }

    public Date getFecha_inscripcion() {
        return fecha_inscripcion;
    }

    public void setFecha_inscripcion(Date fecha_inscripcion) {
        this.fecha_inscripcion = fecha_inscripcion;
    }

    public EstadoInscripcion getEstado() {
        return estado;
    }

    public void setEstado(EstadoInscripcion estado) {
        this.estado = estado;
    }
}
