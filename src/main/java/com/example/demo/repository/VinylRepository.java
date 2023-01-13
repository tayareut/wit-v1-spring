package com.example.demo.repository;

import com.example.demo.dto.SearchVinylsResponse;
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
    private final List<Vinyl> vinylRequestList = new ArrayList<>();

    public List<Vinyl> getAll() {
        return vinylRequestList;
    }

    public Vinyl getById(int id) {
        for (Vinyl vinyl : vinylRequestList) {
            if (vinyl.getId() == (id)) {
                return vinyl;
            }
        }
        return null;
    }

    public Vinyl save(Vinyl vinyl) {
        vinylRequestList.add(vinyl);
        return vinyl;
    }

    public void deleteById(int id) {
        vinylRequestList.removeIf(vinylRequest -> vinylRequest.getId() == (id));
    }

    public Vinyl update(int id, Vinyl vinyl) {
        vinyl.setId(id);
        vinylRequestList.add(id, vinyl);
        return vinyl;
    }

    public List<Vinyl> getVinylsWithArtistFilteredSorted(List<Vinyl> vinylList, String artist) {
        return vinylList.stream()
                .filter(vinyl -> vinyl.getArtist().equalsIgnoreCase(artist))
                .sorted(Comparator.comparingInt(Vinyl::getReleaseDate)
                        .reversed())
                .collect(Collectors.toList());
    }

    public SearchVinylsResponse searchVinylsByAlbum(int page, int elementsPerPage, String searchLine, String sortField, String sortDirection) {

        List<Vinyl> filteredList = vinylRequestList.stream()
                .filter(vinylRequest -> vinylRequest.getAlbum().toLowerCase().contains(searchLine.toLowerCase()))
                .collect(Collectors.toList());

        filteredList = sortVinyls(filteredList,sortField,sortDirection);

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

        return new SearchVinylsResponse(filteredList.size(), pageList.size(), searchLine, page, totalPages, sortField, sortDirection, vinylDtoList);
    }

    public  List<Vinyl> sortVinyls(List<Vinyl> vinylRequestList, String sortField, String sortDirection) {
        if (sortField == null || sortField.trim().isEmpty()) {
            sortField = "id";
        }
        if (sortDirection == null || sortDirection.trim().isEmpty()) {
            sortDirection = "asc";
        }

        Comparator<Vinyl> comparator = switch (sortField) {
            case "artist" -> Comparator.comparing(Vinyl::getArtist);
            case "album" -> Comparator.comparing(Vinyl::getAlbum);
            case "releaseDate" -> Comparator.comparing(Vinyl::getReleaseDate);
            default -> Comparator.comparingInt(Vinyl::getId);
        };

        if (sortDirection.equalsIgnoreCase("desc")) {
            comparator = comparator.reversed();
        }

        return vinylRequestList.stream()
                .sorted(comparator)
                .collect(Collectors.toList());
    }
}

