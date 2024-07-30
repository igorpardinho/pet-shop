package com.petshop.services.impl;

import com.petshop.domain.Tutor;
import com.petshop.dto.TutorDTO;
import com.petshop.repositories.TutorRepository;
import com.petshop.services.TutorService;
import com.petshop.services.exceptions.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TutorServiceImpl implements TutorService {

    private final TutorRepository tutorRepository;
    private final ModelMapper modelMapper;

    public TutorServiceImpl(TutorRepository tutorRepository, ModelMapper modelMapper) {
        this.tutorRepository = tutorRepository;
        this.modelMapper = modelMapper;
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
    public Tutor save(TutorDTO tutorDTO) {
        return tutorRepository.save(modelMapper.map(tutorDTO, Tutor.class));
    }

    @Override
    public Tutor update(TutorDTO tutorDTO) {
        findById(tutorDTO.getId());
        return tutorRepository.save(modelMapper.map(tutorDTO, Tutor.class));
    }

    @Override
    public void delete(UUID id) {
        findById(id);
        tutorRepository.deleteById(id);
    }


}
