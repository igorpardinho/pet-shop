package com.petshop.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

@Entity(name = "TB_TUTOR")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Tutor implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String name;

    private String address;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany()
    private Set<Pet> pets;
}
