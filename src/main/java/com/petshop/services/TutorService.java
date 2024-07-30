package com.petshop.services;

import com.petshop.domain.Tutor;
import com.petshop.dto.TutorDTO;


import java.util.List;
import java.util.UUID;

public interface TutorService {

    Tutor findById(UUID id);
    List<Tutor> findAll();
    Tutor save(TutorDTO tutorDTO);
    Tutor update(TutorDTO tutorDTO);
    void delete(UUID id);
}
