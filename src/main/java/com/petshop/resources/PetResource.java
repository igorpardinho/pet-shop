package com.petshop.resources;


import com.petshop.domain.Pet;
import com.petshop.dto.PetDTO;
import com.petshop.services.impl.PetServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/pets")
public class PetResource {

    private final PetServiceImpl petService;
    private final ModelMapper modelMapper;

    public PetResource(PetServiceImpl petService, ModelMapper modelMapper) {
        this.petService = petService;
        this.modelMapper = modelMapper;

    }


    @GetMapping("{id}")
    public ResponseEntity<PetDTO> findById(@PathVariable(name = "id") UUID id) {

        return ResponseEntity.ok().body(modelMapper.map(petService.findById(id), PetDTO.class));
    }

    @GetMapping
    public ResponseEntity<List<Pet>> findAll() {
        return ResponseEntity.ok().body(petService.findAll());
    }

    @PostMapping
    public ResponseEntity<PetDTO> save(@RequestBody Pet pet) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(modelMapper.map(petService.save(pet), PetDTO.class));
    }

}
