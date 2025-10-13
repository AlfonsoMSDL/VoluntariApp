package com.proyecto.v2.model;

import java.sql.Date;

public class Evaluacion {
    private Long id;
    private Proyecto proyecto;
    private Voluntario voluntario;
    private Organizacion organizacion;
    private Integer calificacion;
    private String observaciones;
    private Date fecha;

    public Evaluacion(Long id, Proyecto proyecto, Voluntario voluntario, Organizacion organizacion, Integer calificacion, String observaciones, Date fecha) {
        this.id = id;
        this.proyecto = proyecto;
        this.voluntario = voluntario;
        this.organizacion = organizacion;
        this.calificacion = calificacion;
        this.observaciones = observaciones;
        this.fecha = fecha;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Proyecto getProyecto() {
        return proyecto;
    }

    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
    }

    public Voluntario getVoluntario() {
        return voluntario;
    }

    public void setVoluntario(Voluntario voluntario) {
        this.voluntario = voluntario;
    }

    public Organizacion getOrganizacion() {
        return organizacion;
    }

    public void setOrganizacion(Organizacion organizacion) {
        this.organizacion = organizacion;
    }

    public Integer getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(Integer calificacion) {
        this.calificacion = calificacion;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}
