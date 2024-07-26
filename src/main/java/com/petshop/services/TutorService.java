package com.petshop.services;

import com.petshop.domain.Tutor;

import java.util.List;
import java.util.UUID;

public interface TutorService {

    Tutor findById(UUID id);
    List<Tutor> findAll();
}
