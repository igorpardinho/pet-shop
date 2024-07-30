package com.petshop.services.impl;

import com.petshop.domain.Pet;
import com.petshop.dto.PetDTO;
import com.petshop.repositories.PetRepository;
import com.petshop.services.PetService;
import com.petshop.services.exceptions.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PetServiceImpl implements PetService {

    private final PetRepository petRepository;
    private final ModelMapper modelMapper;


    public PetServiceImpl(PetRepository petRepository, ModelMapper modelMapper) {
        this.petRepository = petRepository;
        this.modelMapper = modelMapper;
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
    public Pet save(PetDTO petDTO) {
        return petRepository.save(modelMapper.map(petDTO,Pet.class));
    }


    @Override
    public void delete(UUID id) {
        findById(id);
        petRepository.deleteById(id);
    }

    @Override
    public Pet update(PetDTO petDTO) {
       return petRepository.save(modelMapper.map(petDTO,Pet.class));

    }
}
