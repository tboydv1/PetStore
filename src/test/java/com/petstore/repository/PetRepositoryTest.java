package com.petstore.repository;

import com.petstore.models.Pet;
import com.petstore.models.PetSex;
import com.petstore.models.PetTypes;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Sql(scripts = {"classpath:db\\insert-pet.sql"} , executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD )
class PetRepositoryTest {

    Logger log = Logger.getLogger(getClass().getName());
    @Autowired
    PetRepository petRepository;
    Pet testPetData;
    @BeforeEach
    void setUp() {

        testPetData = petRepository.findById(200).orElse(null);
        assertThat(testPetData).isNotNull();
        log.info("Test pet data retrieved from database --> "+ testPetData);

    }
    @AfterEach
    void tearDown() {
    }
    @Test
    void createPetObject_thenSaveToDatabaseTest(){

        //create pet object
        Pet pet = new Pet();
        pet.setAge(5);
        pet.setName("Lucky");
        pet.setSex(PetSex.MALE);
        pet.setTypes(PetTypes.DOG);
        pet.setBreed("bull dog");
        pet.setBirthDate(new Date());

        log.info("Created pet object --> "+ pet);
        assertThat(pet.getId()).isNull();

        //save pet object to the database
        pet = petRepository.save(pet);
        log.info("After saving pet object --> "+ pet);
        assertThat(pet.getId()).isNotNull();
    }

    @Test
    void whenFindAllPetsIsCalled_thenRetrievePetsListTest(){

        List<Pet> allPets = petRepository.findAll();
        assertThat(allPets.size()).isEqualTo(8);
        log.info("All pets retrieved from the database --> "+ allPets);
    }

    @Test
    void whenPetDetailsIsUpdated_thenUpdateDatabaseDetails(){

        //update pet name
        assertThat(testPetData.getName()).isEqualTo("bobby");
        testPetData.setName("jiran");

        testPetData = petRepository.save(testPetData);
        assertThat(testPetData.getName()).isEqualTo("jiran");
    }

    @Test
    void whenDeleteisCalled_thenDeletePetDataTest(){

        List<Pet> allPets = petRepository.findAll();
        assertThat(allPets).isNotNull();
        assertThat(allPets.size()).isEqualTo(8);

        Pet savedPet = petRepository.findById(204).orElse(null);
        assertThat(savedPet).isNotNull();
        petRepository.deleteById(204);

        Pet deletedPet = petRepository.findById(204).orElse(null);
        assertThat(deletedPet).isNull();

        allPets = petRepository.findAll();
        assertThat(allPets).isNotNull();
        assertThat(allPets.size()).isEqualTo(7);

    }


}