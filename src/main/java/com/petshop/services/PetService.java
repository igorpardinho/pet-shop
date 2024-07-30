package com.petshop.services;

import com.petshop.domain.Pet;
import com.petshop.dto.PetDTO;


import java.util.List;
import java.util.UUID;

public interface PetService {

    Pet findById(UUID id);

    List<Pet> findAll();

    Pet save(PetDTO petDTO);

    void delete(UUID id);

    Pet update(PetDTO petDTO);
}
