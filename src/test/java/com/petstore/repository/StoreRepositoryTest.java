package com.petstore.repository;

import com.petstore.models.Pet;
import com.petstore.models.PetSex;
import com.petstore.models.PetTypes;
import com.petstore.models.Store;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.transaction.Transactional;
import java.util.List;
import java.util.logging.Logger;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Sql(scripts = {"classpath:db\\insert-store.sql"})
class StoreRepositoryTest {

    Logger log = Logger.getLogger(getClass().getName());

    @Autowired
    StoreRepository storeRepository;

    Store testStore;

    @BeforeEach
    void setUp() {

        testStore = storeRepository.findById(500).orElse(null);
        assertThat(testStore).isNotNull();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void createNewStoreTest(){

        Store londonStore = new Store();
        londonStore.setStoreName("londonStore");
        londonStore.setState("Lagos");
        londonStore.setCity("Yaba");
        londonStore.setStoreNumber(1451);
        londonStore.setAddress("12, london street");
        londonStore.setCountry("Nigeria");

        assertThat(londonStore.getId()).isNull();

        londonStore = storeRepository.save(londonStore);

        log.info("Store object after saving --> "+ londonStore);
        assertThat(londonStore.getId()).isNotNull();

    }

    @Test
    void whenAddPetsToStore_thenSaveToDbTest(){

        Pet pet = new Pet();
        pet.setName("Jack");
        pet.setBreed("Bull Dog");
        pet.setTypes(PetTypes.DOG);
        pet.setSex(PetSex.MALE);
        pet.setAge(5);
        testStore.addPet(pet);
        pet.setPetStore(testStore);

        testStore = storeRepository.save(testStore);

        log.info("Added pets to the store --> "+testStore);

    }

    @Test
    void whenStoreIsRetrieved_thenRetrievedStoredPets(){

        //Create pets
        Pet pet1 = new Pet();
        pet1.setName("Jack");
        pet1.setBreed("Bull Dog");
        pet1.setTypes(PetTypes.DOG);
        pet1.setSex(PetSex.MALE);
        pet1.setAge(6);
        pet1.setPetStore(testStore);

        Pet pet2 = new Pet();
        pet2.setName("Jack");
        pet2.setBreed("Bull Dog");
        pet2.setTypes(PetTypes.DOG);
        pet2.setSex(PetSex.MALE);
        pet2.setAge(7);
        pet2.setPetStore(testStore);

        //add pets to store
        testStore.addPet(pet2);
        testStore.addPet(pet1);

        //save store
        testStore = storeRepository.save(testStore);
        log.info("Store object saved to the db --> "+ testStore);

        Store savedStore = storeRepository.findById(testStore.getId()).orElse(null);
        assertThat(savedStore.getPets()).isNotNull();
        assertThat(savedStore.getPets()).hasSize(2);
    }

    @Test
    void retriveAllPetsinAStoreTest(){

        //Create pets
        Pet pet1 = new Pet();
        pet1.setName("Jack");
        pet1.setBreed("Bull Dog");
        pet1.setTypes(PetTypes.DOG);
        pet1.setSex(PetSex.MALE);
        pet1.setAge(6);
        pet1.setPetStore(testStore);

        Pet pet2 = new Pet();
        pet2.setName("Jack");
        pet2.setBreed("Bull Dog");
        pet2.setTypes(PetTypes.DOG);
        pet2.setSex(PetSex.MALE);
        pet2.setAge(7);
        pet2.setPetStore(testStore);

        //add pets to store
        testStore.addPet(pet2);
        testStore.addPet(pet1);

        //save store
        testStore = storeRepository.save(testStore);
        log.info("Store object saved to the db --> "+ testStore);

        Store savedStore = storeRepository.findById(testStore.getId()).orElse(null);
        assertThat(savedStore.getPets()).isNotNull();
        assertThat(savedStore.getPets()).hasSize(2);

        List<Pet> petList = savedStore.getPets();

        petList.forEach(System.out::println);
    }


}