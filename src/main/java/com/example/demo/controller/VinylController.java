package com.example.demo.controller;

import com.example.demo.dto.VinylDto;
import com.example.demo.mapper.impl.VinylMapper;
import com.example.demo.model.Vinyl;
import com.example.demo.service.VinylService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class VinylController {
    @Autowired
    private VinylService vinylService;

    @Autowired
    private VinylMapper vinylMapper;

    @GetMapping("/vinyls")
    public ResponseEntity<List<Vinyl>> getAllVinyls() {
        return ResponseEntity.ok(vinylService.getAll());
    }

    @GetMapping("/vinyls/{id}")
    public Vinyl getVinylById(@PathVariable int id) {
        return ResponseEntity.ok(vinylService.getById(id)).getBody();
    }
    @GetMapping("/vinylsdto/{id}")
    public ResponseEntity<VinylDto> getVinylDtoById(@PathVariable int id) {
        VinylDto vinylDto = vinylMapper.entityToDto(vinylService.getById(id));

        return ResponseEntity.ok(vinylDto);
    }

    @PostMapping("/vinyl")
    public ResponseEntity<Vinyl> createVinyl(@RequestBody Vinyl vinyl) {
        return ResponseEntity.ok(vinylService.save(vinyl));
    }

    @PutMapping("/vinyls/{id}")
    public void updateVinyl(@RequestBody Vinyl vinyl) {
        vinylService.update(vinyl);
    }

    @DeleteMapping("/vinyls/{id}")
    public ResponseEntity<String> deleteVinyl(@PathVariable("id") int id) {
       return ResponseEntity.ok(vinylService.deleteById(id));
    }
}

