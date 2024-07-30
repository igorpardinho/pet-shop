package com.petshop.resources;


import com.petshop.domain.Tutor;
import com.petshop.dto.TutorDTO;
import com.petshop.services.impl.TutorServiceImpl;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/tutor")
public class TutorResource {

    public static final String ID = "{id}";
    private final TutorServiceImpl tutorService;
    private final ModelMapper modelMapper;

    public TutorResource(TutorServiceImpl tutorService, ModelMapper modelMapper) {
        this.tutorService = tutorService;
        this.modelMapper = modelMapper;
    }

    @GetMapping(ID)
    public ResponseEntity<TutorDTO> findById(@PathVariable(value = "id") UUID id) {

        return ResponseEntity.ok().body(modelMapper.map(tutorService.findById(id), TutorDTO.class));
    }

    @GetMapping
    public ResponseEntity<List<TutorDTO>> findAll() {
        return ResponseEntity.ok().body(tutorService.findAll().stream().map(t -> modelMapper.map(t, TutorDTO.class))
                .toList());
    }

    @PostMapping
    public ResponseEntity<Tutor> save(@RequestBody @Valid TutorDTO tutorDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(tutorService.save(modelMapper.map(tutorDTO, Tutor.class)));
    }

    @PutMapping(ID)
    public ResponseEntity<Tutor> update(@PathVariable(value = "id") UUID id, @RequestBody @Valid TutorDTO tutorDTO) {
        tutorDTO.setId(id);
        return ResponseEntity.ok().body(tutorService.update(id,modelMapper.map(tutorDTO,Tutor.class)));
    }

    @DeleteMapping(ID)
    public ResponseEntity<Tutor> delete(@PathVariable(value = "id") UUID id){
        tutorService.delete(id);
        return ResponseEntity.noContent().build();
    }


}
