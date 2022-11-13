package com.example.demo.service.impl;

import com.example.demo.model.Vinyl;
import com.example.demo.repository.VinylRepository;
import com.example.demo.service.VinylService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VinylServiceImpl implements VinylService {
    @Autowired
    private VinylRepository vinylRepository;

    @Override
    public List<Vinyl> getAll() {
        return vinylRepository.getAll();
    }

    @Override
    public Vinyl save(Vinyl vinyl) {
        return vinylRepository.save(vinyl);
    }

    @Override
    public Vinyl getById(int id) {
        return vinylRepository.getById(id);
    }

    @Override
    public String deleteById(int id) {
        vinylRepository.deleteById(id);
        return "product removed !! " + id;
    }

    @Override
    public Vinyl update(Vinyl vinyl) {
        return vinylRepository.update(vinyl);
    }
}
