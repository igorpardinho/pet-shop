package com.petshop.dto;


import com.petshop.domain.Tutor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    @NotBlank
    private String  name;
    @NotBlank
    private String gender;
    @NotNull
    private float weight;

    private Tutor tutor;

}
