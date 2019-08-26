package com.ascending.training.repository;

import com.ascending.training.model.Dog;
import com.ascending.training.model.Pet;

import java.util.List;

public interface DogDao {
    boolean save(Dog dog);
    boolean saveDog(Dog dog, Pet pet);
    int update(Dog dog);
    int delete(long id);

    List<Dog> getDogs();
    List<Dog> getDogsByName(String dogName);
}
