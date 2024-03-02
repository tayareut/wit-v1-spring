package com.example.demo.mapper;

import java.util.List;

public interface Mapper<Entity, Dto> {
    Entity dtoToEntity(Dto dto);

    Dto entityToDto(Entity entity);

    List<Entity> dtosToEntities(List<Dto> dtos);

    List<Dto> entitiesToDtos(List<Entity> entities);
}
