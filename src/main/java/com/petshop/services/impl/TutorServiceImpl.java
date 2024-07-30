package com.petshop.services.impl;

import com.petshop.domain.Tutor;
import com.petshop.repositories.TutorRepository;
import com.petshop.services.TutorService;
import com.petshop.services.exceptions.ObjectNotFoundException;
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

        return optionalTutor.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));

    }

    @Override
    public List<Tutor> findAll() {
        return tutorRepository.findAll();
    }

    @Override
    public Tutor save(Tutor tutor) {
        return tutorRepository.save(tutor);
    }

    @Override
    public Tutor update(UUID id, Tutor tutor) {
        findById(id);
        return tutorRepository.save(tutor);
    }

    @Override
    public void delete(UUID id) {
        findById(id);
        tutorRepository.deleteById(id);
    }


}
