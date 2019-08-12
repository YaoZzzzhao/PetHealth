package com.ascending.training.repository;

import com.ascending.training.model.Pet;

import java.util.List;

public interface PetDao {
    boolean save(Pet pet);
    int update(Pet pet);
    int delete(long petId);

    List<Pet> getPet();
    Pet getPetById(long petId);

}
