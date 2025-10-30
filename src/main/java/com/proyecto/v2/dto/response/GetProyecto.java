package com.proyecto.v2.dto.response;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.proyecto.v2.model.Categoria;
import com.proyecto.v2.model.Organizacion;

import java.sql.Date;

public record GetProyecto(
        @JsonAlias String id,
        @JsonAlias String nombre,
        @JsonAlias String descripcion,
        @JsonAlias String ubicacion,
        @JsonAlias String requisitos,
        @JsonAlias String fecha_inicio,
        @JsonAlias String fecha_fin,
        @JsonAlias String voluntarios_requeridos,
        @JsonAlias GetCategoria categoria,
        @JsonAlias GetOrganizacion organizacion
        
) {
}
