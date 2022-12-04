package com.example.demo.repository;

import com.example.demo.model.Vinyl;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class VinylRepository {
    private final List<Vinyl> vinylList = new ArrayList<>();

    public List<Vinyl> getAll() {
        return vinylList;
    }

    public Vinyl getById(int id) {
        for (Vinyl vinyl : vinylList) {
            if (vinyl.getId() == (id)) {
                return vinyl;
            }
        }
        return null;
    }

    public Vinyl save(Vinyl vinyl) {
        vinylList.add(vinyl);
        return vinyl;
    }

    public void deleteById(int id) {
        vinylList.removeIf(vinyl -> vinyl.getId() == (id));
    }

    public Vinyl update(Vinyl vinyl) {
        Vinyl newVinyl = new Vinyl(vinyl.getId(), vinyl.getArtist(), vinyl.getAlbum(), vinyl.getReleaseDate(), vinyl.getListPrice());
        for (int i = 0; i < vinylList.size(); i++) {
            if (vinylList.get(i).getId() == (vinyl.getId())) {
                newVinyl.setId(vinyl.getId());
                newVinyl.setArtist(vinyl.getArtist());
                newVinyl.setAlbum(vinyl.getAlbum());
                newVinyl.setListPrice(vinyl.getListPrice());
                vinylList.set(i, vinyl);
                break;
            }
        }
        return newVinyl;
    }

    @Override
    public String toString() {
        return "VinylRepository{" +
                "vinylList=" + vinylList +
                '}';
    }
}

