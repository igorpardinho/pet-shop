package com.petshop.services.impl;

import com.petshop.domain.Pet;
import com.petshop.domain.Tutor;
import com.petshop.dto.TutorDTO;
import com.petshop.repositories.TutorRepository;
import com.petshop.services.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.*;

@SpringBootTest
class TutorServiceImplTest {
    public static final UUID ID = UUID.fromString("8585a29f-023c-43e1-b3b4-563e5fd4d100");
    public static final String NAME = "Igor";
    public static final String ADDRESS = "Rua dos bobos";
    public static final Set<Pet> PETS = new HashSet<>();
    @InjectMocks
    private TutorServiceImpl tutorService;
    @Mock
    private TutorRepository tutorRepository;
    @Mock
    private ModelMapper modelMapper;

    private Tutor tutor;
    private TutorDTO tutorDTO;
    private Optional<Tutor> optionalTutor;

    @BeforeEach
    void setUp() {
        openMocks(TutorServiceImplTest.class);
        startTutor();
    }

    @Test
    void whenFindByIdReturnTutor() {
        when(tutorRepository.findById(any(UUID.class))).thenReturn(optionalTutor);

        Tutor response = tutorService.findById(ID);

        assertNotNull(response);
        assertEquals(Tutor.class,response.getClass());
        assertEquals(ID,response.getId());
        assertEquals(NAME,response.getName());
        assertEquals(ADDRESS,response.getAddress());
        assertEquals(PETS,response.getPets());
    }
    @Test
    void whenFindByIdReturnObjectNotFoundException(){
        when(tutorRepository.findById(any(UUID.class))).thenThrow(new ObjectNotFoundException("Objeto não encontrado"));

        try {
            tutorService.findById(ID);
        }catch (Exception ex){
            assertNotNull(ex);
            assertEquals(ObjectNotFoundException.class,ex.getClass());
            assertEquals("Objeto não encontrado",ex.getMessage());
        }
    }

    @Test
    void whenFindAllReturnSuccess() {
        when(tutorRepository.findAll()).thenReturn(List.of(tutor));

        List<Tutor> response = tutorService.findAll();

        assertNotNull(response);
        assertEquals(Tutor.class,response.getFirst().getClass());
        assertEquals(ID,response.getFirst().getId());
        assertEquals(NAME,response.getFirst().getName());
        assertEquals(ADDRESS,response.getFirst().getAddress());
        assertEquals(PETS,response.getFirst().getPets());

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

    private void startTutor() {
        PETS.add(new Pet(UUID.fromString("d7b4f0bb-3910-40ba-8600-a3632499d5d2"),
                "Luna", "female", 16));
        tutor = new Tutor(ID, NAME, ADDRESS, PETS);
        tutorDTO = new TutorDTO(ID, NAME, ADDRESS, PETS);
        optionalTutor = Optional.of(new Tutor(ID, NAME, ADDRESS, PETS));
    }
}