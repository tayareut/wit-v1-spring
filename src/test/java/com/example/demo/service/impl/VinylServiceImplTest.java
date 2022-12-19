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
    Vinyl vinyl1;
    Vinyl exceptedVinyl;
    Vinyl updatedVinyl1;

    @Mock
    VinylRepository vinylRepository;

    @InjectMocks
    private VinylService vinylService = new VinylServiceImpl();

    @BeforeEach
    public void init() {
        Vinyl vinyl1 = new Vinyl();

        vinyl1.setId(1);
        vinyl1.setArtist("AC/DC");
        vinyl1.setAlbum("Back in Black");
        vinyl1.setReleaseDate(1980);
        vinyl1.setListPrice(30);

        Vinyl exceptedVinyl = new Vinyl();
        exceptedVinyl.setId(1);
        exceptedVinyl.setArtist("AC/DC");
        exceptedVinyl.setAlbum("Back in Black");
        exceptedVinyl.setReleaseDate(1980);
        exceptedVinyl.setListPrice(30);

        Vinyl updatedVinyl1 = new Vinyl();
        updatedVinyl1.setId(1);
        updatedVinyl1.setArtist("AC/DC Updated");
        updatedVinyl1.setAlbum("Album Updated");
        updatedVinyl1.setReleaseDate(1980);
        updatedVinyl1.setListPrice(30);
    }

    @Test
    void getAll_getAllVinyls_thenSuccess() {
        Mockito.when(vinylRepository.getAll()).thenReturn(Arrays.asList(vinyl1));

        List<Vinyl> resultAllVinyls = vinylRepository.getAll();

        Assertions.assertEquals(1, resultAllVinyls.size());
    }

    @Test
    void save_getVinyl_thenSuccess() {
        Mockito.when(vinylRepository.save(vinyl1)).thenReturn(vinyl1);

        Vinyl resultVinyl = vinylRepository.save(vinyl1);

        Assertions.assertEquals(exceptedVinyl, resultVinyl);
    }

    @Test
    void save_getVinylWithArtistNull_thenNotNullValueException() {
        Vinyl vinylWithArtistNull = new Vinyl();

        vinylWithArtistNull.setId(1);
        vinylWithArtistNull.setArtist(null);
        vinylWithArtistNull.setAlbum("Back in Black");
        vinylWithArtistNull.setReleaseDate(1980);
        vinylWithArtistNull.setListPrice(30);

        Assertions.assertThrows(NotNullValueException.class,
                () -> vinylService.save(vinylWithArtistNull));

        Mockito.verifyNoInteractions(vinylRepository);
    }

    @Test
    void save_getVinylWithAlbumNull_thenNotNullValueException() {
        Vinyl vinylWithAlbumNull = new Vinyl();

        vinylWithAlbumNull.setId(1);
        vinylWithAlbumNull.setArtist("AC/DC");
        vinylWithAlbumNull.setReleaseDate(1980);
        vinylWithAlbumNull.setListPrice(30);

        Assertions.assertThrows(NotNullValueException.class,
                () -> vinylService.save(vinylWithAlbumNull));
        Mockito.verifyNoInteractions(vinylRepository);
    }

    @Test
    void save_getVinylWithIdNotPositive_thenNonPositiveNumException() {
        Vinyl vinylWithIdNotPositive = new Vinyl();

        vinylWithIdNotPositive.setId(0);
        vinylWithIdNotPositive.setArtist("AC/DC");
        vinylWithIdNotPositive.setAlbum("Back in Black");
        vinylWithIdNotPositive.setReleaseDate(1980);
        vinylWithIdNotPositive.setListPrice(30);

        Assertions.assertThrows(NonPositiveNumException.class,
                () -> vinylService.save(vinylWithIdNotPositive));
        Mockito.verifyNoInteractions(vinylRepository);
    }

    @Test
    void save_getVinylWithReleaseDateNotPositive_thenNonPositiveNumException() {
        Vinyl vinylWithReleaseDateNotPositive = new Vinyl();

        vinylWithReleaseDateNotPositive.setId(1);
        vinylWithReleaseDateNotPositive.setArtist("AC/DC");
        vinylWithReleaseDateNotPositive.setAlbum("Back in Black");
        vinylWithReleaseDateNotPositive.setReleaseDate(-100);
        vinylWithReleaseDateNotPositive.setListPrice(30);

        Assertions.assertThrows(NonPositiveNumException.class,
                () -> vinylService.save(vinylWithReleaseDateNotPositive));
        Mockito.verifyNoInteractions(vinylRepository);
    }

    @Test
    void save_getVinylWithListPriceNotPositive_thenNonPositiveNumException() {
        Vinyl vinylWithListPriceNotPositive = new Vinyl();

        vinylWithListPriceNotPositive.setId(1);
        vinylWithListPriceNotPositive.setArtist("AC/DC");
        vinylWithListPriceNotPositive.setAlbum("Back in Black");
        vinylWithListPriceNotPositive.setReleaseDate(1980);
        vinylWithListPriceNotPositive.setListPrice(0);

        Assertions.assertThrows(NonPositiveNumException.class,
                () -> vinylService.save(vinylWithListPriceNotPositive));
        Mockito.verifyNoInteractions(vinylRepository);
    }

    @Test
    void getById_getVinylById_thenSuccess() {
        Mockito.when(vinylRepository.getById(anyInt())).thenReturn(vinyl1);

        Vinyl resultVinyl = vinylService.getById(1);

        Assertions.assertEquals(exceptedVinyl, resultVinyl);
    }

    @Test
    void update_getVinyl_thenReturnUpdatedVinyl() {
        Vinyl vinyl1 = new Vinyl();

        vinyl1.setId(1);
        vinyl1.setArtist("AC/DC");
        vinyl1.setAlbum("Back in Black");
        vinyl1.setReleaseDate(1980);
        vinyl1.setListPrice(30);

        Mockito.when(vinylRepository.save(vinyl1)).thenReturn(vinyl1);

        vinyl1.setId(1);
        vinyl1.setArtist("AC/DC Updated");
        vinyl1.setAlbum("Album Updated");
        vinyl1.setReleaseDate(1980);
        vinyl1.setListPrice(30);

        Vinyl resultVinyl = vinylService.update(1, vinyl1);

        Assertions.assertEquals(updatedVinyl1, resultVinyl);
    }

    @Test
    void update_getVinylWithArtistNull_thenNotNullValueException() {
        Vinyl vinyl1 = new Vinyl();

        vinyl1.setId(1);
        vinyl1.setArtist("AC/DC");
        vinyl1.setAlbum("Back in Black");
        vinyl1.setReleaseDate(1980);
        vinyl1.setListPrice(30);

        Mockito.when(vinylRepository.save(vinyl1)).thenReturn(vinyl1);

        vinyl1.setId(1);
        vinyl1.setArtist(null);
        vinyl1.setAlbum("Album Updated");
        vinyl1.setReleaseDate(1980);
        vinyl1.setListPrice(30);

        Assertions.assertThrows(NotNullValueException.class,
                () -> vinylService.update(1, vinyl1));

        Mockito.verifyNoInteractions(vinylRepository);
    }

    @Test
    void update_getVinylWithAlbumNull_thenNotNullValueException() {
        Vinyl vinyl1 = new Vinyl();

        vinyl1.setId(1);
        vinyl1.setArtist("AC/DC");
        vinyl1.setAlbum("Back in Black");
        vinyl1.setReleaseDate(1980);
        vinyl1.setListPrice(30);

        Mockito.when(vinylRepository.save(vinyl1)).thenReturn(vinyl1);

        vinyl1.setId(1);
        vinyl1.setArtist("AC/DC");
        vinyl1.setAlbum(null);
        vinyl1.setReleaseDate(1980);
        vinyl1.setListPrice(30);

        Assertions.assertThrows(NotNullValueException.class,
                () -> vinylService.update(1, vinyl1));

        Mockito.verifyNoInteractions(vinylRepository);
    }

    @Test
    void update_getVinylWithIdNotPositive_thenNonPositiveNumException() {
        Vinyl vinyl1 = new Vinyl();

        vinyl1.setId(1);
        vinyl1.setArtist("AC/DC");
        vinyl1.setAlbum("Back in Black");
        vinyl1.setReleaseDate(1980);
        vinyl1.setListPrice(30);

        Mockito.when(vinylRepository.save(vinyl1)).thenReturn(vinyl1);

        vinyl1.setId(-1);
        vinyl1.setArtist("AC/DC");
        vinyl1.setAlbum("Back in Black");
        vinyl1.setReleaseDate(1980);
        vinyl1.setListPrice(30);

        Assertions.assertThrows(NonPositiveNumException.class,
                () -> vinylService.update(-1, vinyl1));

        Mockito.verifyNoInteractions(vinylRepository);
    }

    @Test
    void update_getVinylWithReleaseDateNotPositive_thenNonPositiveNumException() {
        Vinyl vinyl1 = new Vinyl();

        vinyl1.setId(1);
        vinyl1.setArtist("AC/DC");
        vinyl1.setAlbum("Back in Black");
        vinyl1.setReleaseDate(1980);
        vinyl1.setListPrice(30);

        Mockito.when(vinylRepository.save(vinyl1)).thenReturn(vinyl1);

        vinyl1.setId(1);
        vinyl1.setArtist("AC/DC");
        vinyl1.setAlbum("Back in Black");
        vinyl1.setReleaseDate(-100);
        vinyl1.setListPrice(30);

        Assertions.assertThrows(NonPositiveNumException.class,
                () -> vinylService.update(1, vinyl1));

        Mockito.verifyNoInteractions(vinylRepository);
    }

    @Test
    void update_getVinylWithListPriceNotPositive_thenNonPositiveNumException() {
        Vinyl vinyl1 = new Vinyl();

        vinyl1.setId(1);
        vinyl1.setArtist("AC/DC");
        vinyl1.setAlbum("Back in Black");
        vinyl1.setReleaseDate(1980);
        vinyl1.setListPrice(30);

        Mockito.when(vinylRepository.save(vinyl1)).thenReturn(vinyl1);

        vinyl1.setId(1);
        vinyl1.setArtist("AC/DC");
        vinyl1.setAlbum("Back in Black");
        vinyl1.setReleaseDate(1980);
        vinyl1.setListPrice(0);

        Assertions.assertThrows(NonPositiveNumException.class,
                () -> vinylService.update(1, vinyl1));

        Mockito.verifyNoInteractions(vinylRepository);
    }

    @Test
    void deleteById() {
        Mockito.when(vinylRepository.getById(anyInt())).thenReturn(vinyl1);

        Assertions.assertAll(() -> vinylService.deleteById(1));
    }
}