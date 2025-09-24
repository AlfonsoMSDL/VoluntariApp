package com.proyecto.v1.mapper;

import com.proyecto.v1.dto.response.GetVoluntario;

public class GenericMapper<Entity, Dto> {
    private final JsonMapper<Entity> entityMapper = new JsonMapper<>();
    private final JsonMapper<Dto> dtoMapper = new JsonMapper<>();

    public Dto toDto(Entity entidad, Class<Dto> dtoClass) {
        String json = entityMapper.toJson(entidad);
        return dtoMapper.fromJson(json, dtoClass);
    }

    public Entity toEntity(Dto dto, Class<Entity> entityClass) {
        String json = dtoMapper.toJson(dto);
        return entityMapper.fromJson(json, entityClass);
    }
}