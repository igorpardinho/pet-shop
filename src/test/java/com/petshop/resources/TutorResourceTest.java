package com.petshop.resources;

import com.petshop.domain.Pet;
import com.petshop.domain.Tutor;
import com.petshop.dto.TutorDTO;
import com.petshop.services.exceptions.ObjectNotFoundException;
import com.petshop.services.impl.TutorServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.*;

@SpringBootTest
class TutorResourceTest {

    public static final UUID ID = UUID.fromString("8585a29f-023c-43e1-b3b4-563e5fd4d100");
    public static final String NAME = "Theo";
    public static final String ADDRESS = "Rua teste 123";
    public static final Set<Pet> PETS = new HashSet<>();


    @InjectMocks
    private TutorResource tutorResource;
    @Mock
    private TutorServiceImpl tutorService;
    @Mock
    private ModelMapper modelMapper;

    private Tutor tutor;
    private TutorDTO tutorDTO;


    @BeforeEach
    void setUp() {
        openMocks(TutorResource.class);
        startdb();
    }

    @Test
    void whenFindByIdThenReturnSuccess() {
        when(tutorService.findById(any(UUID.class))).thenReturn(tutor);
        when(modelMapper.map(any(), any())).thenReturn(tutorDTO);

        ResponseEntity<TutorDTO> response = tutorResource.findById(ID);

        assertNotNull(response);
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(TutorDTO.class, Objects.requireNonNull(response.getBody()).getClass());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ID, response.getBody().getId());
        assertEquals(NAME, response.getBody().getName());
        assertEquals(ADDRESS, response.getBody().getAddress());
        assertEquals(PETS, response.getBody().getPets());
    }

    @Test
    void whenFindByIdThenReturnObjectNotFound() {
        when(tutorService.findById(any(UUID.class))).thenThrow(new ObjectNotFoundException("Objeto não encontrado"));
        when(modelMapper.map(any(), any())).thenReturn(tutorDTO);

        try {
            tutorResource.findById(ID);
        } catch (Exception ex) {
            assertNotNull(ex);
            assertEquals(ObjectNotFoundException.class, ex.getClass());
            assertEquals("Objeto não encontrado", ex.getMessage());
        }
    }

    @Test
    void whenFindAllThenReturnSuccess() {
        when(tutorService.findAll()).thenReturn(List.of(tutor));
        when(modelMapper.map(any(), any())).thenReturn(tutorDTO);

        ResponseEntity<List<TutorDTO>> response = tutorResource.findAll();

        assertNotNull(response);
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(TutorDTO.class, Objects.requireNonNull(response.getBody()).getFirst().getClass());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ID, response.getBody().getFirst().getId());
        assertEquals(NAME, response.getBody().getFirst().getName());
        assertEquals(ADDRESS, response.getBody().getFirst().getAddress());
        assertEquals(PETS, response.getBody().getFirst().getPets());
    }

    @Test
    void whenSaveThenReturnSuccess() {
        when(tutorService.save(any())).thenReturn(tutor);
        when(modelMapper.map(any(), any())).thenReturn(Tutor.class);

        ResponseEntity<Tutor> response = tutorResource.save(tutorDTO);

        assertNotNull(response);
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(Tutor.class, Objects.requireNonNull(response.getBody()).getClass());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(ID, response.getBody().getId());
        assertEquals(NAME, response.getBody().getName());
        assertEquals(ADDRESS, response.getBody().getAddress());
        assertEquals(PETS, response.getBody().getPets());
    }

    @Test
    void whenUpdateThenReturnSuccess() {
        when(tutorService.update(any())).thenReturn(tutor);
        when(modelMapper.map(any(), any())).thenReturn(Tutor.class);

        ResponseEntity<Tutor> response = tutorResource.update(ID, tutorDTO);

        assertNotNull(response);
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(Tutor.class, Objects.requireNonNull(response.getBody()).getClass());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ID, response.getBody().getId());
        assertEquals(NAME, response.getBody().getName());
        assertEquals(ADDRESS, response.getBody().getAddress());
        assertEquals(PETS, response.getBody().getPets());

    }

    @Test
    void whenDeleteThenReturnNoContent() {
        doNothing().when(tutorService).delete(any(UUID.class));
        tutorService.delete(ID);
        verify(tutorService, times(1)).delete(any(UUID.class));

        ResponseEntity<Tutor> response = tutorResource.delete(ID);

        assertNotNull(response);
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }


    public void startdb() {
        PETS.add(new Pet(UUID.fromString("d7b4f0bb-3910-40ba-8600-a3632499d5d2"),
                "Luna", "female", 16));
        tutor = new Tutor(ID, NAME, ADDRESS, PETS);
        tutorDTO = new TutorDTO(ID, NAME, ADDRESS, PETS);

    }
}