package com.petshop.dto;

import com.petshop.domain.Pet;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TutorDTO {

    private UUID id;

    private String name;

    private String address;

    private Set<Pet> pets;
}
