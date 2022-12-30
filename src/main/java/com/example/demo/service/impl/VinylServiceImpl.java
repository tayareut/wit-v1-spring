package com.example.demo.service.impl;

import com.example.demo.exception.NonPositiveNumException;
import com.example.demo.exception.NotNullValueException;
import com.example.demo.model.Vinyl;
import com.example.demo.repository.SearchVinylsResponse;
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
    public Vinyl save(Vinyl vinyl) {
        log.info("Save vinyl method for vinyl with album name: {}", vinyl.getAlbum());
        if (vinyl.getArtist() == null) {
            throw new NotNullValueException("Artist name must be not null");
        }

        if (vinyl.getAlbum() == null) {
            throw new NotNullValueException("Album name must be not null");
        }

        if (vinyl.getId() <= 0) {
            throw new NonPositiveNumException("Id must be more than 0");
        }

        if (vinyl.getReleaseDate() <= 0) {
            throw new NonPositiveNumException("Release Date must be more than 0");
        }

        if (vinyl.getListPrice() <= 0) {
            throw new NonPositiveNumException("List Price must be more than 0");
        }

        return vinylRepository.save(vinyl);
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

        if (vinyl.getArtist() == null) {
            throw new NotNullValueException("Artist name must be not null");
        }

        if (vinyl.getAlbum() == null) {
            throw new NotNullValueException("Album name must be not null");
        }

        if (vinyl.getId() < 1) {
            throw new NonPositiveNumException("Id must be more than 0");
        }

        if (vinyl.getReleaseDate() < 1) {
            throw new NonPositiveNumException("Release Date must be more than 0");
        }

        if (vinyl.getListPrice() < 1) {
            throw new NonPositiveNumException("List Price must be more than 0");
        }

        return vinylRepository.update(id, vinyl);
    }

    @Override
    public List<Vinyl> getVinylsWithArtistFilteredSorted(List<Vinyl> vinylList, String artist) {
        log.info("Get vinyl records method filtered with artist name and sorted descending by release date: {}", artist);
        return vinylRepository.getVinylsWithArtistFilteredSorted(vinylList, artist);
    }

    @Override
    public SearchVinylsResponse searchVinylsByAlbum(int page, int elementsPerPage, String searchLine) {
        log.info("Search vinyl records method by album name:" + searchLine + ". Page number of search results:" + page + ", elements per page:" + elementsPerPage);

        if (page < 1) {
            log.error(page + "is not a valid page number");
            throw new NonPositiveNumException("Page number is less than 1");
        }

        if (elementsPerPage < 1) {
            log.error(elementsPerPage + "is not a valid elementsPerPage");
            throw new NonPositiveNumException("Number of elements per page is less than 1");
        }

        if (searchLine == null || searchLine.trim().isEmpty()) {
            log.error(searchLine + "is not a valid searchLine");
            throw new NotNullValueException("Search line is empty");
        }

        return vinylRepository.searchVinylsByAlbum(page, elementsPerPage, searchLine);
    }
}
