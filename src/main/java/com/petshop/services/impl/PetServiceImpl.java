package com.petshop.services.impl;

import com.petshop.domain.Pet;
import com.petshop.repositories.PetRepository;
import com.petshop.services.PetService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PetServiceImpl implements PetService {

    private final PetRepository petRepository;

    public PetServiceImpl(PetRepository petRepository) {
        this.petRepository = petRepository;
    }


    @Override
    public Pet findById(UUID id) {
        Optional<Pet> optionalPet = petRepository.findById(id);

        return optionalPet.orElse(null);
    }

    @Override
    public List<Pet> findAll() {
        return petRepository.findAll();
    }
}
