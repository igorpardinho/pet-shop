package com.petshop.resources;


import com.petshop.domain.Pet;
import com.petshop.dto.PetDTO;
import com.petshop.services.impl.PetServiceImpl;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/pets")
public class PetResource {

    public static final String ID = "/{id}";
    private final PetServiceImpl petService;
    private final ModelMapper modelMapper;

    public PetResource(PetServiceImpl petService, ModelMapper modelMapper) {
        this.petService = petService;
        this.modelMapper = modelMapper;

    }


    @GetMapping(ID)
    public ResponseEntity<PetDTO> findById(@PathVariable(value = "id") UUID id) {

        return ResponseEntity.ok().body(modelMapper.map(petService.findById(id), PetDTO.class));
    }

    @GetMapping
    public ResponseEntity<List<PetDTO>> findAll() {
        return ResponseEntity.ok().body(petService.findAll().stream()
                .map(p -> modelMapper.map(p, PetDTO.class)).toList());
    }

    @PostMapping
    public ResponseEntity<Pet> create(@RequestBody @Valid PetDTO petDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(petService.save(modelMapper.map(petDTO, Pet.class)));
    }

    @PutMapping(ID)
    public ResponseEntity<Pet> update(@PathVariable(value = "id") UUID id, @RequestBody @Valid PetDTO petDTO) {
        petDTO.setId(id);
        Pet pet = petService.update(modelMapper.map(petDTO, Pet.class));
        return ResponseEntity.status(HttpStatus.OK).body(pet);

    }

    @DeleteMapping(ID)
    public ResponseEntity<PetDTO> delete(@PathVariable(value = "id") UUID id) {
        petService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
