package com.example.demo.service.impl;

import com.example.demo.dto.VinylDto;
import com.example.demo.exception.NonPositiveNumException;
import com.example.demo.exception.NotNullValueException;
import com.example.demo.mapper.VinylMapper;
import com.example.demo.model.Vinyl;
import com.example.demo.repository.SearchVinylsResponse;
import com.example.demo.repository.VinylRepository;
import com.example.demo.service.VinylService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyInt;

@SpringBootTest
class VinylServiceImplTest {
    Vinyl vinyl;
    Vinyl exceptedVinyl;
    Vinyl updatedVinyl;

    VinylDto vinylDto1;
    VinylDto vinylDto2;
    VinylDto vinylDto3;

    @Mock
    VinylRepository vinylRepository;

    @Mock
    VinylMapper vinylMapper;

    @InjectMocks
    private VinylService vinylService = new VinylServiceImpl();

    @BeforeEach
    public void init() {
        Vinyl vinyl = new Vinyl(1, "AC/DC", "Back in Black", 1980, 30);

        Vinyl exceptedVinyl = new Vinyl(1, "AC/DC", "Back in Black", 1980, 30);

        Vinyl updatedVinyl = new Vinyl(1, "AC/DC Updated", "Album Updated", 1980, 30);
    }

    @Test
    void getAll_getAllVinyls_thenSuccess() {
        Mockito.when(vinylRepository.getAll()).thenReturn(Arrays.asList(vinyl));

        List<Vinyl> resultAllVinyls = vinylRepository.getAll();

        Assertions.assertEquals(1, resultAllVinyls.size());
    }

    @Test
    void save_getVinyl_thenSuccess() {
        Mockito.when(vinylRepository.save(vinyl)).thenReturn(vinyl);

        Vinyl resultVinyl = vinylRepository.save(vinyl);

        Assertions.assertEquals(exceptedVinyl, resultVinyl);
    }

    @Test
    void save_getVinylWithArtistNull_thenNotNullValueException() {
        Vinyl vinylWithArtistNull = new Vinyl(1, null, "Back in Black", 1980, 30);

        Assertions.assertThrows(NotNullValueException.class,
                () -> vinylService.save(vinylWithArtistNull));

        Mockito.verifyNoInteractions(vinylRepository);
    }

    @Test
    void save_getVinylWithAlbumNull_thenNotNullValueException() {
        Vinyl vinylWithAlbumNull = new Vinyl(1, "AC/DC", null, 1980, 30);

        Assertions.assertThrows(NotNullValueException.class,
                () -> vinylService.save(vinylWithAlbumNull));
        Mockito.verifyNoInteractions(vinylRepository);
    }

    @Test
    void save_getVinylWithIdNotPositive_thenNonPositiveNumException() {
        Vinyl vinylWithIdNotPositive = new Vinyl(0, "AC/DC", "Back in Black", 1980, 30);

        Assertions.assertThrows(NonPositiveNumException.class,
                () -> vinylService.save(vinylWithIdNotPositive));
        Mockito.verifyNoInteractions(vinylRepository);
    }

    @Test
    void save_getVinylWithReleaseDateNotPositive_thenNonPositiveNumException() {
        Vinyl vinylWithReleaseDateNotPositive = new Vinyl(1, "AC/DC", "Back in Black", -100, 30);

        Assertions.assertThrows(NonPositiveNumException.class,
                () -> vinylService.save(vinylWithReleaseDateNotPositive));
        Mockito.verifyNoInteractions(vinylRepository);
    }

    @Test
    void save_getVinylWithListPriceNotPositive_thenNonPositiveNumException() {
        Vinyl vinylWithListPriceNotPositive = new Vinyl(1, "AC/DC", "Back in Black", 1980, 0);

        Assertions.assertThrows(NonPositiveNumException.class,
                () -> vinylService.save(vinylWithListPriceNotPositive));
        Mockito.verifyNoInteractions(vinylRepository);
    }

    @Test
    void getById_getVinylById_thenSuccess() {
        Mockito.when(vinylRepository.getById(anyInt())).thenReturn(vinyl);

        Vinyl resultVinyl = vinylService.getById(1);

        Assertions.assertEquals(exceptedVinyl, resultVinyl);
    }

    @Test
    void update_getVinyl_thenReturnUpdatedVinyl() {
        Vinyl vinyl = new Vinyl(1, "AC/DC", "Back in Black", 1980, 30);

        Mockito.when(vinylRepository.save(vinyl)).thenReturn(vinyl);

        vinyl.setId(1);
        vinyl.setArtist("AC/DC Updated");
        vinyl.setAlbum("Album Updated");
        vinyl.setReleaseDate(1980);
        vinyl.setListPrice(30);

        Vinyl resultVinyl = vinylService.update(1, vinyl);

        Assertions.assertEquals(updatedVinyl, resultVinyl);
    }

    @Test
    void update_getVinylWithArtistNull_thenNotNullValueException() {
        Vinyl vinyl = new Vinyl(1, "AC/DC", "Back in Black", 1980, 30);

        Mockito.when(vinylRepository.save(vinyl)).thenReturn(vinyl);

        vinyl.setId(1);
        vinyl.setArtist(null);
        vinyl.setAlbum("Album Updated");
        vinyl.setReleaseDate(1980);
        vinyl.setListPrice(30);

        Assertions.assertThrows(NotNullValueException.class,
                () -> vinylService.update(1, vinyl));

        Mockito.verifyNoInteractions(vinylRepository);
    }

    @Test
    void update_getVinylWithAlbumNull_thenNotNullValueException() {
        Vinyl vinyl = new Vinyl(1, "AC/DC", "Back in Black", 1980, 30);

        Mockito.when(vinylRepository.save(vinyl)).thenReturn(vinyl);

        vinyl.setId(1);
        vinyl.setArtist("AC/DC");
        vinyl.setAlbum(null);
        vinyl.setReleaseDate(1980);
        vinyl.setListPrice(30);

        Assertions.assertThrows(NotNullValueException.class,
                () -> vinylService.update(1, vinyl));

        Mockito.verifyNoInteractions(vinylRepository);
    }

    @Test
    void update_getVinylWithIdNotPositive_thenNonPositiveNumException() {
        Vinyl vinyl = new Vinyl(1, "AC/DC", "Back in Black", 1980, 30);

        Mockito.when(vinylRepository.save(vinyl)).thenReturn(vinyl);

        vinyl.setId(-1);
        vinyl.setArtist("AC/DC");
        vinyl.setAlbum("Back in Black");
        vinyl.setReleaseDate(1980);
        vinyl.setListPrice(30);

        Assertions.assertThrows(NonPositiveNumException.class,
                () -> vinylService.update(-1, vinyl));

        Mockito.verifyNoInteractions(vinylRepository);
    }

    @Test
    void update_getVinylWithReleaseDateNotPositive_thenNonPositiveNumException() {
        Vinyl vinyl = new Vinyl(1, "AC/DC", "Back in Black", 1980, 30);

        Mockito.when(vinylRepository.save(vinyl)).thenReturn(vinyl);

        vinyl.setId(1);
        vinyl.setArtist("AC/DC");
        vinyl.setAlbum("Back in Black");
        vinyl.setReleaseDate(-100);
        vinyl.setListPrice(30);

        Assertions.assertThrows(NonPositiveNumException.class,
                () -> vinylService.update(1, vinyl));

        Mockito.verifyNoInteractions(vinylRepository);
    }

    @Test
    void update_getVinylWithListPriceNotPositive_thenNonPositiveNumException() {
        Vinyl vinyl = new Vinyl(1, "AC/DC", "Back in Black", 1980, 30);

        Mockito.when(vinylRepository.save(vinyl)).thenReturn(vinyl);

        vinyl.setId(1);
        vinyl.setArtist("AC/DC");
        vinyl.setAlbum("Back in Black");
        vinyl.setReleaseDate(1980);
        vinyl.setListPrice(0);

        Assertions.assertThrows(NonPositiveNumException.class,
                () -> vinylService.update(1, vinyl));

        Mockito.verifyNoInteractions(vinylRepository);
    }

    @Test
    void deleteById_getVinylById_thenSuccess() {
        Mockito.when(vinylRepository.getById(anyInt())).thenReturn(vinyl);

        Assertions.assertAll(() -> vinylService.deleteById(1));
    }

    @Test
    void getVinylsWithArtistFilteredSorted_getVinylsFilteredByArtistNameAndSortedDesc_thenSuccess() {
        Vinyl vinyl1 = new Vinyl(1, "AC/DC", "Back in Black", 1980, 30);
        Vinyl vinyl2 = new Vinyl(2, "Tame Impala", "Currents", 2020, 15);
        Vinyl vinyl3 = new Vinyl(3, "Arctic Monkeys", "Favourite Worst Nightmare", 2011,15);
        Vinyl vinyl4 = new Vinyl(4, "Arctic Monkeys", "AM", 2013,17);
        List<Vinyl> vinylList = Arrays.asList(vinyl1, vinyl2, vinyl3, vinyl4);

        Mockito.when(vinylRepository.getVinylsWithArtistFilteredSorted(vinylList, "Arctic Monkeys")).thenCallRealMethod();

        List<Vinyl> resultList = vinylRepository.getVinylsWithArtistFilteredSorted(vinylList, "Arctic Monkeys");

        Assertions.assertEquals(2, resultList.size());
        Assertions.assertEquals(Arrays.asList(vinyl4, vinyl3), resultList);
        Assertions.assertEquals(2013, resultList.get(0).getReleaseDate());
        Assertions.assertEquals(2011, resultList.get(1).getReleaseDate());
    }

    @Test
    @Disabled
    void searchVinylsByAlbum_getVinylsFilteredByAlbumShowedWithPagination_thenSuccess() {
        Vinyl vinyl1 = new Vinyl(1, "AC/DC", "Back in Black", 1980, 30);
        Vinyl vinyl2 = new Vinyl(2, "Tame Impala", "Currents", 2020, 15);
        Vinyl vinyl3 = new Vinyl(3, "Amy Winehouse", "Back to Black", 2010, 23);

        VinylDto vinylDto1 = new VinylDto(1, "AC/DC", "Back in Black", 1980);
        VinylDto vinylDto2 = new VinylDto(2, "Tame Impala", "Currents", 2020);
        VinylDto vinylDto3 = new VinylDto(3, "Amy Winehouse", "Back to Black", 2010);

        List<Vinyl> vinylList = Arrays.asList(vinyl1, vinyl2, vinyl3);

        Mockito.when(vinylRepository.getAll().stream()).thenReturn(vinylList.stream());

        Mockito.when(vinylMapper.modelToDto(vinyl1)).thenReturn(vinylDto1);
        Mockito.when(vinylMapper.modelToDto(vinyl2)).thenReturn(vinylDto2);
        Mockito.when(vinylMapper.modelToDto(vinyl3)).thenReturn(vinylDto3);

        /*Mockito.when(vinylRepository.searchVinylsByAlbum(1, 2, "Black")).thenCallRealMethod();
*/
        SearchVinylsResponse resultResponse = vinylService.searchVinylsByAlbum(1, 2, "Black");

        Assertions.assertEquals(2, resultResponse.getTotalElements());
        Assertions.assertEquals(2, resultResponse.getElementsPerPage());
        Assertions.assertEquals("Black", resultResponse.getSearchLine());
        Assertions.assertEquals(1, resultResponse.getPage());
        Assertions.assertEquals(1, resultResponse.getTotalPages());
        Assertions.assertEquals(Arrays.asList(vinylDto1, vinylDto3), resultResponse.getElements());
    }
}
