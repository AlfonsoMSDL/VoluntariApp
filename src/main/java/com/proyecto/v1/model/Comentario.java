package com.proyecto.v1.model;

import java.util.Date;

public class Comentario {
    private Long id;
    private Voluntario voluntario;
    private Proyecto proyecto;
    private String contenido;
    private Date fecha_comentario;

    public Comentario(Long id, Voluntario voluntario, Proyecto proyecto, String contenido, Date fecha_comentario) {
        this.id = id;
        this.voluntario = voluntario;
        this.proyecto = proyecto;
        this.contenido = contenido;
        this.fecha_comentario = fecha_comentario;
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

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public Date getFecha_comentario() {
        return fecha_comentario;
    }

    public void setFecha_comentario(Date fecha_comentario) {
        this.fecha_comentario = fecha_comentario;
    }
}
