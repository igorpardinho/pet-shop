package com.petshop.services.impl;

import com.petshop.domain.Pet;
import com.petshop.dto.PetDTO;
import com.petshop.repositories.PetRepository;
import com.petshop.services.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class PetServiceImplTest {

    public static final UUID ID = UUID.fromString("8585a29f-023c-43e1-b3b4-563e5fd4d100");
    public static final String NAME = "Theo";
    public static final String GENDER = "Male";
    public static final int WEIGHT = 24;
    @InjectMocks
    private PetServiceImpl petService;
    @Mock
    private PetRepository petRepository;
    @Mock
    private ModelMapper modelMapper;

    private Pet pet;

    private PetDTO petDTO;

    private Optional<Pet> optionalPet;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(PetServiceImplTest.class);
        start();
    }

    @Test
    void whenFindByIdThenReturnPet() {
        when(petRepository.findById(any(UUID.class))).thenReturn(optionalPet);

        Pet response = petService.findById(ID);

        assertNotNull(response);
        assertEquals(Pet.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NAME, response.getName());
        assertEquals(GENDER, response.getGender());
        assertEquals(WEIGHT, response.getWeight());

    }

    @Test
    void whenFindByIdThenReturnObjectNotFoundException() {
        when(petRepository.findById(any(UUID.class))).thenThrow(new ObjectNotFoundException("Objeto não encontrado"));

        try {
            petService.findById(UUID.randomUUID());
        } catch (Exception ex) {
            assertNotNull(ex);
            assertEquals(ObjectNotFoundException.class, ex.getClass());
            assertEquals("Objeto não encontrado", ex.getMessage());
        }

    }

    @Test
    void whenFindAllThenReturnSuccess() {
        when(petRepository.findAll()).thenReturn(List.of(pet));

        List<Pet> response = petService.findAll();

        assertNotNull(response);
        assertEquals(Pet.class, response.getFirst().getClass());
        assertEquals(ID, response.getFirst().getId());
        assertEquals(NAME, response.getFirst().getName());
        assertEquals(GENDER, response.getFirst().getGender());
        assertEquals(WEIGHT, response.getFirst().getWeight());
    }

    @Test
    void whenSaveThenReturnSuccess() {
        when(petRepository.save(any())).thenReturn(pet);
        when(modelMapper.map(any(),any())).thenReturn(pet);

        Pet response = petService.save(petDTO);

        assertNotNull(response);
        assertEquals(Pet.class,response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NAME, response.getName());
        assertEquals(GENDER, response.getGender());
        assertEquals(WEIGHT, response.getWeight());
    }

    @Test
    void whenDeleteThenReturnSuccess() {
        when(petRepository.findById(any(UUID.class))).thenReturn(optionalPet);
        doNothing().when(petRepository).deleteById(any(UUID.class));
        petService.delete(ID);
        verify(petRepository, times(1)).deleteById(any(UUID.class));
    }

    @Test
    void whenDeleteThenReturnObjectNotFoundException() {
        when(petRepository.findById(any(UUID.class))).thenThrow(new ObjectNotFoundException("Objeto não encontrado"));
        doNothing().when(petRepository).deleteById(any(UUID.class));

        try {
            petService.delete(ID);
        } catch (Exception ex) {
            assertNotNull(ex);
            assertEquals(ObjectNotFoundException.class, ex.getClass());
            assertEquals("Objeto não encontrado", ex.getMessage());
        }
    }

    @Test
    void whenUpdateThenReturnSuccess() {
        when(petRepository.save(any())).thenReturn(pet);
        when(modelMapper.map(any(),any())).thenReturn(pet);

        Pet response = petService.update(petDTO);

        assertNotNull(response);
        assertEquals(Pet.class,response.getClass());
        assertEquals(ID,response.getId());
        assertEquals(NAME,response.getName());
        assertEquals(GENDER,response.getGender());
        assertEquals(WEIGHT,response.getWeight());
    }

    public void start() {
        pet = new Pet(ID, NAME, GENDER, WEIGHT);
        petDTO = new PetDTO(ID, NAME, GENDER, WEIGHT);
        optionalPet = Optional.of(new Pet(ID, NAME, GENDER, WEIGHT));

    }
}