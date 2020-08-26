package com.petstore.models;


import javax.persistence.Entity;
import javax.persistence.*;
import javax.persistence.GenerationType;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    @Column(nullable = false)
    private String storeName;

    @Column(nullable = false)
    private Integer storeNumber;

    private String address;

    private String city;

    private String state;

    private String country;

    @OneToMany(mappedBy = "petStore", fetch = FetchType.EAGER, cascade = {CascadeType.ALL}
    )
    private List<Pet> pets;


    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public Integer getStoreNumber() {
        return storeNumber;
    }

    public void setStoreNumber(Integer storeNumber) {
        this.storeNumber = storeNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<Pet> getPets() {
        return pets;
    }

    public void setPets(List<Pet> pets) {
        this.pets = pets;
    }

    public void addPet(Pet onePet){

        if(this.pets == null){
            this.pets = new ArrayList<>();
        }

        this.pets.add(onePet);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Store{");
        sb.append("Id=").append(Id);
        sb.append(", storeName='").append(storeName).append('\'');
        sb.append(", storeNumber=").append(storeNumber);
        sb.append(", address='").append(address).append('\'');
        sb.append(", city='").append(city).append('\'');
        sb.append(", state='").append(state).append('\'');
        sb.append(", country='").append(country).append('\'');
        sb.append(", pets=").append(pets);
        sb.append('}');
        return sb.toString();
    }
}