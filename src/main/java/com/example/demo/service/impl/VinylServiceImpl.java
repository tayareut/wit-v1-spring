package com.example.demo.service.impl;

import com.example.demo.exception.NonPositiveNumException;
import com.example.demo.exception.NotNullValueException;
import com.example.demo.model.Vinyl;
import com.example.demo.repository.VinylRepository;
import com.example.demo.service.VinylService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VinylServiceImpl implements VinylService {
    Logger logger = LoggerFactory.getLogger(VinylServiceImpl.class);
    @Autowired
    private VinylRepository vinylRepository;

    @Override
    public List<Vinyl> getAll() {
        logger.info("Get all vinyls method for all posted vinyls: {}", vinylRepository.toString());
        return vinylRepository.getAll();
    }

    @Override
    public Vinyl save(Vinyl vinyl) {
        logger.info("Save vinyl method for vinyl with album name: {}", vinyl.getAlbum());
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
        logger.info("Get vinyl method for vinyl by id: {}", id);
        return vinylRepository.getById(id);
    }

    @Override
    public String deleteById(int id) {
        logger.info("Delete vinyl method for vinyl by id: {}", id);
        vinylRepository.deleteById(id);
        return "product removed !! " + id;
    }

    @Override
    public Vinyl update(int id, Vinyl vinyl) {
        logger.info("Update vinyl method for vinyl with id: {}", vinyl.getId());

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

        return vinylRepository.update(id, vinyl);
    }
}
