package com.petshop.dto;


import com.petshop.domain.Tutor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PetDTO {


    private UUID id;
    private String name;
    private String gender;
    private float weight;
    private Tutor tutor;

}
