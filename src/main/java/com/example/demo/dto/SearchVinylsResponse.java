package com.example.demo.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class SearchVinylsResponse {
    private int totalElements;

/*    @Min(value = 1, message = "Elements per page should not be less than 1")
    @Max(value = 30, message = "Elements per page should not be greater than 30")*/
    private int elementsPerPage;

/*    @NotNull(message = "Search line must be not null")
    @NotBlank(message = "Search line must be not blank")*/
    private String searchLine;

    /*@Min(value = 1, message = "Page should not be less than 1")
    @Max(value = 2147483646, message = "Page should not be greater than 2147483646")*/
    private int page;
    private int totalPages;
    private String sortField;
    private String sortDirection;
    private List<VinylDto> elements;
}
