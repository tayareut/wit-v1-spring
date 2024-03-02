package com.example.demo.mapper.impl;

import com.example.demo.dto.VinylDto;
import com.example.demo.mapper.Mapper;
import com.example.demo.model.Vinyl;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class VinylMapper implements Mapper<Vinyl, VinylDto> {

    @Override
    public Vinyl dtoToEntity(VinylDto vinylDto) {
        return new Vinyl(vinylDto.getId(), vinylDto.getArtist(), vinylDto.getAlbum(), vinylDto.getReleaseDate());
    }

    @Override
    public VinylDto entityToDto(Vinyl vinyl) {
        return new VinylDto(vinyl.getId(), vinyl.getArtist(), vinyl.getAlbum(), vinyl.getReleaseDate());
    }

    @Override
    public List<Vinyl> dtosToEntities(List<VinylDto> vinylDtos) {
        return vinylDtos.stream()
                .map(this::dtoToEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<VinylDto> entitiesToDtos(List<Vinyl> vinyls) {
        return vinyls.stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }
}
