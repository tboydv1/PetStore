package com.petstore.repository;

import com.petstore.models.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
public interface PetRepository  extends JpaRepository<Pet, Integer> {


}
