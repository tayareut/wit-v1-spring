package com.example.demo.service;

import com.example.demo.model.Vinyl;

import java.util.List;

public interface VinylService {
    List<Vinyl> getAll();

    Vinyl save(Vinyl vinyl);

    Vinyl getById(int id);

    String deleteById(int id);

    Vinyl update(int id, Vinyl vinyl);

    List<Vinyl> getAllFilteredSortedVinyls(List<Vinyl> vinyls);
}
