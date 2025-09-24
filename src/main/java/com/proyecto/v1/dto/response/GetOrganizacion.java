package com.proyecto.v1.dto.response;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record GetOrganizacion(
        @JsonAlias String nombre,
        @JsonAlias String nombreUsuario,
        @JsonAlias String correo
) {
}
