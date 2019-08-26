package com.ascending.training.repository;

import com.ascending.training.model.Pet;
import com.ascending.training.model.User;

import java.util.List;

public interface PetDao {
    boolean save(Pet pet);
    boolean savePet(Pet p, User u );
    int update(Pet pet);
    int delete(long petId);

    List<Pet> getPets();
    List<Pet> getPetsByName(String petName);

}
