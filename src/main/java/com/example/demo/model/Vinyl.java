package com.example.demo.model;

import java.io.Serializable;

public class Vinyl implements Serializable {
    private int id;
    private String artist;
    private String album;

    public Vinyl() {}

    public int getId() {
        return this.id;
    }

    public String getArtist() {
        return this.artist;
    }

    public String getAlbum() {
        return this.album;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public Vinyl(int id, String artist, String album) {
        this.id = id;
        this.artist = artist;
        this.album = album;
    }
}


