package com.proyecto.v2.model;

import com.proyecto.v2.model.Categoria;

import java.util.Date;

public class Proyecto {
    private Long id;
    private String nombre;
    private String descripcion;
    private String ubicacion;
    private String requisitos;
    private Date fecha_inicio;
    private Date fecha_fin;
    private Integer voluntarios_requeridos;
    private Categoria categoria;
    private Organizacion organizacion;

    public Proyecto(Long id, String nombre, String descripcion, String ubicacion, String requisitos, Date fecha_inicio, Date fecha_fin, Integer voluntarios_requeridos, Categoria categoria, Organizacion organizacion) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.ubicacion = ubicacion;
        this.requisitos = requisitos;
        this.fecha_inicio = fecha_inicio;
        this.fecha_fin = fecha_fin;
        this.voluntarios_requeridos = voluntarios_requeridos;
        this.categoria = categoria;
        this.organizacion = organizacion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getRequisitos() {
        return requisitos;
    }

    public void setRequisitos(String requisitos) {
        this.requisitos = requisitos;
    }

    public Date getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(Date fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public Date getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(Date fecha_fin) {
        this.fecha_fin = fecha_fin;
    }

    public Integer getVoluntarios_requeridos() {
        return voluntarios_requeridos;
    }

    public void setVoluntarios_requeridos(Integer voluntarios_requeridos) {
        this.voluntarios_requeridos = voluntarios_requeridos;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Organizacion getOrganizacion() {
        return organizacion;
    }

    public void setOrganizacion(Organizacion organizacion) {
        this.organizacion = organizacion;
    }
}
