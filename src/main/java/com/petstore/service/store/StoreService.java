package com.petstore.service.store;

import com.petstore.models.Pet;
import com.petstore.models.Store;

import java.util.List;

public interface StoreService {

    Store save(Store store);

    Store findById(Integer storeId);

    Store update(Store store);

    void delete(Integer storeId);

    List<Store> findAll();
}
