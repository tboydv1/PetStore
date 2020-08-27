package com.petstore.web;


import com.petstore.models.Pet;
import com.petstore.models.PetSex;
import com.petstore.models.PetTypes;
import com.petstore.models.Store;
import com.petstore.service.exception.StoreObjectNotPresentException;
import com.petstore.service.store.StoreService;
import com.petstore.web.dto.StorePetDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@Controller
@RequestMapping("/store")
public class StoreController {

    @Autowired
    StoreService storeService;

    Logger log = Logger.getLogger(getClass().getName());

    @GetMapping("/form")
    public String getStoreForm(Model model){
        model.addAttribute("store", new Store());

        return "create-store";
    }

    @PostMapping("/create")
    public String createStore(@ModelAttribute("store") Store store, Model model){

        log.info("Store information --> "+ store);

        store =  storeService.save(store);

        log.info("Store info after saving to the databse --> "+ store);
        model.addAttribute("storeObj", store);
        return "store-info";
    }

    @GetMapping("/list")
    public String getAllStores(Model model){

        List<Store> storeList = storeService.findAll();

        model.addAttribute("storeList", storeList);

        return "store-list";
    }

    @GetMapping("/pets/{id}")
    public String getStorePets(@PathVariable("id") Integer id, Model model)  {

        log.info("find store pets request --> "+ id);
        List<Pet> storePets = null;
        try {
           storePets = storeService.findStorePets(id);
        }catch (StoreObjectNotPresentException e){
            log.info("Store object not present");
            return "redirect:/";
        }


        model.addAttribute("storePets", storePets);
        return "pet-list";
    }

    @GetMapping("/pets/create/{id}")
    public String mapStoreIdToPet(@PathVariable("id") Integer storeId, Model model){

        log.info("store id --> "+ storeId);

        //create a dto and set id
        StorePetDTO store = new StorePetDTO();
        store.setStoreId(storeId);


        model.addAttribute("storePet", store);


        //return pet create form
        return "create-pet";

    }

    @PostMapping("/pet/save")
    public String savePetToStore(@ModelAttribute("storePet") StorePetDTO petDto,
                                  Model model){

        log.info("store Pet info --> "+ petDto);

        //create a pet object
        Pet pet = new Pet();
        pet.setName(petDto.getName());
        pet.setBreed(petDto.getBreed());
        pet.setAge(petDto.getAge());
        pet.setSex(PetSex.valueOf(petDto.getSex()));
        pet.setTypes(PetTypes.valueOf(petDto.getTypes()));

        try {
            storeService.addPets(pet, petDto.getStoreId());
        }catch(StoreObjectNotPresentException e){
            return "redirect:/store/list";
        }

        return "redirect:/store/list";
    }


}
