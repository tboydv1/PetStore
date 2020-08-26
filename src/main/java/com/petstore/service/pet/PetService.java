package com.petstore.service.pet;

import com.petstore.models.Pet;

import java.util.List;
import java.util.Optional;

public interface PetService {

    Pet save(Pet pet);

    Optional<Pet> findById(Integer petId);

    Pet update(Pet pet);

    void delete(Integer petid);

    List<Pet> findAll();
}
