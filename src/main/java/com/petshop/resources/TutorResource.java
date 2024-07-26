package com.petshop.resources;


import com.petshop.dto.TutorDTO;
import com.petshop.services.impl.TutorServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/tutor")
public class TutorResource {

    private final TutorServiceImpl tutorService;
    private final ModelMapper modelMapper;

    public TutorResource(TutorServiceImpl tutorService, ModelMapper modelMapper) {
        this.tutorService = tutorService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("{id}")
    public ResponseEntity<TutorDTO> findById(@PathVariable(value = "id") UUID id) {

        return ResponseEntity.ok().body(modelMapper.map(tutorService.findById(id), TutorDTO.class));
    }

    @GetMapping
    public ResponseEntity<List<TutorDTO>> findAll() {
        return ResponseEntity.ok().body(tutorService.findAll().stream().map(t -> modelMapper.map(t, TutorDTO.class))
                .toList());
    }
}
