package com.example.demo.service.impl;

import com.example.demo.exception.NonPositiveNumException;
import com.example.demo.exception.NotNullValueException;
import com.example.demo.model.Vinyl;
import com.example.demo.repository.VinylRepository;
import com.example.demo.service.VinylService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
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

    @Mock
    VinylRepository vinylRepository;

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
}