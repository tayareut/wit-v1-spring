package com.example.demo.mapper;

import com.example.demo.dto.VinylDto;
import com.example.demo.model.Vinyl;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface VinylMapper {
    VinylMapper INSTANCE = Mappers.getMapper(VinylMapper.class);
    VinylDto modelToDto(Vinyl vinyl);
    Vinyl dtoToModel(VinylDto vinylDto);
}
