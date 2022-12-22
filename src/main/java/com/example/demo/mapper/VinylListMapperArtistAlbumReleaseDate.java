package com.example.demo.mapper;

import com.example.demo.dto.VinylDtoArtistAlbumReleaseDate;
import com.example.demo.model.Vinyl;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface VinylListMapperArtistAlbumReleaseDate {
    VinylListMapperArtistAlbumReleaseDate INSTANCE = Mappers.getMapper(VinylListMapperArtistAlbumReleaseDate.class);
    VinylDtoArtistAlbumReleaseDate convert(Vinyl vinyl);
    List<VinylDtoArtistAlbumReleaseDate> convert(List<Vinyl> vinylList);
}
