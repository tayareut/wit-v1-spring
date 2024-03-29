package com.example.demo.service.impl;

import com.example.demo.dto.VinylRequest;
import com.example.demo.model.Vinyl;
import com.example.demo.dto.SearchVinylsResponse;
import com.example.demo.repository.VinylRepository;
import com.example.demo.service.VinylService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class VinylServiceImpl implements VinylService {
    @Autowired
    private VinylRepository vinylRepository;

    @Override
    public List<Vinyl> getAll() {
        log.info("Get all vinyls method for all posted vinyls: {}", vinylRepository.toString());
        return vinylRepository.getAll();
    }

    @Override
    public Vinyl save(VinylRequest vinylRequest) {
        log.info("Save vinyl method for vinyl with album name: {}", vinylRequest.getAlbum());
        return vinylRepository.save(new Vinyl(vinylRequest.getId(),vinylRequest.getArtist(),vinylRequest.getAlbum(),vinylRequest.getReleaseDate(),vinylRequest.getListPrice()));
    }

    @Override
    public Vinyl getById(int id) {
        log.info("Get vinyl method for vinyl by id: {}", id);
        return vinylRepository.getById(id);
    }

    @Override
    public String deleteById(int id) {
        log.info("Delete vinyl method for vinyl by id: {}", id);
        vinylRepository.deleteById(id);
        return "product removed !! " + id;
    }

    @Override
    public Vinyl update(int id, Vinyl vinyl) {
        log.info("Update vinyl method for vinyl with id: {}", vinyl.getId());
        return vinylRepository.update(id, vinyl);
    }

    @Override
    public List<Vinyl> getVinylsWithArtistFilteredSorted(List<Vinyl> vinylList, String artist) {
        log.info("Get vinyl records method filtered with artist name and sorted descending by release date: {}", artist);
        return vinylRepository.getVinylsWithArtistFilteredSorted(vinylList, artist);
    }

    @Override
    public SearchVinylsResponse searchVinylsByAlbum(int page, int elementsPerPage, String searchLine, String sortField, String sortDirection) {
        log.info("Search vinyl records method by album name:" + searchLine + ". Page number of search results:" + page + ", elements per page:" + elementsPerPage);
        return vinylRepository.searchVinylsByAlbum(page, elementsPerPage, searchLine, sortField, sortDirection);
    }
}
