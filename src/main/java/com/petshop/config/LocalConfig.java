package com.petshop.config;

import com.petshop.domain.Pet;
import com.petshop.repositories.PetRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.List;

@Configuration
@Profile("local")
public class LocalConfig {

    private final PetRepository petRepository;

    public LocalConfig(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    @Bean
    public List<Pet> startdb(){
        Pet pet1 = new Pet(null,"pipi","female",10);
        Pet pet2 = new Pet(null,"nega","female",15.60f);

       return petRepository.saveAll(List.of(pet1,pet2));
    }
}
