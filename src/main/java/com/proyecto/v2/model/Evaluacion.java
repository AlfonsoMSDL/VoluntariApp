package com.proyecto.v2.model;

public class Evaluacion {
    private Long id;
    private Proyecto proyecto;
    private Voluntario voluntario;
    private Integer calificacion;
    private String observaciones;

    public Evaluacion(Long id, Proyecto proyecto, Voluntario voluntario, Integer calificacion, String observaciones) {
        this.id = id;
        this.proyecto = proyecto;
        this.voluntario = voluntario;
        this.calificacion = calificacion;
        this.observaciones = observaciones;
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
}
