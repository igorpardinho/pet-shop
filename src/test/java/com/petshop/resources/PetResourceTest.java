package com.petshop.resources;

import com.petshop.domain.Pet;
import com.petshop.dto.PetDTO;
import com.petshop.repositories.PetRepository;
import com.petshop.services.exceptions.ObjectNotFoundException;
import com.petshop.services.impl.PetServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
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
    @Mock
    private PetRepository petRepository;

    private PetDTO petDTO;
    private Pet pet;
    Optional<Pet> optionalPet;


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
        assertEquals(HttpStatusCode.valueOf(200), response.getStatusCode());
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
        assertEquals(HttpStatusCode.valueOf(200), response.getStatusCode());

    }

    @Test
    void save() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    public void startdb() {
        pet = new Pet(ID, NAME, GENDER, WEIGHT);
        petDTO = new PetDTO(ID, NAME, GENDER, WEIGHT);
        optionalPet = Optional.of(new Pet(ID, NAME, GENDER, WEIGHT));
    }
}