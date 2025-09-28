package com.proyecto.v1.dto.response;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record GetVoluntario(
        //Estos seran los datos que se mostraran en el cliente

        @JsonAlias Long id,
        @JsonAlias String nombre,
        @JsonAlias String apellido,
        @JsonAlias String rol,
        @JsonAlias String correo,
        @JsonAlias String nombreUsuario


) {
}
