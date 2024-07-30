package com.petshop.config;

import com.petshop.domain.Pet;
import com.petshop.domain.Tutor;
import com.petshop.repositories.PetRepository;
import com.petshop.repositories.TutorRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;


import java.util.List;


@Configuration
@Profile("local")
public class LocalConfig {

    private final PetRepository petRepository;
    private final TutorRepository tutorRepository;

    public LocalConfig(PetRepository petRepository,TutorRepository tutorRepository) {
        this.petRepository = petRepository;
        this.tutorRepository = tutorRepository;
    }

    @Bean
    public List<Pet> startdb(){
        Pet pet1 = new Pet(null,"pipi","female",10);
        Pet pet2 = new Pet(null,"nega","female",15.60f);

       return petRepository.saveAll(List.of(pet1,pet2));
    }
    @Bean
    public List<Tutor> startdbTutor(){

        Tutor tutor1 = new Tutor(null,"Livia","Rua teste 123",null);
        Tutor tutor2 = new Tutor(null,"Igor","Rua teste 123", null );

        return tutorRepository.saveAll(List.of(tutor1,tutor2));
    }
}
