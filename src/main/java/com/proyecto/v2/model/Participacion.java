package com.proyecto.v2.model;

import java.sql.Date;

public class Participacion {
    private Long id;
    private Voluntario  voluntario;
    private Proyecto  proyecto;
    private Date fechaInicio;
    private Date fechaFin;
    private int horasRealizadas;

    public Participacion(Long id, Voluntario voluntario, Proyecto proyecto, Date fechaInicio, Date fechaFin, int horasRealizadas) {
        this.id = id;
        this.voluntario = voluntario;
        this.proyecto = proyecto;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.horasRealizadas = horasRealizadas;
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

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public int getHorasRealizadas() {
        return horasRealizadas;
    }

    public void setHorasRealizadas(int horasRealizadas) {
        this.horasRealizadas = horasRealizadas;
    }

    @Override
    public String toString() {
        return "Participacion{" +
                "id=" + id +
                ", voluntario=" + voluntario +
                ", proyecto=" + proyecto +
                ", fechaInicio=" + fechaInicio +
                ", fechaFin=" + fechaFin +
                ", horasRealizadas=" + horasRealizadas +
                '}';
    }
}
