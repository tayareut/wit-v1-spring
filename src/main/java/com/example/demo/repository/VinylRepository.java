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

    public Vinyl update(int id, Vinyl vinyl) {
        vinyl.setId(id);
        vinylList.add(id, vinyl);
        return vinyl;
    }

    @Override
    public String toString() {
        return "VinylRepository{" +
                "vinylList=" + vinylList +
                '}';
    }
}

