package com.petshop.dto;

import com.petshop.domain.Pet;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TutorDTO {

    private UUID id;
    @NotBlank
    private String name;
    @NotBlank
    private String address;

    private Set<Pet> pets = new HashSet<>();
}
