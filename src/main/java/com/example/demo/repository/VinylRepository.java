package com.example.demo.repository;

import com.example.demo.model.Vinyl;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class VinylRepository {
    private List<Vinyl> vinylList = new ArrayList<>();

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
        int idVinyl = 0;
        int id = 0;
        for (int i = 0; i < vinylList.size(); i++) {
            if (vinylList.get(i).getId() == (vinyl.getId())) {
                id = vinyl.getId();
                idVinyl = i;
                break;
            }
        }
        Vinyl newVinyl = new Vinyl();
        newVinyl.setId(id);
        newVinyl.setArtist(vinyl.getArtist());
        newVinyl.setAlbum(vinyl.getAlbum());
        vinylList.set(idVinyl, vinyl);
        return newVinyl;
    }
}

