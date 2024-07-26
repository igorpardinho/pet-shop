package com.petshop.services.impl;

import com.petshop.domain.Tutor;
import com.petshop.dto.TutorDTO;
import com.petshop.repositories.TutorRepository;
import com.petshop.services.TutorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TutorServiceImpl implements TutorService {

    private final TutorRepository tutorRepository;

    public TutorServiceImpl(TutorRepository tutorRepository) {
        this.tutorRepository = tutorRepository;
    }


    @Override
    public Tutor findById(UUID id) {
        Optional<Tutor> optionalTutor = tutorRepository.findById(id);

        return optionalTutor.orElse(null);

    }

    @Override
    public List<Tutor> findAll() {
        return tutorRepository.findAll();
    }

    @Override
    public Tutor save(Tutor tutor) {
        return tutorRepository.save(tutor);
    }


}
