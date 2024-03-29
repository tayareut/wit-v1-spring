package com.example.demo.controller;

import com.example.demo.dto.VinylDto;
import com.example.demo.dto.VinylDtoArtistAlbumReleaseDate;
import com.example.demo.dto.VinylRequest;
import com.example.demo.mapper.VinylListMapperArtistAlbumReleaseDate;
import com.example.demo.mapper.VinylMapper;
import com.example.demo.model.Vinyl;
import com.example.demo.dto.SearchVinylsResponse;
import com.example.demo.service.VinylService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@Validated
public class VinylController {
    @Autowired
    private VinylService vinylService;

    @Autowired
    private VinylMapper vinylMapper;

    @GetMapping("/vinyls")
    public ResponseEntity<List<Vinyl>> getAllVinyls() {
        return ResponseEntity.ok(vinylService.getAll());
    }

    @GetMapping("/vinyls/artist")
    public ResponseEntity<List<VinylDtoArtistAlbumReleaseDate>> getAllVinylsWithArtistAlbumReleaseDateFilteredSortedReversedByReleaseDate(@RequestParam(name = "artist") String artist) {

        List<VinylDtoArtistAlbumReleaseDate> vinylDtosArtistAlbumReleaseDate = VinylListMapperArtistAlbumReleaseDate.INSTANCE.convert(vinylService.getVinylsWithArtistFilteredSorted(vinylService.getAll(), artist));

        return ResponseEntity.ok(vinylDtosArtistAlbumReleaseDate);
    }

    @GetMapping("/vinyls/search")
    public ResponseEntity<SearchVinylsResponse> getAllVinylsByAlbum(
            @RequestParam(value = "page", defaultValue = "1")
            @Min(value = 1, message = "Page should not be less than 1")
            @Max(value = 2147483646, message = "Page should not be greater than 2147483646")
            int page,

            @RequestParam(value = "elementsPerPage", defaultValue = "10")
            @Min(value = 1, message = "Elements per page should not be less than 1")
            @Max(value = 30, message = "Elements per page should not be greater than 30")
            int elementsPerPage,

            @RequestParam(value = "searchLine")
            @NotNull(message = "Search line must be not null")
            @NotBlank(message = "Search line must be not blank")
            String searchLine,

            @RequestParam(value = "sortField", defaultValue = "id") String sortField,
            @RequestParam(value = "sortDirection", defaultValue = "asc") String sortDirection) {

        SearchVinylsResponse searchVinylsByAlbumResponse = vinylService.searchVinylsByAlbum(page, elementsPerPage, searchLine, sortField, sortDirection);
        return ResponseEntity.ok(searchVinylsByAlbumResponse);
    }

    @GetMapping("/vinyls/{id}")
    public Vinyl getVinylById(@PathVariable int id) {
        return ResponseEntity.ok(vinylService.getById(id)).getBody();
    }

    @GetMapping("/vinylsdto/{id}")
    public ResponseEntity<VinylDto> getVinylDtoById(@PathVariable int id) {
        VinylDto vinylDto = vinylMapper.modelToDto(vinylService.getById(id));

        return ResponseEntity.ok(vinylDto);
    }

    @PostMapping("/vinyls")
    public ResponseEntity<Vinyl> createVinyl(@RequestBody @Valid VinylRequest vinylRequest) {
        return ResponseEntity.ok(vinylService.save(vinylRequest));
    }

    @PutMapping("/vinyls/{id}/updatedVinyl")
    public Vinyl updateVinyl(@PathVariable int id, @RequestBody Vinyl vinyl) {
        return vinylService.update(id, vinyl);
    }

    @DeleteMapping("/vinyls/{id}")
    public ResponseEntity<String> deleteVinyl(@PathVariable("id") int id) {
       return ResponseEntity.ok(vinylService.deleteById(id));
    }
}

