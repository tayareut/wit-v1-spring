package com.example.demo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class VinylDto implements Serializable {
    private int id;
    private String artist;
    private String album;
    private int releaseDate;

    public VinylDto(int id, String artist, String album, int releaseDate) {
        this.id = id;
        this.artist = artist;
        this.album = album;
        this.releaseDate = releaseDate;
    }
}
