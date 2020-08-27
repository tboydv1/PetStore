package com.petstore.service.store;

import com.petstore.models.Pet;
import com.petstore.models.PetSex;
import com.petstore.models.PetTypes;
import com.petstore.models.Store;
import com.petstore.repository.StoreRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Sql(scripts = {"classpath:db\\insert-store.sql"})
class StoreServiceImplTest {

    @Mock
    StoreRepository storeRepository;

    @InjectMocks
    StoreService storeService = new StoreServiceImpl();;

    @Autowired
    StoreRepository concreteStoreRepo;

    Logger log = Logger.getLogger(getClass().getName());


    Store testStore;


    @BeforeEach
    void setUp() {

        MockitoAnnotations.initMocks(this);

        testStore = new Store();
    }

    @Test
    void save() {

        when(storeService.save(testStore)).thenReturn(testStore);
        storeService.save(testStore);

        verify(storeRepository, times(1)).save(testStore);
    }

    @Test
    void findById() {

        when(storeService.findById(45)).thenReturn(Optional.of(testStore));
        storeService.findById(45);

        verify(storeRepository, times(1)).findById(45);
    }

    @Test
    void update() {
        when(storeService.save(testStore)).thenReturn(testStore);
        storeService.save(testStore);

        verify(storeRepository, times(1)).save(testStore);

    }

    @Test
    void delete() {

        doNothing().when(storeRepository).deleteById(31);
        storeService.delete(31);

        verify(storeRepository, times(1)).deleteById(31);
    }

    @Test
    void findAll() {

        List<Store> storeList = storeRepository.findAll();
        when(storeService.findAll()).thenReturn(storeList);

        verify(storeRepository, times(1)).findAll();
    }

    @Test
    void addPets() {

        //retrieve store details
        Store savedStore = concreteStoreRepo.findById(500).orElse(null);
        log.info("Retrieved store from the database --> "+ savedStore);
        assertThat(savedStore).isNotNull();
        assertThat(savedStore.getPets()).hasSize(0);

        //add pets to store
        Pet pet = new Pet();
        pet.setName("Mayor");
        pet.setTypes(PetTypes.BIRD);
        pet.setSex(PetSex.MALE);
        pet.setBreed("Parrot");
        pet.setAge(25);
        pet.setPetStore(savedStore);

        savedStore.addPet(pet);
        //save store
        concreteStoreRepo.save(savedStore);

        //retrieve strore
        Store savedStore2 = concreteStoreRepo.findById(500).orElse(null);
        assertNotNull(savedStore2);
        log.info("Retrieved store after adding pet--> "+savedStore2);

        //verify that pet is added to store
        assertThat(savedStore2.getPets()).hasSize(1);
    }
}