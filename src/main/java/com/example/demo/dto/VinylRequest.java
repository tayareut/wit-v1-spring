package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VinylRequest implements Serializable {

    @Max(2147483646)
    @Min(1)
    private int id;

    @NotNull (message = "Artist name must be not null")
    @NotBlank (message = "Artist name must be not blank")
    private String artist;

    @NotNull (message = "Album name must be not null")
    @NotBlank (message = "Album name must be not blank")
    private String album;

    @Min(value = 0, message = "Release date should not be less than 0")
    @Max(value = 2147483646, message = "Release date price should not be greater than 2147483646")
    private int releaseDate;

    @Min(value = 0, message = "List price should not be less than 0")
    @Max(value = 2147483646, message = "List price should not be greater than 2147483646")
    private int listPrice;
}
