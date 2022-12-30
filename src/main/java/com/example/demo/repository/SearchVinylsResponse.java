package com.example.demo.repository;

import com.example.demo.dto.VinylDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class SearchVinylsResponse {
    private int totalElements;
    private int elementsPerPage;
    private String searchLine;
    private int page;
    private int totalPages;
    private List<VinylDto> elements;
}
