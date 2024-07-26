package com.petshop.services;

import com.petshop.domain.Pet;



import java.util.List;
import java.util.UUID;

public interface PetService {

    Pet findById(UUID id);

    List<Pet> findAll();

    Pet create(Pet pet);

    void delete(UUID id);

    Pet update(Pet pet);
}
