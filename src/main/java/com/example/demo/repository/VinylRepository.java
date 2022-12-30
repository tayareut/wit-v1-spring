package com.example.demo.repository;

import com.example.demo.dto.VinylDto;
import com.example.demo.mapper.VinylMapper;
import com.example.demo.model.Vinyl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class VinylRepository {

    @Autowired
    private VinylMapper vinylMapper;
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

    public List<Vinyl> getVinylsWithArtistFilteredSorted(List<Vinyl> vinylList, String artist) {
        return vinylList.stream()
                .filter(vinyl -> vinyl.getArtist().equals(artist))
                .sorted(Comparator.comparingInt(Vinyl::getReleaseDate)
                        .reversed())
                .collect(Collectors.toList());
    }

    public SearchVinylsResponse searchVinylsByAlbum(int page, int elementsPerPage, String searchLine) {

        List<Vinyl> filteredList = vinylList.stream()
                .filter(vinyl -> vinyl.getAlbum().contains(searchLine))
                .collect(Collectors.toList());

        int startIndex = (page - 1) * elementsPerPage;
        int endIndex = startIndex + elementsPerPage;
        if (endIndex > filteredList.size()) {
            endIndex = filteredList.size();
        }

        List<Vinyl> pageList = filteredList.subList(startIndex,endIndex);

        List<VinylDto> vinylDtoList = pageList.stream()
                .map(vinylMapper::modelToDto)
                .collect(Collectors.toList());

        int totalPages = (int) Math.ceil((double) filteredList.size() / elementsPerPage);

        return new SearchVinylsResponse(filteredList.size(), pageList.size(), searchLine, page, totalPages, vinylDtoList);
    }

    @Override
    public String toString() {
        return "VinylRepository{" +
                "vinylList=" + vinylList +
                '}';
    }
}

