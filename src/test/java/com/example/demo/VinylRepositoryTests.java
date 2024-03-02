package com.example.demo;

import com.example.demo.model.Vinyl;
import com.example.demo.repository.VinylRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
public class VinylRepositoryTests {

    @Autowired
    VinylRepository vinylRepository;

    @Test
    public void testGetAll() {
        List<Vinyl> vinylList = new ArrayList<>();

        Vinyl vinyl1 = new Vinyl();
        vinyl1.setId(1);
        vinyl1.setArtist("AC/DC");
        vinyl1.setAlbum("Back in Black");
        vinyl1.setReleaseDate(1980);
        vinyl1.setListPrice(30);
        vinylList.add(vinyl1);

        Vinyl vinyl2 = new Vinyl();
        vinyl2.setId(2);
        vinyl2.setArtist("Asaf Avidan");
        vinyl2.setAlbum("Gold Shadow");
        vinyl2.setReleaseDate(2015);
        vinyl2.setListPrice(23);
        vinylList.add(vinyl2);

        VinylRepository mockRepository = mock(VinylRepository.class);

        when(mockRepository.getAll()).thenReturn(vinylList);

        List<Vinyl> returnedVinylList = mockRepository.getAll();

        assertNotNull(returnedVinylList);
        assertEquals(2, returnedVinylList.size());
        assertEquals(vinylList, returnedVinylList);
    }

    @Test
    public void testSave() {
        Vinyl vinyl = new Vinyl();
        vinyl.setId(1);
        vinyl.setArtist("AC/DC");
        vinyl.setAlbum("Back in Black");
        vinyl.setReleaseDate(1980);
        vinyl.setListPrice(30);

        Vinyl savedVinyl = vinylRepository.save(vinyl);

        assertNotNull(savedVinyl);
        assertEquals(1, savedVinyl.getId());
        assertEquals(vinyl.getArtist(), savedVinyl.getArtist());
        assertEquals(vinyl.getAlbum(), savedVinyl.getAlbum());
        assertEquals(vinyl.getReleaseDate(), savedVinyl.getReleaseDate());
        assertEquals(vinyl.getListPrice(), savedVinyl.getListPrice());
    }
}
