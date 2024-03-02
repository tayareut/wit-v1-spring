package com.example.demo.service;

import com.example.demo.dto.VinylRequest;
import com.example.demo.model.Vinyl;
import com.example.demo.dto.SearchVinylsResponse;

import java.util.List;

public interface VinylService {
    List<Vinyl> getAll();

    Vinyl save(VinylRequest vinylRequest);

    Vinyl getById(int id);

    String deleteById(int id);

    Vinyl update(int id, Vinyl vinyl);

    List<Vinyl> getVinylsWithArtistFilteredSorted(List<Vinyl> vinylList, String artist);

    SearchVinylsResponse searchVinylsByAlbum(int page, int elementsPerPage, String searchLine, String sortField, String sortDirection);

}
