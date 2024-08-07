package com.petshop.repositories;

import com.petshop.domain.Pet;
import jakarta.persistence.QueryHint;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Transactional
@Repository
public interface PetRepository extends JpaRepository<Pet, UUID> {

    @QueryHints({
            @QueryHint(name = "javax.persistence.query.timeout", value = "2000"),
            @QueryHint(name = "org.hibernate.fetchSize", value = "20"),
            @QueryHint(name = "org.hibernate.cacheable", value = "true")
    })
    @Override
    Optional<Pet> findById(UUID uuid);

    @QueryHints({
            @QueryHint(name = "javax.persistence.query.timeout", value = "2000"),
            @QueryHint(name = "org.hibernate.cacheable", value = "true")
    })
    @Override
    List<Pet> findAll();
}
