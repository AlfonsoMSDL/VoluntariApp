package com.proyecto.v1.dto.response;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record GetOrganizacion(
        //Estos seran los datos que se mostraran en el cliente
        @JsonAlias String id,
        @JsonAlias String nombre,
        @JsonAlias String nombreUsuario,
        @JsonAlias String rol,
        @JsonAlias String correo
) {
}
