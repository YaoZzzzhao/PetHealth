package com.ascending.training.repository;

import com.ascending.training.model.Pet;

import java.util.List;

public interface PetDao {
    boolean save(Pet pet);
    boolean update(Pet pet);
    boolean delete(int petId);

    List<Pet> getPet();
    Pet getPetById(long petId);

}
