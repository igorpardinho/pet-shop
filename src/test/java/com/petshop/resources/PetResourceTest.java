package com.petshop.resources;

import com.petshop.domain.Pet;
import com.petshop.dto.PetDTO;
import com.petshop.services.exceptions.ObjectNotFoundException;
import com.petshop.services.impl.PetServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.*;

@SpringBootTest
class PetResourceTest {

    public static final UUID ID = UUID.fromString("8585a29f-023c-43e1-b3b4-563e5fd4d100");
    public static final String NAME = "Theo";
    public static final String GENDER = "Male";
    public static final int WEIGHT = 24;

    @InjectMocks
    private PetResource petResource;
    @Mock
    private ModelMapper modelMapper;
    @Mock
    private PetServiceImpl petService;

    private PetDTO petDTO;
    private Pet pet;



    @BeforeEach
    void setUp() {
        openMocks(PetResourceTest.class);
        startdb();
    }

    @Test
    void whenFindByIdThenReturnSuccess() {
        when(petService.findById(any(UUID.class))).thenReturn(pet);
        when(modelMapper.map(any(), any())).thenReturn(petDTO);

        ResponseEntity<PetDTO> response = petResource.findById(ID);

        assertNotNull(response);
        assertEquals(PetDTO.class, Objects.requireNonNull(response.getBody()).getClass());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void whenFindByIdThenReturnObjectNotFound() {
        when(petService.findById(any(UUID.class))).thenThrow(new ObjectNotFoundException("Objeto não encontrado"));
        when(modelMapper.map(any(), any())).thenReturn(petDTO);
        try {
            petResource.findById(ID);
        } catch (Exception ex) {
            assertNotNull(ex);
            assertEquals(ObjectNotFoundException.class, ex.getClass());
            assertEquals("Objeto não encontrado", ex.getMessage());
        }
    }

    @Test
    void whenFindAllThenReturnSuccess() {
        when(petService.findAll()).thenReturn(List.of(pet));
        when(modelMapper.map(any(), any())).thenReturn(petDTO);

        ResponseEntity<List<PetDTO>> response = petResource.findAll();

        assertNotNull(response);
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(PetDTO.class, Objects.requireNonNull(response.getBody()).getFirst().getClass());
        assertEquals(HttpStatus.OK, response.getStatusCode());

    }

    @Test
    void whenSaveThenReturnSuccess() {
        when(petService.save(any())).thenReturn(pet);

        ResponseEntity<Pet> response = petResource.save(petDTO);

        assertNotNull(response);
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(Pet.class, Objects.requireNonNull(response.getBody()).getClass());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    void whenUpdateThenReturnSuccess() {
        when(petService.update(any())).thenReturn(pet);


        ResponseEntity<Pet> response = petResource.update(ID, petDTO);

        assertNotNull(response);
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(Pet.class, Objects.requireNonNull(response.getBody()).getClass());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void whenDeleteThenReturnNoContent() {
        doNothing().when(petService).delete(any(UUID.class));

        ResponseEntity<PetDTO> response = petResource.delete(ID);

        verify(petService, times(1)).delete(any(UUID.class));

        assertNotNull(response);
        assertEquals(HttpStatus.NO_CONTENT,response.getStatusCode());
    }

    public void startdb() {
        pet = new Pet(ID, NAME, GENDER, WEIGHT);
        petDTO = new PetDTO(ID, NAME, GENDER, WEIGHT);

    }
}