package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Vinyl implements Serializable {

    private int id;
    private String artist;
    private String album;
    private int releaseDate;
    private int listPrice;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vinyl vinyl = (Vinyl) o;
        return id == vinyl.id && releaseDate == vinyl.releaseDate && listPrice == vinyl.listPrice && artist.equals(vinyl.artist) && album.equals(vinyl.album);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, artist, album, releaseDate, listPrice);
    }

    @Override
    public String toString() {
        return "Vinyl{" +
                "id=" + id +
                ", artist='" + artist + '\'' +
                ", album='" + album + '\'' +
                ", releaseDate=" + releaseDate +
                ", listPrice=" + listPrice +
                '}';
    }
}


