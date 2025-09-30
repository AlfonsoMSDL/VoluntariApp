package com.proyecto.v2.model;

import java.util.Date;

public class Certificado {
    private Long id;
    private Voluntario voluntario;
    private Proyecto proyecto;
    private Date fecha_emision;
    private String url_documento;

    public Certificado(Long id, Voluntario voluntario, Proyecto proyecto, Date fecha_emision, String url_documento) {
        this.id = id;
        this.voluntario = voluntario;
        this.proyecto = proyecto;
        this.fecha_emision = fecha_emision;
        this.url_documento = url_documento;
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

    public Date getFecha_emision() {
        return fecha_emision;
    }

    public void setFecha_emision(Date fecha_emision) {
        this.fecha_emision = fecha_emision;
    }

    public String getUrl_documento() {
        return url_documento;
    }

    public void setUrl_documento(String url_documento) {
        this.url_documento = url_documento;
    }
}
