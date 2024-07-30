package com.petshop.services.impl;

import com.petshop.domain.Pet;
import com.petshop.repositories.PetRepository;
import com.petshop.services.PetService;
import com.petshop.services.exceptions.ObjectNotFoundException;
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

        return optionalPet.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
    }

    @Override
    public List<Pet> findAll() {
        return petRepository.findAll();
    }

    @Override
    public Pet save(Pet pet) {
        return petRepository.save(pet);
    }

    @Override
    public void delete(UUID id) {
        findById(id);
        petRepository.deleteById(id);
    }

    @Override
    public Pet update(Pet pet) {
       return petRepository.save(pet);

    }
}
