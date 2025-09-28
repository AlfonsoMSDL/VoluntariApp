package com.proyecto.v1.dto.response;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.proyecto.v1.model.util.Rol;
@JsonIgnoreProperties(ignoreUnknown = true)

public record GetUsuario(
        //Estos seran los datos que se mostraran en el cliente
        @JsonAlias Long id,
        @JsonAlias String correo,
        @JsonAlias String rol,
        @JsonAlias String nombreUsuario
){}