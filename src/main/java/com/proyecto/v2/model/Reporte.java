package com.proyecto.v2.model;

import javax.xml.crypto.Data;

public class Reporte {
    private Long id;
    private Organizacion organizacion;
    private Proyecto proyecto;
    private String contenido;
    private Data fecha_reporte;

    public Reporte(Long id, Organizacion organizacion, Proyecto proyecto, String contenido, Data fecha_reporte) {
        this.id = id;
        this.organizacion = organizacion;
        this.proyecto = proyecto;
        this.contenido = contenido;
        this.fecha_reporte = fecha_reporte;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Organizacion getOrganizacion() {
        return organizacion;
    }

    public void setOrganizacion(Organizacion organizacion) {
        this.organizacion = organizacion;
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

    public Data getFecha_reporte() {
        return fecha_reporte;
    }

    public void setFecha_reporte(Data fecha_reporte) {
        this.fecha_reporte = fecha_reporte;
    }
}
