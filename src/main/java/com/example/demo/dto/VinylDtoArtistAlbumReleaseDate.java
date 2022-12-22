package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VinylDtoArtistAlbumReleaseDate implements Serializable {

    private String artist;
    private String album;
    private int releaseDate;
}
