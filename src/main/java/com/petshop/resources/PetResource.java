package com.petshop.resources;

import com.petshop.domain.Pet;
import com.petshop.services.impl.PetServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/pets")
public class PetResource {

    private final PetServiceImpl petService;

    public PetResource(PetServiceImpl petService) {
        this.petService = petService;
    }


    @GetMapping("{id}")
    public ResponseEntity<Pet> findById(@PathVariable(name = "id") UUID id) {
        return ResponseEntity.ok().body(petService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<Pet>> findAll() {
        return ResponseEntity.ok().body(petService.findAll());
    }
}
